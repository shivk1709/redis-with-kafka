package com.java.book;

import com.project.AuthorDto;
import com.project.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorClient authorClient;

    @Autowired
    private CacheManager cacheManager;


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC_NAME = "update-cache";


    private BookDTO convertToDTO(Book book)
    {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setGenre(book.getGenre());
        dto.setAuthorId(book.getAuthorId());
        return dto;
    }

    private Book convertToEntity(BookDTO dto)
    {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setGenre(dto.getGenre());
        book.setAuthorId(dto.getAuthorId());
        return book;
    }

    @Override
    @CachePut(value = "books", key = "#result.id")
    public BookDTO createBook(BookDTO dto)
    {
        Book book = convertToEntity(dto);

        AuthorDto authorDto = authorClient.getAuthorById(book.getAuthorId());
        if (authorDto == null)
        {
            throw new IllegalArgumentException("Author not found");
        }

        Book saved = bookRepository.save(book);
        BookDTO savedDto = convertToDTO(saved);

        kafkaTemplate.send(TOPIC_NAME, String.valueOf(saved.getId()), savedDto.getAuthorId());

        return savedDto;
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public BookDTO getBookById(Long id)
    {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(book);
    }

    @Override
    @Cacheable(value = "book-by-author", key = "#authorId")
    public List<BookDTO> getBooksByAuthorId(Long authorId)
    {
        return bookRepository.findByAuthorId(authorId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAllBooks()
    {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "books", key = "#id")
    @CacheEvict(value = "book-by-author", key = "#dto.authorId")
    public BookDTO updateBook(Long id, BookDTO dto)
    {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setGenre(dto.getGenre());
        book.setAuthorId(dto.getAuthorId());

        Book updatedBook = bookRepository.save(book);
        BookDTO updatedDto = convertToDTO(updatedBook);

        kafkaTemplate.send(TOPIC_NAME, String.valueOf(id), updatedDto.getAuthorId());

        return updatedDto;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "books", key = "#id"),
    })
    public void deleteBook(Long id)
    {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        kafkaTemplate.send(TOPIC_NAME, String.valueOf(id), book.getAuthorId());
        cacheManager.getCache("book-by-author").evict(book.getAuthorId());
        bookRepository.deleteById(id);
    }
}

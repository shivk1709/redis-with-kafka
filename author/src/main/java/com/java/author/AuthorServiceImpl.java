package com.java.author;

import com.project.AuthorDto;
import com.project.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService
{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookClient bookClient;

    private AuthorDto convertToDTO(Author author)
    {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        dto.setBio(author.getBio());

        // Fetch books from Book microservice
        List<BookDTO> books = bookClient.getBooksByAuthorId(author.getId());
        dto.setBooks(books);

        return dto;
    }

    private Author convertToEntity(AuthorDto dto)
    {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setEmail(dto.getEmail());
        author.setBio(dto.getBio());
        return author;
    }

    @Override
    @CachePut(value = "authors", key = "#result.id")
    public AuthorDto createAuthor(AuthorDto authorDTO)
    {
        Author author = convertToEntity(authorDTO);
        return convertToDTO(authorRepository.save(author));
    }

    @Override
    @Cacheable(value = "authors", key = "#id")
    public AuthorDto getAuthorById(Long id)
    {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return convertToDTO(author);
    }

    @Override
    @Cacheable(value = "authors", key = "'all'")
    public List<AuthorDto> getAllAuthors()
    {
        return authorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "authors", key = "#id")
    public AuthorDto updateAuthor(Long id, AuthorDto authorDTO)
    {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(authorDTO.getName());
        author.setEmail(authorDTO.getEmail());
        author.setBio(authorDTO.getBio());

        return convertToDTO(authorRepository.save(author));
    }

    @Override
    @CacheEvict(value = "authors", key = "#id")
    public void deleteAuthor(Long id)
    {
        if (!authorRepository.existsById(id))
        {
            throw new RuntimeException("Author not found");
        }
        authorRepository.deleteById(id);
    }

}
package com.java.book;

import com.project.BookDTO;

import java.util.List;

public interface BookService
{
    BookDTO createBook(BookDTO dto);

    BookDTO getBookById(Long id);

    List<BookDTO> getBooksByAuthorId(Long authorId);

    List<BookDTO> getAllBooks();

    BookDTO updateBook(Long id, BookDTO dto);

    void deleteBook(Long id);
}
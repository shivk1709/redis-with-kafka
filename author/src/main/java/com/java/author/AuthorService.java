package com.java.author;

import com.project.AuthorDto;

import java.util.List;

public interface AuthorService
{
    AuthorDto createAuthor(AuthorDto authorDTO);

    AuthorDto getAuthorById(Long id);

    List<AuthorDto> getAllAuthors();

    AuthorDto updateAuthor(Long id, AuthorDto authorDTO);

    void deleteAuthor(Long id);
}
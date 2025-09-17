package com.project;

import java.util.List;


public class AuthorDto
{
    private Long id;
    private String name;
    private String email;
    private String bio;
    private List<BookDTO> books;

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public void setBooks(List<BookDTO> books)
    {
        this.books = books;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getBio()
    {
        return bio;
    }

    public List<BookDTO> getBooks()
    {
        return books;
    }
}

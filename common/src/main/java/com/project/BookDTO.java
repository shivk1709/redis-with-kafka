package com.project;

import java.io.Serializable;

public class BookDTO implements Serializable
{
    private Long id;
    private String title;
    private String genre;
    private Long authorId;

    public Long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGenre()
    {
        return genre;
    }

    public Long getAuthorId()
    {
        return authorId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setAuthorId(Long authorId)
    {
        this.authorId = authorId;
    }

    public BookDTO(Long id, String title, String genre, Long authorId)
    {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
    }

    public BookDTO()
    {
    }
}

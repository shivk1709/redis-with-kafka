package com.java.book;

import com.project.AuthorDto;
import com.project.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "author-service", url = "http://localhost:8081")
public interface AuthorClient
{
    @GetMapping("/api/authors/{authorId}")
    AuthorDto getAuthorById(@PathVariable Long authorId);
}
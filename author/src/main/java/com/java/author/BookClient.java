package com.java.author;

import com.project.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8082")
public interface BookClient
{
    @GetMapping("/api/books/author/{authorId}")
    List<BookDTO> getBooksByAuthorId(@PathVariable Long authorId);
}

package com.java.author;

import com.project.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto dto)
    {
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id)
    {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors()
    {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto dto)
    {
        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id)
    {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}

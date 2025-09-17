package com.java.book;

import com.project.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController
{

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO dto)
    {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthorId(@PathVariable Long authorId)
    {
        return ResponseEntity.ok(bookService.getBooksByAuthorId(authorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto)
    {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id)
    {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

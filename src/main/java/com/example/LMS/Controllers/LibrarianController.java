package com.example.LMS.Controllers;

import com.example.LMS.Models.Book;
import com.example.LMS.Services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;


    @PostMapping("/books")
    public Book addBooks(@RequestBody Book book) {
        System.out.println("Received request");
        return librarianService.addBooks(book);
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteBooks(@RequestBody Book book) {
        try {
            librarianService.removeBooks(book);
            return ResponseEntity.status(204).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

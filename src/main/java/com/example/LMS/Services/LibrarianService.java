package com.example.LMS.Services;

import com.example.LMS.Models.Book;
import com.example.LMS.Repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibrarianService {
    @Autowired
    private BookRepo bookRepo;

    public Book addBooks(Book book) {
        return bookRepo.save(book);
    }

    public void removeBooks(Book book) {
        Optional<Book> bookOptional = bookRepo.findById(book.getBookId());
        if(bookOptional.isEmpty()) throw new RuntimeException("Book not found");
        else {
            bookRepo.delete(book);
        }
    }

}

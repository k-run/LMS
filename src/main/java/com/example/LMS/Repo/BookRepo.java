package com.example.LMS.Repo;

import com.example.LMS.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);
    Book findBybookISBN(String bookISBN);
    Book findBybookName(String bookName);

}

package com.example.LMS.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "book_isbn", nullable = false)
    private String bookISBN;

    @Column(name = "author", nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "availableStatus", nullable = false)
    private AvailableStatus availableStatus;

}

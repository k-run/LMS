package com.example.LMS.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "librarian")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "librarian_id")
    private Long librarianId;

    @Column(name = "librarian_name")
    private String librarianName;
}

package com.example.LMS.DTO;

import com.example.LMS.Models.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowResponseDto {
    private String message;
    private List<Book> borrowedBooks;
}

package com.example.LMS.DTO;

import com.example.LMS.Models.Book;
import com.example.LMS.Models.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequestDto {
    private Long memberId;
    private List<Book> books;

}

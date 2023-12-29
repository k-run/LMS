package com.example.LMS.Controllers;

import com.example.LMS.DTO.BorrowRequestDto;
import com.example.LMS.DTO.BorrowResponseDto;
import com.example.LMS.Models.Book;
import com.example.LMS.Models.Member;
import com.example.LMS.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/searchByName/{name}")
    public Book searchByName(@PathVariable String name) {
        System.out.println("Searching for " + name);
        return memberService.searchByName(name);
    }

    @GetMapping(value = "/searchByAuthor/{author}")
    public List<Book> searchByAuthor(@PathVariable String author) {
        return memberService.searchByAuthor(author);
    }

    @GetMapping(value = "/searchByISBN/{isbn}")
    public Book searchByISBN(@PathVariable String isbn) {
        return memberService.searchByISBN(isbn);
    }

    @PostMapping(value = "/borrowBook")
    public ResponseEntity<BorrowResponseDto> borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        BorrowResponseDto borrowResponseDto = memberService.borrowBook(borrowRequestDto.getMemberId(), borrowRequestDto.getBooks());
        return ResponseEntity.ok(borrowResponseDto);
    }

    @PostMapping(value = "/returnBook")
    public ResponseEntity<BorrowResponseDto> returnBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        BorrowResponseDto borrowResponseDto = memberService.returnBook(borrowRequestDto.getMemberId(), borrowRequestDto.getBooks());
        return ResponseEntity.ok(borrowResponseDto);
    }

}

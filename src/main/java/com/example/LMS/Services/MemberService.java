package com.example.LMS.Services;

import com.example.LMS.DTO.BorrowResponseDto;
import com.example.LMS.Models.AvailableStatus;
import com.example.LMS.Models.Book;
import com.example.LMS.Models.Member;
import com.example.LMS.Repo.BookRepo;
import com.example.LMS.Repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final BookRepo bookRepo;
    private final MemberRepo memberRepo;

    @Autowired
    public MemberService(BookRepo bookRepo, MemberRepo memberRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    public Book searchByName(String name) {
        System.out.println("inside search by name");
        return bookRepo.findBybookName(name);
    }

    public List<Book> searchByAuthor(String author) {
        System.out.println("inside search by author");
        return bookRepo.findByAuthor(author);
    }

    public Book searchByISBN(String isbn) {
        System.out.println("inside search by isbn");
        return bookRepo.findBybookISBN(isbn);
    }

    public BorrowResponseDto borrowBook(Long memberId, List<Book> books) {
        Optional<Member> member = memberRepo.findById(memberId);
        if(member.isEmpty())
            return new BorrowResponseDto("No such Member exists", List.of());

        List<Book> borrowedBooks = new ArrayList<>();

        synchronized (this) {
            for (Book book : books) {
                Optional<Book> libraryBook = bookRepo.findById(book.getBookId());

                if(libraryBook.isEmpty()) return new BorrowResponseDto ("Book does not exists", List.of());
                Book borrowedBook = libraryBook.get();

                if (borrowedBook.getAvailableStatus() == AvailableStatus.UNAVAILABLE) {
                    System.out.println("Book not available at the moment. Please try again later");
                    continue;
                }

                // check if member
                Date currentDate = Date.from(Instant.now());
                if (currentDate.after(member.get().getMemberShipDateFrom()) &&
                        currentDate.before(member.get().getMemberShipDateTo())) {

                    borrowedBook.setAvailableStatus(AvailableStatus.UNAVAILABLE);

                    borrowedBooks.add(book);
                    //librarianService.removeBooks(book);
                }
            }

        }

        BorrowResponseDto borrowResponseDto = new BorrowResponseDto();
        borrowResponseDto.setBorrowedBooks(borrowedBooks);
        borrowResponseDto.setMessage("Successfully borrowed " + borrowedBooks.size() + " books");
        return borrowResponseDto;
    }

    public BorrowResponseDto returnBook(Long memberId, List<Book> books) {
        Optional<Member> member = memberRepo.findById(memberId);
        if(member.isEmpty())
            return new BorrowResponseDto("No such Member exists", List.of());

        List<Book> returnedBooks = new ArrayList<>();

        synchronized (this) {
            for (Book book : books) {
                Optional<Book> libraryBook = bookRepo.findById(book.getBookId());

                if(libraryBook.isEmpty()) return new BorrowResponseDto ("Book does not exists", List.of());
                Book returnedBook = libraryBook.get();

                // check if member
                Date currentDate = Date.from(Instant.now());
                if (currentDate.after(member.get().getMemberShipDateFrom()) &&
                        currentDate.before(member.get().getMemberShipDateTo())) {

                    returnedBook.setAvailableStatus(AvailableStatus.AVAILABLE);

                    //bookRepo.getBookMap().put(book.getBookId(), returnedBook);
                    returnedBooks.add(book);
                    //librarianService.addBooks(book);
                }
            }

        }

        BorrowResponseDto borrowResponseDto = new BorrowResponseDto();
        borrowResponseDto.setBorrowedBooks(returnedBooks);
        borrowResponseDto.setMessage("Successfully returned " + returnedBooks.size() + " books");
        return borrowResponseDto;
    }

}

package com.stack.puzzle.domain.book.controller;

import com.stack.puzzle.domain.book.dto.BookResponse;
import com.stack.puzzle.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public List<BookResponse> getBooks(@RequestParam(value="emotion") String emotion) {
        return bookService.getNaverBooks(emotion);
    }

}

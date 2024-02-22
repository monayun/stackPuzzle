package com.stack.puzzle.domain.book.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponse {
    private String title;
    private String author;
    private String link;
    private String image;
    private String description;
}

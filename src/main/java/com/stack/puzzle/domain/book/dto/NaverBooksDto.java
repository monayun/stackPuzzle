package com.stack.puzzle.domain.book.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class NaverBooksDto {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<ItemList> items;


    @Getter
    @ToString
    public static class ItemList {
        private String title;
        private String link;
        private String image;
        private String author;
        private String discount;
        private String publisher;
        private String pubdate;
        private String isbn;
        private String description;
    }

}

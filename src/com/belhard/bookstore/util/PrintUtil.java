package com.belhard.bookstore.util;

import com.belhard.bookstore.service.dto.BookDto;

public class PrintUtil {
    public static void showBriefInfo(BookDto bookDto) {
        System.out.println("Id: " + bookDto.getId() + "\nauthor: " + bookDto.getAuthor() + " - title: " + bookDto.getTitle() +
                ", cover: " + bookDto.getCover() + "\nPrice: " + bookDto.getPrice());
    }
}
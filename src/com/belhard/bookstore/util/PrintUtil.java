package com.belhard.bookstore.util;

import com.belhard.bookstore.dao.entity.Book;

public class PrintUtil {
    public static void showBriefInfo(Book book) {
        System.out.println("Id: " + book.getId() + "\nauthor: " + book.getAuthor() + " - title: " + book.getTitle() +
                ", cover: " + book.getCover() + "\nPrice: " + book.getPrice());
    }
}
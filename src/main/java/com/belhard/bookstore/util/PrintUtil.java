package com.belhard.bookstore.util;

import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.dto.UserDto;

public class PrintUtil {
    public static void showBriefInfo(BookDto bookDto) {
        System.out.println("Id: " + bookDto.getId() + "\nauthor: " + bookDto.getAuthor() + " - title: " +
                bookDto.getTitle() + ", cover: " + bookDto.getCover() + "\nPrice: " + bookDto.getPrice());
    }

    public static void showBriefInfo(UserDto userDto) {
        System.out.println("Id: " + userDto.getId() + "\nFirst name: " + userDto.getFirstName() + "\nLast name: " +
                userDto.getLastName() + "\nRole: " + userDto.getRole() + "\nEmail: " + userDto.getEmail() +
                "\nPassword: " + userDto.getPassword());
    }
}
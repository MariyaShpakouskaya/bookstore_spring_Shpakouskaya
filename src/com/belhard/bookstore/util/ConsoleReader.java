package com.belhard.bookstore.util;

import com.belhard.bookstore.service.dto.BookDto;

import java.util.Scanner;

public class ConsoleReader {

    public static BookDto readerForCreateBookDto(Scanner scanner) {
        BookDto bookDto = new BookDto();
        System.out.println("Please, enter isbn: ");
        bookDto.setIsbn(scanner.nextLine());
        System.out.println("Please, enter author: ");
        bookDto.setAuthor(scanner.nextLine());
        System.out.println("Please, enter title: ");
        bookDto.setTitle(scanner.nextLine());
        System.out.println("Please, enter cover: ");
        if (scanner.hasNextLine()) {
            String userCover = scanner.nextLine();
            if (userCover.equalsIgnoreCase("SOFT")
                    || userCover.equalsIgnoreCase("HARD")
                    || userCover.equalsIgnoreCase("SPECIAL")) {
                bookDto.setCover(BookDto.Cover.valueOf(userCover.toUpperCase()));
            }
        } else {
            throw new RuntimeException("Your cover doesn't exist!");
        }
        System.out.println("Please, enter price: ");
        bookDto.setPrice(scanner.nextBigDecimal());
        return bookDto;
    }

    public static BookDto readerForUpdateBookDto(Scanner scanner, BookDto bookDto) {
        System.out.println("Please, enter isbn: ");
        bookDto.setIsbn(scanner.nextLine());
        System.out.println("Please, enter author: ");
        bookDto.setAuthor(scanner.nextLine());
        System.out.println("Please, enter title: ");
        bookDto.setTitle(scanner.nextLine());
        System.out.println("Please, enter cover: ");
        if (scanner.hasNextLine()) {
            String userCover = scanner.nextLine();
            if (userCover.equalsIgnoreCase("SOFT")
                    || userCover.equalsIgnoreCase("HARD")
                    || userCover.equalsIgnoreCase("SPECIAL")) {
                bookDto.setCover(BookDto.Cover.valueOf(userCover.toUpperCase()));
            }
        } else {
            throw new RuntimeException("Your cover doesn't exist!");
        }
        System.out.println("Please, enter price: ");
        bookDto.setPrice(scanner.nextBigDecimal());
        return bookDto;
    }
}

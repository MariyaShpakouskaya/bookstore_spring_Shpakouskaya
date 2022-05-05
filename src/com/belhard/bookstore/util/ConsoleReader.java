package com.belhard.bookstore.util;

import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.dto.UserDto;

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

    public static UserDto readerForCreateUserDto(Scanner scanner) {
        UserDto userDto = new UserDto();
        System.out.println("Please, enter first name: ");
        userDto.setFirstName(scanner.nextLine());
        System.out.println("Please, enter last name: ");
        userDto.setLastName(scanner.nextLine());
        System.out.println("Please, enter email: ");
        userDto.setEmail(scanner.nextLine());
        System.out.println("Please, enter role: ");
        if (scanner.hasNextLine()) {
            String userRole = scanner.nextLine();
            if (userRole.equalsIgnoreCase("ADMIN")
                    || userRole.equalsIgnoreCase("MANAGER")
                    || userRole.equalsIgnoreCase("CUSTOMER")) {
                userDto.setRole(UserDto.Role.valueOf(userRole.toUpperCase()));
            }
        } else {
            throw new RuntimeException("Your role doesn't exist!");
        }
        System.out.println("Please, enter password: ");
        userDto.setPassword(scanner.nextLine());
        return userDto;
    }

    public static UserDto readerForUpdateUserDto(Scanner scanner, UserDto userDto) {
        System.out.println("Please, enter first name: ");
        userDto.setFirstName(scanner.nextLine());
        System.out.println("Please, enter last name: ");
        userDto.setLastName(scanner.nextLine());
        System.out.println("Please, enter email: ");
        userDto.setEmail(scanner.nextLine());
        System.out.println("Please, enter role: ");
        if (scanner.hasNextLine()) {
            String userRole = scanner.nextLine();
            if (userRole.equalsIgnoreCase("ADMIN")
                    || userRole.equalsIgnoreCase("MANAGER")
                    || userRole.equalsIgnoreCase("CUSTOMER")) {
                userDto.setRole(UserDto.Role.valueOf(userRole.toUpperCase()));
            }
        } else {
            throw new RuntimeException("Your role doesn't exist!");
        }
        System.out.println("Please, enter password: ");
        userDto.setPassword(scanner.nextLine());
        return userDto;
    }
}

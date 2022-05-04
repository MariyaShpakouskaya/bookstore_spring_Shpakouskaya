package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.BookServiceImpl;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.util.PrintUtil;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final BookService BOOK_SERVICE = new BookServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chooseMainMenuOption(scanner);
        scanner.close();
    }

    public static void chooseMainMenuOption(Scanner scanner) {
        System.out.println("enter \"all\" to see all books\n" +
                "enter \"get id \" to find a book by id\n" +
                "enter \"delete id\" to delete book by id\n" +
                "enter \"create\" to add a book to the catalog\n" +
                "enter \"update id\" to change an existing book by id\n" +
                "enter \"exit\" to exit the program\n");
        String input = scanner.nextLine();
        String[] options = input.split(" ");
        String command = options[0];
        switch (command) {
            case "all": {
                List<BookDto> bookDtos = BOOK_SERVICE.getAllBooks();
                for (BookDto bookDto : bookDtos) {
                    PrintUtil.showBriefInfo(bookDto);
                }
                break;
            }
            case "get": {
                System.out.println(BOOK_SERVICE.getBookById(Long.parseLong(options[1])));
                break;
            }
            case "delete": {
                BOOK_SERVICE.deleteBook(Long.parseLong(options[1]));
                System.out.println("Book has been removed!");
                break;
            }
            case "create": {
                BookDto bookDto = new BookDto();
                System.out.println("Please, enter isbn: ");
                bookDto.setIsbn(scanner.nextLine());
                System.out.println("Please, enter author: ");
                bookDto.setAuthor(scanner.nextLine());
                System.out.println("Please, enter title: ");
                bookDto.setTitle(scanner.nextLine());
                System.out.println("Please, enter cover: ");
                bookDto.setCover(BookDto.Cover.valueOf(scanner.nextLine()));
                System.out.println("Please, enter price: ");
                bookDto.setPrice(scanner.nextBigDecimal());
                BookDto createdBook = BOOK_SERVICE.createBook(bookDto);
                System.out.println("Book was created: ");
                PrintUtil.showBriefInfo(createdBook);
                break;
            }
            case "update": {
                BookDto bookDto = BOOK_SERVICE.getBookById(Long.parseLong(options[1]));
                System.out.println("Please, enter isbn: ");
                bookDto.setIsbn(scanner.nextLine());
                System.out.println("Please, enter author: ");
                bookDto.setAuthor(scanner.nextLine());
                System.out.println("Please, enter title: ");
                bookDto.setTitle(scanner.nextLine());
                System.out.println("Please, enter cover: ");
                bookDto.setCover(BookDto.Cover.valueOf(scanner.nextLine()));
                System.out.println("Please, enter price: ");
                bookDto.setPrice(scanner.nextBigDecimal());
                BookDto updateBook = BOOK_SERVICE.updateBook(bookDto);
                System.out.println("Book was updated: ");
                PrintUtil.showBriefInfo(updateBook);
                break;
            }
            case "exit": {
                return;
            }
            default: {
                System.out.println("Invalid input! Please, try again!");
            }
        }
    }
}
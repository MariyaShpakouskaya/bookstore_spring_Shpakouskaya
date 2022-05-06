package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.BookServiceImpl;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.util.ConsoleReader;
import com.belhard.bookstore.util.PrintUtil;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final BookService BOOK_SERVICE = new BookServiceImpl();
    public static final String APPLICATION_FEATURES = "enter \"all\" to see all books\n" +
            "enter \"get id \" to find a book by id\n" +
            "enter \"delete id\" to delete book by id\n" +
            "enter \"create\" to add a book to the catalog\n" +
            "enter \"update id\" to change an existing book by id\n" +
            "enter \"exit\" to exit the program\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chooseMainMenuOption(scanner);
        scanner.close();
    }

    public static void chooseMainMenuOption(Scanner scanner) {
        System.out.println(APPLICATION_FEATURES);
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
                PrintUtil.showBriefInfo(BOOK_SERVICE.getBookById(Long.parseLong(options[1])));
                break;
            }
            case "delete": {
                BOOK_SERVICE.deleteBook(Long.parseLong(options[1]));
                System.out.println("Book has been removed!");
                break;
            }
            case "create": {
                BookDto createdBook = BOOK_SERVICE.createBook(ConsoleReader.readerForCreateBookDto(scanner));
                System.out.println("Book was created: ");
                PrintUtil.showBriefInfo(createdBook);
                break;
            }
            case "update": {
                BookDto bookDto = ConsoleReader.readerForUpdateBookDto
                        (scanner, BOOK_SERVICE.getBookById(Long.parseLong(options[1])));
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
package com.belhard.bookstore.controller;

import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.util.PrintUtil;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.BookDaoJdbcImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final BookDao BOOK_DAO = new BookDaoJdbcImpl();

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        chooseMainMenuOption(scanner);
        scanner.close();

        System.out.println("Count of all books is " + BOOK_DAO.countAllBooks());

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
                List<Book> books = BOOK_DAO.getAllBooks();
                for (Book book : books) {
                    PrintUtil.showBriefInfo(book);
                }
                break;
            }
            case "get": {
                Book book = BOOK_DAO.getBookById(Long.parseLong(options[1]));
                PrintUtil.showBriefInfo(book);
                break;
            }
            case "delete": {
                BOOK_DAO.deleteBook(Long.parseLong(options[1]));
                break;
            }
            case "create": {
                Book book = new Book();
                System.out.println("Please, enter isbn: ");
                book.setIsbn(scanner.nextLine());
                System.out.println("Please, enter author: ");
                book.setAuthor(scanner.nextLine());
                System.out.println("Please, enter title: ");
                book.setTitle(scanner.nextLine());
                System.out.println("Please, enter cover: ");
                book.setCover(Book.Cover.valueOf(scanner.nextLine()));
                System.out.println("Please, enter price: ");
                book.setPrice(scanner.nextBigDecimal());
                Book createdBook = BOOK_DAO.createBook(book);
                System.out.println("Book was created: ");
                PrintUtil.showBriefInfo(createdBook);
                break;
            }
            case "update": {
                Book book = BOOK_DAO.getBookById(Long.parseLong(options[1]));
                System.out.println("Please, enter isbn: ");
                book.setIsbn(scanner.nextLine());
                System.out.println("Please, enter author: ");
                book.setAuthor(scanner.nextLine());
                System.out.println("Please, enter title: ");
                book.setTitle(scanner.nextLine());
                System.out.println("Please, enter cover: ");
                book.setCover(Book.Cover.valueOf(scanner.nextLine()));
                System.out.println("Please, enter price: ");
                book.setPrice(scanner.nextBigDecimal());
                BOOK_DAO.updateBook(book);
                System.out.println("Book was updated: ");
                PrintUtil.showBriefInfo(book);
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
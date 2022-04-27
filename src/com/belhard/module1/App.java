package com.belhard.module1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static Connection connection;
    public static void main(String[] args) throws SQLException {
        initDbConnection();
        List<Book> books = getAllBooks();
        books.forEach(System.out::println);

        long id = 5;
        Book book = getBookById(id);
        System.out.println("User's book: ");
        System.out.println(book);
    }

    public static void initDbConnection() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/bookstore_bh";
        String user = "postgres";
        String password = "root";
        connection = DriverManager.getConnection(url, user, password);
    }

    public static List<Book> getAllBooks() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
            books.add(book);
        }
        return books;
    }

    public static Book getBookById(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id= ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        Book book = null;
        if (resultSet.next()) {
            book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
        }
        return book;
    }
}
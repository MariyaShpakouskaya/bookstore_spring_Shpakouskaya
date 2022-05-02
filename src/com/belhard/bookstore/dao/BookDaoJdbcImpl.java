package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.connection.DbConfigurator;
import com.belhard.bookstore.dao.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJdbcImpl implements BookDao {

    public static final String GET_ALL = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id";
    public static final String GET_BY_ID = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.id= ? AND deleted = false";
    public static final String GET_BY_ISBN = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.isbn= ? AND deleted = false";
    public static final String GET_BY_AUTHOR = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.author= ? AND deleted = false";
    public static final String INSERT = "INSERT INTO books (isbn, author, title, cover_id, price) VALUES (?,?,?,(SELECT id FROM covers WHERE name = ?), ?)";
    public static final String UPDATE = "UPDATE books SET isbn = ?, author = ?, title = ?, price = ?, cover_id = (SELECT id FROM covers WHERE name = ?) WHERE id = ? AND deleted = false";
    public static final String DELETE = "UPDATE books SET deleted = true WHERE id = ? AND deleted = false";
    public static final String COUNT_ALL = "SELECT COUNT(*) FROM books";

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = DbConfigurator.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                Book book = processResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Book processResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setAuthor(resultSet.getString("author"));
        book.setTitle(resultSet.getString("title"));
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setCover(Book.Cover.valueOf(resultSet.getString("cover")));
        return book;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = null;
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = null;
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_ISBN);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_AUTHOR);
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = processResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book createBook(Book book) {
        try {
            Connection connection = DbConfigurator.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getTitle());
            statement.setString(4, book.getCover().toString());
            statement.setBigDecimal(5, book.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return getBookById(generatedKeys.getLong("id"));
            } else {
                throw new RuntimeException("Something went wrong... ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("...");
    }

    @Override
    public Book updateBook(Book book) {
        try {
            Connection connection = DbConfigurator.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getTitle());
            statement.setBigDecimal(4, book.getPrice());
            statement.setString(5, book.getCover().toString());
            statement.setLong(6, book.getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return getBookById(generatedKeys.getLong("id"));
            } else {
                throw new RuntimeException("Something went wrong... ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("...");
    }

    @Override
    public boolean deleteBook(Long id) {
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(DELETE);
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("...");
    }

    @Override
    public int countAllBooks() {
        int counter = 0;
        try {
            Statement statement = DbConfigurator.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_ALL);
            if (resultSet.next()) {
                counter = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
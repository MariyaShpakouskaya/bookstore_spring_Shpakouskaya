package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.mapper.BookRowMapper;
import com.belhard.bookstore.dao.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("bookDao")
public class BookDaoJdbcImpl implements BookDao {

    public static final String GET_ALL = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id";
    public static final String GET_BY_ID = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.id = :id AND deleted = false";
    public static final String GET_BY_ISBN = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.isbn = :isbn AND deleted = false";
    public static final String GET_BY_AUTHOR = "SELECT b.id, b.isbn, b.author, b.title, b.price, c.name AS cover FROM books b JOIN covers c ON b.cover_id = c.id WHERE b.author = :author AND deleted = false";
    public static final String INSERT = "INSERT INTO books (isbn, author, title, cover_id, price) VALUES (:isbn, :author, :title, (SELECT id FROM covers WHERE name = :cover), :price)";
    public static final String UPDATE = "UPDATE books SET isbn = :isbn, author = :author, title = :title, price = :price, cover_id = (SELECT id FROM covers WHERE name = :cover) WHERE id = :id AND deleted = false";
    public static final String DELETE = "UPDATE books SET deleted = true WHERE id = :id AND deleted = false";
    public static final String COUNT_ALL = "SELECT COUNT(*) FROM books";
    static Logger logger = LogManager.getLogger();

    private final NamedParameterJdbcTemplate template;
    private final BookRowMapper rowMapper;

    @Autowired
    public BookDaoJdbcImpl(NamedParameterJdbcTemplate template, BookRowMapper rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return template.query(GET_ALL, rowMapper);
    }

    @Override
    public Book getBookById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(GET_BY_ID, params, rowMapper);
    }


    @Override
    public Book getBookByIsbn(String isbn) {
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", isbn);
        return template.queryForObject(GET_BY_ISBN, params, rowMapper);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        Map<String, Object> params = new HashMap<>();
        params.put("author", author);
        return template.query(GET_BY_AUTHOR, params, rowMapper);
    }

    @Override
    public Book createBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", book.getIsbn());
        params.put("author", book.getAuthor());
        params.put("title", book.getTitle());
        params.put("cover", book.getCover().toString().toLowerCase());
        params.put("price", book.getPrice());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(INSERT, source, keyHolder, new String[]{"id"});
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't create book!" + book);
        }
        Long id = Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                .orElseThrow(() -> new RuntimeException("Can't create book!"));
        return getBookById(id);
    }

    @Override
    public Book updateBook(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("isbn", book.getIsbn());
        params.put("author", book.getAuthor());
        params.put("title", book.getTitle());
        params.put("cover", book.getCover().toString().toLowerCase());
        params.put("price", book.getPrice());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(UPDATE, source);
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't update book!" + book);
        }
        return getBookById(book.getId());
    }

    @Override
    public boolean deleteBook(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.update(DELETE, params) == 1;
    }

    @Override
    public int countAllBooks() {
        return template.getJdbcOperations().update(COUNT_ALL);
    }
}
package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BookDaoJdbcImpl implements BookDao {

    public static final String GET_ALL = "from Book where deleted = false";
    public static final String GET_ALL_BY_AUTHOR = "from Book where author = ?";
    @PersistenceContext
    private final EntityManager manager;

    public BookDaoJdbcImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = manager.createQuery(GET_ALL, Book.class).getResultList();
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = manager.find(Book.class, id);
        return book;
    }


    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = manager.find(Book.class, isbn);
        return book;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = manager.createQuery(GET_ALL_BY_AUTHOR, Book.class).getResultList();
        return books;
    }

    @Override
    public Book createBook(Book book) {
        manager.persist(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        manager.merge(book);
        return book;
    }

    @Override
    public boolean deleteBook(Long id) {
        Book managed = manager.find(Book.class, id);
        boolean success = false;
        if (managed != null) {
            managed.setDeleted(true);
            manager.merge(managed);
            success = true;
        }
        return success;
    }

    @Override
    public int countAllBooks() {
        int count = (int) Long.parseLong(manager.createNativeQuery("SELECT COUNT(*) FROM books").toString());
        return count;
    }
}
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
public class BookDaoJdbcImpl implements BookDao {

    @PersistenceContext
    private final EntityManager manager;

    public BookDaoJdbcImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = manager.createQuery("from Book", Book.class).getResultList();
        manager.clear();
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = manager.find(Book.class, id);
        manager.clear();
        return book;
    }


    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = manager.find(Book.class, isbn);
        manager.clear();
        return book;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        for (Book book : books) {
            book = manager.find(Book.class, author);
            books.add(book);
        }
        return books;
    }

    @Transactional
    @Override
    public Book createBook(Book book) {
        manager.persist(book);
        manager.clear();
        return book;
    }

    @Transactional
    @Override
    public Book updateBook(Book book) {
        manager.merge(book);
        manager.flush();
        manager.clear();
        return book;
    }

    @Transactional
    @Override
    public boolean deleteBook(Long id) {
        Book managed = manager.find(Book.class, id);
        boolean success = false;
        if (managed != null) {
            manager.remove(managed);
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
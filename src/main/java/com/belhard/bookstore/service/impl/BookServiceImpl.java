package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("bookService")
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookServiceImpl() {
    }

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    private BookDto bookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setCover(BookDto.Cover.valueOf(book.getCover().toString().toUpperCase()));
        return bookDto;
    }

    private Book bookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setCover(Book.Cover.valueOf(bookDto.getCover().toString().toUpperCase()));
        return book;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookDao.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            BookDto bookDto = bookToBookDto(book);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public BookDto getBookById(Long id) {
        try {
            Book book = bookDao.getBookById(id);
            return bookToBookDto(book);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book bookToCreate = bookDtoToBook(bookDto);
        return bookToBookDto(bookDao.createBook(bookToCreate));
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookDao.getBookById(bookDto.getId());
        if (book != null && !Objects.equals(book.getId(), bookDto.getId())) {
            throw new RuntimeException("You can't update this book");
        }
        Book bookToUpdate = bookDtoToBook(bookDto);
        return bookToBookDto(bookDao.updateBook(bookToUpdate));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookDao.deleteBook(id)) {
            throw new RuntimeException("This book didn't deleted!");
        }
    }

    @Override
    public void countAllBooks() {
        System.out.println("Count of all books is " + bookDao.countAllBooks());
    }
}

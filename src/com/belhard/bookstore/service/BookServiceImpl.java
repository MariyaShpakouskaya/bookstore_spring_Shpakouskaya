package com.belhard.bookstore.service;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.BookDaoJdbcImpl;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.service.dto.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookServiceImpl implements BookService {

    private final BookDao BOOK_DAO = new BookDaoJdbcImpl();

    private BookDto bookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setCover(BookDto.Cover.valueOf(book.getCover().toString()));
        return bookDto;
    }

    private Book bookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setCover(Book.Cover.valueOf(bookDto.getCover().toString()));
        return book;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = BOOK_DAO.getAllBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            BookDto bookDto = bookToBookDto(book);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = BOOK_DAO.getBookById(id);
        return bookToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book existing = BOOK_DAO.getBookByIsbn(bookDto.getIsbn());
        if (existing != null) {
            throw new RuntimeException("This book is already exist!");
        }
        Book bookToCreate = bookDtoToBook(bookDto);
        return bookToBookDto(BOOK_DAO.createBook(bookToCreate));
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = BOOK_DAO.getBookByIsbn(bookDto.getIsbn());
        if (book != null && !Objects.equals(book.getId(), bookDto.getId())) {
            throw new RuntimeException("You can't update this book");
        }
        Book bookToUpdate = bookDtoToBook(bookDto);
        return bookToBookDto(BOOK_DAO.updateBook(bookToUpdate));
    }

    @Override
    public void deleteBook(Long id) {
        if (!BOOK_DAO.deleteBook(id)) {
            throw new RuntimeException("This book didn't deleted!");
        }
    }
}

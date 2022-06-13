package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.BookRepository;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl() {
    }

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto bookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setCover(BookDto.Cover.valueOf(book.getCover().toString().toUpperCase()));
        return bookDto;
    }

    public Book bookDtoToBook(BookDto bookDto) {
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
    public List<BookDto> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findBookByDeletedFalse(pageable);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            BookDto bookDto = bookToBookDto(book);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @Override
    public BookDto getBookById(Long id) {
            Book book = bookRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("No book with this id " + id));
            return bookToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book bookToCreate = bookDtoToBook(bookDto);
        return bookToBookDto(bookRepository.save(bookToCreate));
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookRepository.getById(bookDto.getId());
        if (book != null && !Objects.equals(book.getId(), bookDto.getId())) {
            throw new RuntimeException("You can't update this book");
        }
        Book bookToUpdate = bookDtoToBook(bookDto);
        return bookToBookDto(bookRepository.save(bookToUpdate));
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.getById(id);
        book.setDeleted(true);
        bookRepository.save(book);
    }

    @Override
    public int countAllBooks() {
        return (int) bookRepository.countBooksByDeletedFalse();
    }
}

package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto);

    void deleteBook(Long id);

    void countAllBooks();
}

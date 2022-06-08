package com.belhard.bookstore.service;

import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.service.dto.BookDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto);

    void deleteBook(Long id);

    int countAllBooks();

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDto);

}

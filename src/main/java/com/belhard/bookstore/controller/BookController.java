package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/books")
public class BookController {
    public static final int SIZE_OF_PAGE = 10;
    public static final String SORT_COLUMN = "id";
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBooks(Model model, @RequestParam int page) {
        int quantityOfPages = (bookService.countAllBooks()) / SIZE_OF_PAGE;
        System.out.println(bookService.countAllBooks());
        Pageable pageable = PageRequest.of(page - 1, SIZE_OF_PAGE, Sort.Direction.ASC, SORT_COLUMN);
        List<BookDto> bookDtos = bookService.getAllBooks(pageable);
        model.addAttribute("books", bookDtos);
        model.addAttribute("page", page);
        model.addAttribute("pages", quantityOfPages);
        return "books";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        return "book";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Model model, @RequestParam Map<String, Object> params) {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(params.get("isbn").toString());
        bookDto.setAuthor(params.get("author").toString());
        bookDto.setTitle(params.get("title").toString());
        bookDto.setCover(BookDto.Cover.valueOf(params.get("cover").toString().toUpperCase()));
        bookDto.setPrice(BigDecimal.valueOf(Double.parseDouble(params.get("price").toString())));
        BookDto created = bookService.createBook(bookDto);
        System.out.println(created.toString());
        model.addAttribute("book", created);
        return "book";
    }

    @GetMapping("/create")
    public String createForm() {
        return "createBook";
    }

    @PostMapping("/{id}")
    public String update(Model model, @PathVariable Long id, @RequestParam Map<String, Object> params) {
        BookDto bookDto = bookService.getBookById(id);
        bookDto.setId(id);
        bookDto.setIsbn(params.get("isbn").toString());
        bookDto.setAuthor(params.get("author").toString());
        bookDto.setTitle(params.get("title").toString());
        bookDto.setCover(BookDto.Cover.valueOf(params.get("cover").toString().toUpperCase()));
        bookDto.setPrice(BigDecimal.valueOf(Double.parseDouble(params.get("price").toString().toUpperCase())));
        BookDto updated = bookService.updateBook(bookDto);
        model.addAttribute("book", updated);
        return "book";
    }

    @PostMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        bookService.deleteBook(id);
        model.addAttribute("message", "Book with id " + id + " deleted");
        return "deleted";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        return "editBook";
    }

}

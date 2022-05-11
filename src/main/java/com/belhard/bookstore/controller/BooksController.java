package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.BookServiceImpl;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/books")
public class BooksController extends HttpServlet {
    private static final BookService BOOK_SERVICE = new BookServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        List<BookDto> bookDtos = BOOK_SERVICE.getAllBooks();
        out.write(("<style>*{text-align: center; color: Crimson; background-color: #00FA9A;font-size: 20px}</style>"));
        out.write(("<h1>BOOKS</h1>"));
        out.write(("<table id=\"t1\" width=\"100%\">"));
        out.write("<tr><th>ID</th><th>ISBN</th><th>TITLE</th><th>AUTHOR</th><th>COVER</th><th>PRICE</th>");
        for (BookDto bookDto : bookDtos) {
            out.write("<tr><td>" + bookDto.getId() + "</td>");
            out.write("<td>" + bookDto.getIsbn() + "</td>");
            out.write("<td>" + bookDto.getTitle() + "</td>");
            out.write("<td>" + bookDto.getAuthor() + "</td>");
            out.write("<td>" + bookDto.getCover().toString().toLowerCase() + "</td>");
            out.write("<td>" + bookDto.getPrice() + "</td></tr>");
        }
        out.write("</table>");
    }
}

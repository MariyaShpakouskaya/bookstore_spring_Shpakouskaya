package com.belhard.bookstore.dao.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private Long id;
    private String isbn;
    private String author;
    private String title;
    private Cover cover;
    private BigDecimal price;

    public Book() {
    }

    public Book(String isbn, String author, String title, BigDecimal price, Cover cover) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.price = price;
        this.cover = cover;
    }

    public enum Cover {
        SOFT,
        HARD,
        SPECIAL
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id)
                && Objects.equals(isbn, book.isbn)
                && Objects.equals(author, book.author)
                && Objects.equals(title, book.title)
                && cover == book.cover
                && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, author, title, cover, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", cover=" + cover +
                ", price=" + price +
                '}';
    }
}

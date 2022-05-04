package com.belhard.bookstore.service;

import java.math.BigDecimal;
import java.util.Objects;

public class BookDto {
    private Long id;
    private String isbn;
    private String author;
    private String title;
    private Cover cover;
    private BigDecimal price;

    public BookDto() {
    }

    public BookDto(String isbn, String author, String title, Cover cover, BigDecimal price) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.price = price;
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

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(isbn, bookDto.isbn) && Objects.equals(author, bookDto.author) && Objects.equals(title, bookDto.title) && cover == bookDto.cover && Objects.equals(price, bookDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, author, title, cover, price);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", cover=" + cover +
                ", price=" + price +
                '}';
    }
}

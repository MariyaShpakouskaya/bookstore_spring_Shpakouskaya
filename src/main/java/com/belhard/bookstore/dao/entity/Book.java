package com.belhard.bookstore.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isbn", nullable = true, length = 50, unique = true)
    private String isbn;

    @Column(name = "author", nullable = true, length = 100)
    private String author;

    @Column(name = "title", nullable = true, length = 100)
    private String title;

    @Enumerated
    @Column(name = "cover_id")
    private Cover cover;

    @Column(name = "price", columnDefinition = "decimal(8,2) default 0.0", nullable = true)
    private BigDecimal price;

    @Column(name = "deleted")
    private boolean deleted = false;

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
        NO_COVER,
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return deleted == book.deleted && Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && cover == book.cover && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, author, title, cover, price, deleted);
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

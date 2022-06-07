package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findBookByDeletedFalse (Pageable pageable);

}

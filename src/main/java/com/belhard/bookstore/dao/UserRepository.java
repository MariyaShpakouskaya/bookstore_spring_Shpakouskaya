package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findUsersByDeletedFalse(Pageable pageable);

    User findUserByLastName(String lastName);

    User findUserByEmail(String email);

    long countUsersByDeletedFalse();
}

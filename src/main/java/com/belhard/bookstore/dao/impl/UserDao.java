package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByLastName(String lastName);

    User getUserByEmail(String email);

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUser(Long id);

    int countAllUsers();


}

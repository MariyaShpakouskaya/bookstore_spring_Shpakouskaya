package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    @PersistenceContext
    private final EntityManager manager;

    public UserDaoJdbcImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = manager.createQuery("from User", User.class).getResultList();
        manager.clear();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = manager.find(User.class, id);
        manager.clear();
        return user;
    }

    @Override
    public User getUserByLastName(String lastName) {
        User user = manager.find(User.class, lastName);
        manager.clear();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = manager.find(User.class, email);
        manager.clear();
        return user;
    }

    @Transactional
    @Override
    public User createUser(User user) {
        manager.persist(user);
        manager.clear();
        return user;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        manager.merge(user);
        manager.flush();
        manager.clear();
        return user;
    }

    @Transactional
    @Override
    public boolean deleteUser(Long id) {
        User managed = manager.find(User.class, id);
        boolean success = false;
        if (managed != null) {
            manager.remove(managed);
            success = true;
        }
        return success;
    }

    @Override
    public int countAllUsers() {
        int count = (int) Long.parseLong(manager.createNativeQuery("SELECT COUNT(*) FROM users").toString());
        return count;
    }
}

package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoJdbcImpl implements UserDao {

    public static final String GET_ALL = "from User where deleted = false";
    @PersistenceContext
    private final EntityManager manager;

    public UserDaoJdbcImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = manager.createQuery(GET_ALL, User.class).getResultList();
        manager.clear();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = manager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByLastName(String lastName) {
        User user = manager.find(User.class, lastName);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = manager.find(User.class, email);
        return user;
    }

    @Override
    public User createUser(User user) {
        manager.persist(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        manager.merge(user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        User managed = manager.find(User.class, id);
        boolean success = false;
        if (managed != null) {
            managed.setDeleted(true);
            manager.merge(managed);
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

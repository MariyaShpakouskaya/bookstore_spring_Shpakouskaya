package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("userDao")
public class UserDaoJdbcImpl implements UserDao {

    public static final String GET_ALL_USERS = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id";
    public static final String GET_BY_USER_ID = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id= :id AND deleted = false";
    public static final String GET_BY_USER_LASTNAME = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.last_name = :last_name AND deleted = false";
    public static final String GET_BY_USER_EMAIL = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = :email AND deleted = false";
    public static final String INSERT_USER = "INSERT INTO users (first_name, last_name, email, role_id, password) VALUES (:first_name,:last_name,:email,(SELECT id FROM roles WHERE name = :role), :password)";
    public static final String UPDATE_USER = "UPDATE users SET first_name = :first_name, last_name = :last_name, email = :email, role_id = (SELECT id FROM roles WHERE name = :role), password = :password WHERE id = :id AND deleted = false";
    public static final String DELETE_USER = "UPDATE users SET deleted = true WHERE id = :id AND deleted = false";
    public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM users";
    static Logger logger = LogManager.getLogger();
    private final NamedParameterJdbcTemplate template;
    private final UserRowMapper rowMapper;

    @Autowired
    public UserDaoJdbcImpl(NamedParameterJdbcTemplate template, UserRowMapper rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return template.query(GET_ALL_USERS, rowMapper);
    }

    @Override
    public User getUserById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(GET_BY_USER_ID, params, rowMapper);
    }

    @Override
    public User getUserByLastName(String lastName) {
        Map<String, Object> params = new HashMap<>();
        params.put("last_name", lastName);
        return template.queryForObject(GET_BY_USER_LASTNAME, params, rowMapper);
    }

    @Override
    public User getUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return template.queryForObject(GET_BY_USER_EMAIL, params, rowMapper);
    }

    @Override
    public User createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", user.getFirstName());
        params.put("last_name", user.getLastName());
        params.put("email", user.getEmail());
        params.put("role", user.getRole().toString().toLowerCase());
        params.put("password", user.getPassword());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(INSERT_USER, source, keyHolder, new String[]{"id"});
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't create user!" + user);
        }
        Long id = Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                .orElseThrow(() -> new RuntimeException("Can't create user!"));
        return getUserById(id);
    }

    @Override
    public User updateUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", user.getFirstName());
        params.put("last_name", user.getLastName());
        params.put("email", user.getEmail());
        params.put("role", user.getRole().toString().toLowerCase());
        params.put("password", user.getPassword());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(INSERT_USER, source);
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't update user!" + user);
        }
        return getUserById(user.getId());
    }

    @Override
    public boolean deleteUser(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.update(DELETE_USER, params) == 1;
    }

    @Override
    public int countAllUsers() {
        return template.getJdbcOperations().update(COUNT_ALL_USERS);
    }
}

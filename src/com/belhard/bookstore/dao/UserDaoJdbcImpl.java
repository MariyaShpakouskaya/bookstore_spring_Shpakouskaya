package com.belhard.bookstore.dao;

import com.belhard.bookstore.connection.DbConfigurator;
import com.belhard.bookstore.dao.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public static final String GET_ALL_USERS = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id";
    public static final String GET_BY_USER_ID = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id= ? AND deleted = false";
    public static final String GET_BY_USER_LASTNAME = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.last_name = ? AND deleted = false";
    public static final String GET_BY_USER_EMAIL = "SELECT u.id, u.first_name, u.last_name, u.email, u.password, r.name AS role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ? AND deleted = false";
    public static final String INSERT_USER = "INSERT INTO users (first_name, last_name, email, role_id, password) VALUES (?,?,?,(SELECT id FROM roles WHERE name = ?), ?)";
    public static final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, email = ?, role_id = (SELECT id FROM roles WHERE name = ?), password = ? WHERE id = ? AND deleted = false";
    public static final String DELETE_USER = "UPDATE users SET deleted = true WHERE id = ? AND deleted = false";
    public static final String COUNT_ALL_USERS = "SELECT COUNT(*) FROM users";


    private User processResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(User.Role.valueOf(resultSet.getString("role")));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = DbConfigurator.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                User user = processResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_USER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLastName(String lastName) {
        User user = null;
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_USER_LASTNAME);
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(GET_BY_USER_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = processResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        try {
            Connection connection = DbConfigurator.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return getUserById(generatedKeys.getLong("id"));
            } else {
                throw new RuntimeException("Something went wrong... ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something went wrong... ");
    }

    @Override
    public User updateUser(User user) {
        try {
            Connection connection = DbConfigurator.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return getUserById(generatedKeys.getLong("id"));
            } else {
                throw new RuntimeException("Something went wrong... ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something went wrong... ");
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            PreparedStatement statement = DbConfigurator.getConnection().prepareStatement(DELETE_USER);
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Something went wrong... ");
    }

    @Override
    public int countAllUsers() {
        int counter = 0;
        try {
            Statement statement = DbConfigurator.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_ALL_USERS);
            if (resultSet.next()) {
                counter = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
}

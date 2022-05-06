package com.belhard.bookstore.service;

import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.UserDaoJdbcImpl;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private final UserDao USER_DAO = new UserDaoJdbcImpl();

    private UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(UserDto.Role.valueOf(user.getRole().toString()));
        return userDto;
    }

    private User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(User.Role.valueOf(userDto.getRole().toString()));
        return user;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = USER_DAO.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = userToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = USER_DAO.getUserById(id);
        return userToUserDto(user);
    }

    @Override
    public UserDto getUserByLastName(String lastName) {
        User user = USER_DAO.getUserByLastName(lastName);
        return userToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = USER_DAO.getUserByEmail(email);
        return userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User existing = USER_DAO.getUserByEmail(userDto.getEmail());
        if (existing != null) {
            throw new RuntimeException("This user is already exist!");
        }
        User userToCreate = userDtoToUser(userDto);
        return userToUserDto(USER_DAO.createUser(userToCreate));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = USER_DAO.getUserByLastName(userDto.getLastName());
        if (user != null && !Objects.equals(user.getId(), userDto.getId())) {
            throw new RuntimeException("You can't update this user!");
        }
        User userToUpdate = userDtoToUser(userDto);
        return userToUserDto(USER_DAO.updateUser(userToUpdate));
    }

    @Override
    public void deleteUser(Long id) {
        if (!USER_DAO.deleteUser(id)) {
            throw new RuntimeException("This user didn't deleted!");
        }
    }

    @Override
    public void countAllUsers() {
        System.out.println("Count of all users is " + USER_DAO.countAllUsers());
    }
}

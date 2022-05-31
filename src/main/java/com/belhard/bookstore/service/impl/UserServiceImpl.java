package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

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
        List<User> users = userDao.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = userToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Long id) {
        try {
            User user = userDao.getUserById(id);
            return userToUserDto(user);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDto getUserByLastName(String lastName) {
        User user = userDao.getUserByLastName(lastName);
        return userToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        return userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User userToCreate = userDtoToUser(userDto);
        return userToUserDto(userDao.createUser(userToCreate));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userDao.getUserById(userDto.getId());
        if (user != null && !Objects.equals(user.getId(), userDto.getId())) {
            throw new RuntimeException("You can't update this user!");
        }
        User userToUpdate = userDtoToUser(userDto);
        return userToUserDto(userDao.updateUser(userToUpdate));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userDao.deleteUser(id)) {
            throw new RuntimeException("This user didn't deleted!");
        }
    }

    @Override
    public void countAllUsers() {
        System.out.println("Count of all users is " + userDao.countAllUsers());
    }
}

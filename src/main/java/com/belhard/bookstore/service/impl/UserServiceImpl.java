package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.UserRepository;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(UserDto.Role.valueOf(user.getRole().toString()));
        return userDto;
    }

    public User userDtoToUser(UserDto userDto) {
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
    public List<UserDto> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findUsersByDeletedFalse(pageable);
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
            User user = userRepository.getById(id);
            return userToUserDto(user);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDto getUserByLastName(String lastName) {
        User user = userRepository.findUserByLastName(lastName);
        return userToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User userToCreate = userDtoToUser(userDto);
        return userToUserDto(userRepository.save(userToCreate));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.getById(userDto.getId());
        if (user != null && !Objects.equals(user.getId(), userDto.getId())) {
            throw new RuntimeException("You can't update this user!");
        }
        User userToUpdate = userDtoToUser(userDto);
        return userToUserDto(userRepository.save(userToUpdate));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.getById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public int countAllUsers() {
        return (int) userRepository.count();
    }
}

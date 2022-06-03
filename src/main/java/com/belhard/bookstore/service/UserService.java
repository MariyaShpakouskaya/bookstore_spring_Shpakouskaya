package com.belhard.bookstore.service;

import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto getUserByLastName(String lastName);

    UserDto getUserByEmail(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);

    void countAllUsers();

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

}

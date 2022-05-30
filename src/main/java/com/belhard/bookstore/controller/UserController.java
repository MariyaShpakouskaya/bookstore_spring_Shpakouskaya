package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("users", userDtos);
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "user";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(Model model, @RequestParam Map<String, Object> params) {
        UserDto userDto = new UserDto();
        userDto.setEmail(params.get("email").toString());
        userDto.setFirstName(params.get("first_name").toString());
        userDto.setLastName(params.get("last_name").toString());
        userDto.setRole(UserDto.Role.valueOf(params.get("role").toString().toUpperCase()));
        userDto.setPassword(params.get("password").toString());
        UserDto created = userService.createUser(userDto);
        System.out.println(created.toString());
        model.addAttribute("user", created);
        return "user";
    }

    @GetMapping("/create")
    public String createForm() {
        return "createUser";
    }

    @PostMapping("/{id}")
    public String update(Model model, @PathVariable Long id, @RequestParam Map<String, Object> params) {
        UserDto userDto = userService.getUserById(id);
        userDto.setId(id);
        userDto.setEmail(params.get("email").toString());
        userDto.setFirstName(params.get("first_name").toString());
        userDto.setLastName(params.get("last_name").toString());
        userDto.setRole(UserDto.Role.valueOf(params.get("role").toString().toUpperCase()));
        userDto.setPassword(params.get("password").toString());
        UserDto updated = userService.updateUser(userDto);
        model.addAttribute("user", updated);
        return "user";
    }

    @PostMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        userService.deleteUser(id);
        model.addAttribute("message", "User with id " + id + " deleted");
        return "deleted";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "editUser";
    }

}
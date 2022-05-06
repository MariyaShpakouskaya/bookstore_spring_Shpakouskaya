package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.UserServiceImpl;
import com.belhard.bookstore.service.dto.UserDto;
import com.belhard.bookstore.util.ConsoleReader;
import com.belhard.bookstore.util.PrintUtil;

import java.util.List;
import java.util.Scanner;

public class UserApp {

    private static final UserService USER_SERVICE = new UserServiceImpl();
    public static final String APPLICATION_FEATURES = "enter \"all\" to see all users\n" +
            "enter \"id\" to find a user by id\n" +
            "enter \"lastname\" to find a user by last name\n" +
            "enter \"email\" to find a user by email\n" +
            "enter \"create\" to add a user to the base\n" +
            "enter \"update {id}\" to change an existing user by id\n" +
            "enter \"delete {id}\" to delete user by id\n" +
            "enter \"exit\" to exit the program\n" +
            "enter \"count\" to count all users in base\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chooseMainMenuOption(scanner);
        scanner.close();
    }

    public static void chooseMainMenuOption(Scanner scanner) {
        System.out.println(APPLICATION_FEATURES);
        String input = scanner.nextLine();
        String[] options = input.split(" ");
        String command = options[0];
        switch (command) {
            case "all": {
                List<UserDto> userDtos = USER_SERVICE.getAllUsers();
                for (UserDto userDto : userDtos) {
                    PrintUtil.showBriefInfo(userDto);
                }
                break;
            }
            case "id": {
                System.out.print("Enter id: ");
                PrintUtil.showBriefInfo(USER_SERVICE.getUserById(scanner.nextLong()));
                break;
            }
            case "lastname": {
                System.out.print("Enter lastname: ");
                PrintUtil.showBriefInfo(USER_SERVICE.getUserByLastName(scanner.next()));
                break;
            }
            case "email": {
                System.out.print("Enter email: ");
                PrintUtil.showBriefInfo(USER_SERVICE.getUserByEmail(scanner.next()));
                break;
            }
            case "create": {
                UserDto createdUser = USER_SERVICE.createUser(ConsoleReader.readerForCreateUserDto(scanner));
                System.out.println("User was created: ");
                PrintUtil.showBriefInfo(createdUser);
                break;
            }
            case "update": {
                UserDto userDto = ConsoleReader.readerForUpdateUserDto
                        (scanner, USER_SERVICE.getUserById(Long.parseLong(options[1])));
                UserDto updateUser = USER_SERVICE.updateUser(userDto);
                System.out.println("User was updated: ");
                PrintUtil.showBriefInfo(updateUser);
                break;
            }
            case "delete": {
                USER_SERVICE.deleteUser(Long.parseLong(options[1]));
                System.out.println("User has been removed!");
                break;
            }
            case "count": {
                USER_SERVICE.countAllUsers();
                break;
            }
            case "exit": {
                return;
            }
            default: {
                System.out.println("Invalid input! Please, try again!");
            }
        }
    }
}

package com.belhard.bookstore.controller.command;

import com.belhard.bookstore.controller.command.impl.BookCommand;
import com.belhard.bookstore.controller.command.impl.BooksCommand;
import com.belhard.bookstore.controller.command.impl.ErrorCommand;
import com.belhard.bookstore.controller.command.impl.UserCommand;
import com.belhard.bookstore.controller.command.impl.UsersCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> register = new HashMap<>();

    private static class Holder {
        private static final CommandFactory instance = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return Holder.instance;
    }

    private CommandFactory() {
    }

    static {
        register.put("book", new BookCommand());
        register.put("books", new BooksCommand());
        register.put("error", new ErrorCommand());
        register.put("user", new UserCommand());
        register.put("users", new UsersCommand());
    }

    public Command getCommand(String action) {
        Command command = register.get(action);
        if (command == null) {
            return register.get("error");
        }
        return command;
    }
}
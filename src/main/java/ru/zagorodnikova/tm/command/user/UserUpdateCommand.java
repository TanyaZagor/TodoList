package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserUpdateCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

    public UserUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "update user";
    }

    @Override
    public String description() {
        return "command to update user";
    }

    @Override
    public void execute() {
        System.out.println("New first name");
        String firstName = in.nextLine();
        System.out.println("New last name");
        String lastName = in.nextLine();
        System.out.println("New email");
        String email = in.nextLine();
        getBootstrap().getUserService().update(firstName, lastName, email);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

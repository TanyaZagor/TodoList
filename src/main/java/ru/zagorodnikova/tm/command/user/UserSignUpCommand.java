package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserSignUpCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

    public UserSignUpCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "sign up";
    }

    @Override
    public String description() {
        return "command to sign up";
    }

    @Override
    public void execute() {
        System.out.println("Login");
        String login = in.nextLine();
        System.out.println("Password");
        String password = in.nextLine();
        System.out.println("New first name");
        String firstName = in.nextLine();
        System.out.println("New last name");
        String lastName = in.nextLine();
        System.out.println("New email");
        String email = in.nextLine();
        getBootstrap().getUserService().signUp(login, password, firstName, lastName, email);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}

package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserSignInCommand extends AbstractCommand {

    private final Scanner in = getBootstrap().getScanner();

    public UserSignInCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "sign in";
    }

    @Override
    public String description() {
        return "command to sign in";
    }

    @Override
    public void execute() {
        System.out.println("Login");
        String login = in.nextLine();
        System.out.println("Password");
        String password = in.nextLine();
        getBootstrap().getUserService().signIn(login, password);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}

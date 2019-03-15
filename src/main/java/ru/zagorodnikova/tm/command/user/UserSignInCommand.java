package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.User;

import java.util.Scanner;

public class UserSignInCommand extends AbstractCommand {

    public UserSignInCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "sign in";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sign in";
    }

    @Override
    public void execute() {
        System.out.println("Login");
        @NotNull final String login = getServiceLocator().getTerminalService().nextLine();
        System.out.println("Password");
        @NotNull final String password = getServiceLocator().getTerminalService().nextLine();
        @Nullable final User user = getServiceLocator().getUserService().signIn(login, password);
        if (user!= null) {
            getServiceLocator().setCurrentUser(user);
            System.out.println(user);
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}

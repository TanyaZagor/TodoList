package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.User;


public class UserShowCommand extends AbstractCommand {
    public UserShowCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "show user";
    }

    @NotNull
    @Override
    public String description() {
        return "command to show user data";
    }

    @Override
    public void execute() {
        @NotNull User user = getServiceLocator().getUserService().findUser(getServiceLocator().getSession());
        System.out.println("Login: " + user.getLogin() + " name: " + user.getFirstName() + " lastName: " + user.getLastName() + " email: " + user.getEmail());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

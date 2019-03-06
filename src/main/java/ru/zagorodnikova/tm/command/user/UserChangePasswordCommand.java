package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserChangePasswordCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public UserChangePasswordCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "change password";
    }

    @Override
    public String description() {
        return "command to change password";
    }

    @Override
    public void execute() {
        System.out.println("Login");
        String login = in.nextLine();
        System.out.println("Password");
        String password = in.nextLine();
        System.out.println("New password");
        String newPassword = in.nextLine();
        getServiceLocator().getUserService().changePassword(getServiceLocator().getCurrentUser().getId(), login, password, newPassword);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserUpdateCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public UserUpdateCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        getServiceLocator().getUserService().update(getServiceLocator().getCurrentUser().getId(), firstName, lastName, email);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

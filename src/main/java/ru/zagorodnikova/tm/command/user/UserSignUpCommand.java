package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.User;

import java.util.Scanner;

public class UserSignUpCommand extends AbstractCommand {

    private final Scanner in = getServiceLocator().getScanner();

    public UserSignUpCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        User user = getServiceLocator().getUserService().signUp(login, password, firstName, lastName, email, RoleType.USER);
        if (user != null) {
            getServiceLocator().setCurrentUser(user);
            System.out.println(user);
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}

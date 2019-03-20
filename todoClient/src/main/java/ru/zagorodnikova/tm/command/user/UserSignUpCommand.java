package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.User;

public class UserSignUpCommand extends AbstractCommand {

    public UserSignUpCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "sign up";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sign up";
    }

    @Override
    public void execute() {
        System.out.println("Login");
        @NotNull final String login = getServiceLocator().getTerminalService().nextLine();
        System.out.println("Password");
        @NotNull final String password = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New first name");
        @NotNull final String firstName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New last name");
        @NotNull final String lastName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New email");
        @NotNull final String email = getServiceLocator().getTerminalService().nextLine();
        @Nullable final Session session = getServiceLocator().getSessionService().signUp(login, password, firstName, lastName, email);
        if (session != null) {
            getServiceLocator().setSession(session);
            System.out.println("ok");
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}

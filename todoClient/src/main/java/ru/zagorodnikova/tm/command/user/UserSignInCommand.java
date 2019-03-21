package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.User;

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
    public void execute() throws Exception_Exception {
        System.out.println("Login");
        @NotNull final String login = getServiceLocator().getTerminalService().nextLine();
        System.out.println("Password");
        @NotNull final String password = getServiceLocator().getTerminalService().nextLine();
        @Nullable final Session session = getServiceLocator().getSessionService().signIn(login, password);
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

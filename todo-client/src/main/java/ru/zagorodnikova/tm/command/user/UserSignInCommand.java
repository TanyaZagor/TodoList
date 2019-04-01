package ru.zagorodnikova.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.Session;

import javax.inject.Inject;

public class UserSignInCommand extends AbstractCommand {

    private ServiceLocator serviceLocator;

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
        @NotNull final String login = serviceLocator.getTerminalService().nextLine();
        System.out.println("Password");
        @NotNull final String password = serviceLocator.getTerminalService().nextLine();
        @Nullable final Session session = serviceLocator.getSessionService().signIn(login, password);
        if (session != null) {
            getServiceLocator().setSession(session);
            System.out.println("ok");
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Inject
    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}

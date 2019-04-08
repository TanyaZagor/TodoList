package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Session;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

@Component
public class UserSignInCommand extends AbstractCommand {
    @Autowired
    private SessionEndpoint sessionService;

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private TerminalService terminalService;

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
    public void execute() throws Exception {
        System.out.println("Login");
        @NotNull final String login = terminalService.nextLine();
        System.out.println("Password");
        @NotNull final String password = terminalService.nextLine();
        @Nullable final Session session = sessionService.signIn(login, password);
        if (session != null) {
            serviceLocator.setSession(session);
            System.out.println("ok");
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }

}

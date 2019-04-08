package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;

@Component
public class UserSignOutCommand extends AbstractCommand {
    @Autowired
    private SessionEndpoint sessionService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "sign out";
    }

    @NotNull
    @Override
    public String description() {
        return "command to sign out";
    }

    @Override
    public void execute() throws Exception {
        sessionService.remove(serviceLocator.getSession());
        serviceLocator.setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

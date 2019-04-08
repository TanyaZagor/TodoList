package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;

@Component
public class UserRemoveCommand extends AbstractCommand {
    @Autowired
    private UserEndpoint userService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "remove user";
    }

    @NotNull
    @Override
    public String description() {
        return "command to remove user";
    }

    @Override
    public void execute() throws Exception {
        userService.removeUser(serviceLocator.getSession());
        serviceLocator.setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

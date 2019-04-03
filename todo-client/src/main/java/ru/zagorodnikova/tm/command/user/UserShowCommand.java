package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.UserDto;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;


public class UserShowCommand extends AbstractCommand {
    @Inject
    private UserEndpoint userService;

    @Inject
    private ServiceLocator serviceLocator;

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
    public void execute() throws Exception {
        @NotNull UserDto user = userService.findUser(serviceLocator.getSession());
        System.out.println("Login: " + user.getLogin() + ", name: " + user.getFirstName() +
                ", lastName: " + user.getLastName() + ", email: " + user.getEmail() +
                ", status: " + user.getRoleType().toString());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

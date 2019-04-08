package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.UserDto;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;

@Component
public class UserShowCommand extends AbstractCommand {
    @Autowired
    private UserEndpoint userService;

    @Autowired
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

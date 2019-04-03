package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;

public class UserUpdateCommand extends AbstractCommand {

    @Inject
    private UserEndpoint userService;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TerminalService terminalService;

    @NotNull
    @Override
    public String command() {
        return "update user";
    }

    @NotNull
    @Override
    public String description() {
        return "command to update user";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("New first name");
        @NotNull final String firstName = terminalService.nextLine();
        System.out.println("New last name");
        @NotNull final String lastName = terminalService.nextLine();
        System.out.println("New email");
        @NotNull final String email = terminalService.nextLine();
        userService.updateUser(serviceLocator.getSession(), firstName, lastName, email);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

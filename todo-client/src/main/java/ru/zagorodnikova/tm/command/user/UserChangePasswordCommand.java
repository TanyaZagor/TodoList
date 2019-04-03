package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.inject.Inject;

public class UserChangePasswordCommand extends AbstractCommand {

    @Inject
    private UserEndpoint userService;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TerminalService terminalService;

    @NotNull
    @Override
    public String command() {
        return "change password";
    }

    @NotNull
    @Override
    public String description() {
        return "command to change password";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Login");
        @NotNull final String login = terminalService.nextLine();
        System.out.println("Password");
        @NotNull final String password = terminalService.nextLine();
        System.out.println("New password");
        @NotNull final String newPassword = terminalService.nextLine();
        userService.changePassword(serviceLocator.getSession(), login, password, newPassword);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

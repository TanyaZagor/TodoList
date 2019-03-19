package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserChangePasswordCommand extends AbstractCommand {

    public UserChangePasswordCommand() {
    }

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
    public void execute() {
        System.out.println("Login");
        @NotNull final String login = getServiceLocator().getTerminalService().nextLine();
        System.out.println("Password");
        @NotNull final String password = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New password");
        @NotNull final String newPassword = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getUserService().changePassword(getServiceLocator().getSession().getUserId(), login, password, newPassword);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

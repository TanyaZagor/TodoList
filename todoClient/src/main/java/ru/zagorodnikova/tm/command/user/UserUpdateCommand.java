package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserUpdateCommand extends AbstractCommand {

    public UserUpdateCommand() {
    }

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
    public void execute() {
        System.out.println("New first name");
        @NotNull final String firstName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New last name");
        @NotNull final String lastName = getServiceLocator().getTerminalService().nextLine();
        System.out.println("New email");
        @NotNull final String email = getServiceLocator().getTerminalService().nextLine();
        getServiceLocator().getUserService().updateUser(getServiceLocator().getCurrentUser().getId(), firstName, lastName, email);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

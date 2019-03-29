package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;
import ru.zagorodnikova.tm.endpoint.UserDto;

import java.util.List;

public class UserListCommand extends AbstractCommand {
    public UserListCommand() {
    }

    @NotNull
    @Override
    public String command() {
        return "user list";
    }

    @NotNull
    @Override
    public String description() {
        return "command to show all users";
    }

    @Override
    public void execute() throws Exception_Exception {
        @Nullable final List<UserDto> list = getServiceLocator().getUserService().findAllUsers(getServiceLocator().getSession());
        if (!(list == null || list.isEmpty())) {
            list.forEach(user -> System.out.println(user.getLogin()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

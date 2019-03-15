package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.User;

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
    public void execute() {
        @Nullable final List<User> list = getServiceLocator().getUserService().findAll(getServiceLocator().getCurrentUser().getRoleType());
        if (!(list == null || list.isEmpty())) {
            list.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

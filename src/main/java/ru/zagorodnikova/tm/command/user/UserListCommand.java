package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;

import java.util.List;

public class UserListCommand extends AbstractCommand {
    public UserListCommand() {
    }

    @Override
    public String command() {
        return "user list";
    }

    @Override
    public String description() {
        return "command to show all users";
    }

    @Override
    public void execute() {
        List<AbstractEntity> list = getServiceLocator().getUserService().findAll(getServiceLocator().getCurrentUser().getRoleType());
        if (!(list == null || list.isEmpty())) {
            list.forEach(System.out::println);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

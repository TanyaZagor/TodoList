package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserRemoveCommand extends AbstractCommand {
    public UserRemoveCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "remove user";
    }

    @Override
    public String description() {
        return "command to remove user";
    }

    @Override
    public void execute() {
        getServiceLocator().getUserService().remove(getServiceLocator().getCurrentUser().getId());
        getServiceLocator().setCurrentUser(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

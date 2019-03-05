package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserSignOutCommand extends AbstractCommand {
    public UserSignOutCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "sign out";
    }

    @Override
    public String description() {
        return "command to sign out";
    }

    @Override
    public void execute() {
        getBootstrap().getUserService().signOut();
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

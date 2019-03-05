package ru.zagorodnikova.tm.command.user;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class UserShowCommand extends AbstractCommand {
    public UserShowCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "show user";
    }

    @Override
    public String description() {
        return "command to show user data";
    }

    @Override
    public void execute() {
        System.out.println(getBootstrap().getCurrentUser());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

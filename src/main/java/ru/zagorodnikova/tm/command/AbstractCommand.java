package ru.zagorodnikova.tm.command;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;

public abstract class AbstractCommand {

    private Bootstrap bootstrap;

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    abstract public String command();
    abstract public String description();
    abstract public void execute();

    abstract public boolean isSecure();
}

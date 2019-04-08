package ru.zagorodnikova.tm.command;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommand {

    @NotNull
    abstract public String command();

    @NotNull
    abstract public String description();

    abstract public void execute() throws Exception;

    abstract public boolean isSecure();

}

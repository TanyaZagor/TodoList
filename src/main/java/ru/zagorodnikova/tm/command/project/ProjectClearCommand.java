package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.AbstractCommand;


public class ProjectClearCommand extends AbstractCommand {

    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "clear projects";
    }

    @Override
    public String description() {
        return "command to clear projects";
    }

    @Override
    public void execute() {
        getBootstrap().getProjectService().deleteAll();
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

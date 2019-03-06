package ru.zagorodnikova.tm.command.project;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;


public class ProjectClearCommand extends AbstractCommand {

    public ProjectClearCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        getServiceLocator().getProjectService().removeAll(getServiceLocator().getCurrentUser().getId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

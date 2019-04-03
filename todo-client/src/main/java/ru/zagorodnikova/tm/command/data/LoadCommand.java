package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.AdminEndpoint;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

import javax.inject.Inject;

public class LoadCommand extends AbstractCommand {

    @Inject
    private AdminEndpoint adminService;

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "load";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load";
    }

    @Override
    public void execute() throws Exception {
        adminService.load(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

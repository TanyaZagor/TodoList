package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.AdminEndpoint;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

import javax.inject.Inject;

public class SaveToJsonJaxbCommand extends AbstractCommand {
    @Inject
    private AdminEndpoint adminService;

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "save json jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save json jaxb";
    }

    @Override
    public void execute() throws Exception {
        adminService.saveToJsonJaxb(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

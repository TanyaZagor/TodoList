package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.AdminEndpoint;

@Component
public class LoadFromJsonJaxbCommand extends AbstractCommand {
    @Autowired
    private AdminEndpoint adminService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "load json jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load json jaxb";
    }

    @Override
    public void execute() throws Exception {
        adminService.loadFromJsonJaxb(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

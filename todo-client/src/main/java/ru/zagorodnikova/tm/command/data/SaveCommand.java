package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.AdminEndpoint;
import ru.zagorodnikova.tm.endpoint.Exception_Exception;

@Component
public class SaveCommand extends AbstractCommand {
    @Autowired
    private AdminEndpoint adminService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "save";
    }

    @NotNull
    @Override
    public String description() {
        return "command to save data";
    }

    @Override
    public void execute() throws Exception_Exception {
        adminService.save(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

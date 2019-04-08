package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.AdminEndpoint;

@Component
public class SaveToXmlJaxbCommand extends AbstractCommand {
    @Autowired
    private AdminEndpoint adminService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "save xml jaxb";
    }

    @NotNull
    @Override
    public String description() {
        return "save to xml jaxb";
    }

    @Override
    public void execute() throws Exception {
        adminService.saveToXmlJaxb(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

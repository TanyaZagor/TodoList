package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.api.service.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Map;

public interface ServiceLocator{

    @NotNull
    IUserService getUserService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    TerminalService getTerminalService();

    @NotNull
    User getCurrentUser();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    void setCurrentUser(User user);

    void execute(String command) throws IOException, JAXBException, ClassNotFoundException;
}

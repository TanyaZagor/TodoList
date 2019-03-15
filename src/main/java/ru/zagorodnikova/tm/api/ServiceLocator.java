package ru.zagorodnikova.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Map;

public interface ServiceLocator {

    @NotNull
    IUserService<User> getUserService();

    @NotNull
    IProjectService<Project> getProjectService();

    @NotNull
    ITaskService<Task> getTaskService();

    @NotNull
    TerminalService getTerminalService();

    @NotNull
    User getCurrentUser();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    void setCurrentUser(User user);

    void execute(String command) throws IOException, JAXBException, ClassNotFoundException;
}

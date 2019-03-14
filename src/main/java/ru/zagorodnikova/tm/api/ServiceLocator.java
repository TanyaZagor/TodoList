package ru.zagorodnikova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.AbstractEntity;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Map;

public interface ServiceLocator {

    @NotNull
    IUserService<AbstractEntity> getUserService();

    @NotNull
    IProjectService<AbstractEntity> getProjectService();

    @NotNull
    ITaskService<AbstractEntity> getTaskService();

    @NotNull
    TerminalService getTerminalService();

    @NotNull
    User getCurrentUser();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    void setCurrentUser(User user);

    void execute(String command) throws IOException, JAXBException, ClassNotFoundException;
}

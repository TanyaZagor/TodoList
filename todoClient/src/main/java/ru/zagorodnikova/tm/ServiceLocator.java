package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Map;

public interface ServiceLocator{

    @NotNull
    UserEndpoint getUserService();

    @NotNull
    ProjectEndpoint getProjectService();

    @NotNull
    TaskEndpoint getTaskService();

    @NotNull
    SessionEndpoint getSessionService();

    @NotNull
    TerminalService getTerminalService();

    @NotNull
    Session getSession();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    void setSession(Session session);

    void execute(String command) throws IOException, JAXBException, ClassNotFoundException;
}

package ru.zagorodnikova.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.*;
import ru.zagorodnikova.tm.service.TerminalService;
import java.lang.Exception;
import java.util.Map;

public interface ServiceLocator{

    void init(@NotNull Class[] commandClasses);

    @NotNull
    TerminalService getTerminalService();

    @Nullable
    Session getSession();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    void setSession(Session session);

    void execute(String command) throws Exception;
}

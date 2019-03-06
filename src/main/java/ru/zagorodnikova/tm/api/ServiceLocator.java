package ru.zagorodnikova.tm.api;

import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.User;

import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    Scanner getScanner();

    User getCurrentUser();

    Map<String, AbstractCommand> getCommands();

    void setCurrentUser(User user);
}

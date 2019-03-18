package ru.zagorodnikova.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

public interface ServiceLocator {

    @NotNull
    IUserService getUserService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();


    @NotNull
    User getCurrentUser();

    void setCurrentUser(User user);

    //void execute(String command) throws IOException, JAXBException, ClassNotFoundException;
}

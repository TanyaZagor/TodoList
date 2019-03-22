package ru.zagorodnikova.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.service.*;

import java.sql.Connection;

public interface ServiceLocator {

    @NotNull
    IUserService getUserService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    ISessionService getSessionService();

    @NotNull
    IDomainService getDomainService();

    @NotNull
    IAdminService getAdminService();

    @NotNull
    Connection getConnection() throws Exception;

}

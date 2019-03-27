package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IAdminService;
import ru.zagorodnikova.tm.api.service.IUserService;

public class AdminService implements IAdminService {

    @NotNull private final IUserService userService;

    public AdminService(@NotNull final ServiceLocator serviceLocator) {
        this.userService = serviceLocator.getUserService();
    }

    public void removeAllUsers() {
        userService.removeAll();
    }
}

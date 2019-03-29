package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.service.IAdminService;
import ru.zagorodnikova.tm.api.service.IUserService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@NoArgsConstructor
public class AdminService implements IAdminService {

    @Inject
    private IUserService userService;

    public void removeAllUsers() {
        userService.removeAll();
    }
}

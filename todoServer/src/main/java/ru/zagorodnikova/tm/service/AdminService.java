package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IAdminService;
import ru.zagorodnikova.tm.entity.User;

public class AdminService implements IAdminService {

    @NotNull private final IUserRepository<User> userRepository;

    public AdminService(@NotNull final IUserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public void removeAllUsers() {
        userRepository.removeAll();
    }
}

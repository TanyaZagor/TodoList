package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import java.rmi.AccessException;

public interface ISessionService {

    @Nullable
    Session persist(@NotNull User user);

    void remove(@NotNull Session session);

    void validate(Session session);
}

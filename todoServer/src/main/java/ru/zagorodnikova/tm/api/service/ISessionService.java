package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

public interface ISessionService {

    @Nullable
    Session persist(@NotNull User user);

    void remove(@NotNull Session session);

    boolean validate(Session session);
}

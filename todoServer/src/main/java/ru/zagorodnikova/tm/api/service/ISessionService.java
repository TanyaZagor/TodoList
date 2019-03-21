package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Session;
import ru.zagorodnikova.tm.entity.User;

import java.rmi.AccessException;

public interface ISessionService {

    @Nullable
    Session persist(@NotNull User user) throws Exception;

    void remove(@NotNull Session session) throws Exception;

    void validate(Session session) throws Exception;
}

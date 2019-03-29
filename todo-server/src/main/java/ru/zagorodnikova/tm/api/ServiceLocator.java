package ru.zagorodnikova.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.service.*;
import javax.persistence.EntityManagerFactory;

public interface ServiceLocator {

    void init() throws Exception;
}

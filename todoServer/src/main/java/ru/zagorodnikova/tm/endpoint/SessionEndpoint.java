package ru.zagorodnikova.tm.endpoint;


import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.zagorodnikova.tm.api.service.ISessionService")
public class SessionEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public SessionEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


}

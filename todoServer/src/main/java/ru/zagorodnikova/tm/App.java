package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.endpoint.ProjectEndpoint;
import ru.zagorodnikova.tm.endpoint.SessionEndpoint;
import ru.zagorodnikova.tm.endpoint.TaskEndpoint;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;

import javax.xml.ws.Endpoint;

public class App {

    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
        Endpoint.publish("http://localhost:8080/ProjectEndpoint", new ProjectEndpoint(bootstrap));
        Endpoint.publish("http://localhost:8080/TaskEndpoint", new TaskEndpoint(bootstrap));
        Endpoint.publish("http://localhost:8080/UserEndpoint", new UserEndpoint(bootstrap));
        Endpoint.publish("http://localhost:8080/SessionEndpoint", new SessionEndpoint(bootstrap));
    }
}

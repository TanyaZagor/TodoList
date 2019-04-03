package ru.zagorodnikova.tm.producer;

import ru.zagorodnikova.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class EndpointProducer {

    @Inject
    private ProjectEndpointService  projectEndpointService;

    @Inject
    private TaskEndpointService taskEndpointService;

    @Inject
    private UserEndpointService userEndpointService;

    @Inject
    private SessionEndpointService sessionEndpointService;

    @Inject
    private AdminEndpointService adminEndpointService;

    @Produces
    private TaskEndpoint getTaskEndpoint() {
        return taskEndpointService.getTaskEndpointPort();
    }

    @Produces
    private ProjectEndpoint getProjectEndpoint() {
        return projectEndpointService.getProjectEndpointPort();
    }

    @Produces
    private UserEndpoint getUserEndpoint() {
        return userEndpointService.getUserEndpointPort();
    }

    @Produces
    private SessionEndpoint getSessionEndpoint() {
        return sessionEndpointService.getSessionEndpointPort();
    }

    @Produces
    private AdminEndpoint getAdminEndpoint() {
        return adminEndpointService.getAdminEndpointPort();
    }
}

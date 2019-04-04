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
    public TaskEndpoint getTaskEndpoint() {
        return taskEndpointService.getTaskEndpointPort();
    }

    @Produces
    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpointService.getProjectEndpointPort();
    }

    @Produces
    public UserEndpoint getUserEndpoint() {
        return userEndpointService.getUserEndpointPort();
    }

    @Produces
    public SessionEndpoint getSessionEndpoint() {
        return sessionEndpointService.getSessionEndpointPort();
    }

    @Produces
    private AdminEndpoint getAdminEndpoint() {
        return adminEndpointService.getAdminEndpointPort();
    }
}

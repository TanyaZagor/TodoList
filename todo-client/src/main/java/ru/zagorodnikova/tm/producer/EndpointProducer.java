package ru.zagorodnikova.tm.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.endpoint.*;

@Component
public class EndpointProducer {

    @Autowired
    private ProjectEndpointService  projectEndpointService;

    @Autowired
    private TaskEndpointService taskEndpointService;

    @Autowired
    private UserEndpointService userEndpointService;

    @Autowired
    private SessionEndpointService sessionEndpointService;

    @Autowired
    private AdminEndpointService adminEndpointService;

    @Bean
    public TaskEndpoint getTaskEndpoint() {
        return taskEndpointService.getTaskEndpointPort();
    }

    @Bean
    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpointService.getProjectEndpointPort();
    }

    @Bean
    public UserEndpoint getUserEndpoint() {
        return userEndpointService.getUserEndpointPort();
    }

    @Bean
    public SessionEndpoint getSessionEndpoint() {
        return sessionEndpointService.getSessionEndpointPort();
    }

    @Bean
    private AdminEndpoint getAdminEndpoint() {
        return adminEndpointService.getAdminEndpointPort();
    }
}

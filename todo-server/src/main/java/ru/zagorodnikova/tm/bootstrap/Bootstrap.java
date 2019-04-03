package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.*;
import ru.zagorodnikova.tm.service.PropertyService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@Setter
@Getter
@Default
@NoArgsConstructor
@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @Inject
    private ISessionEndpoint sessionEndpoint;

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private IProjectEndpoint projectEndpoint;

    @Inject
    private ITaskEndpoint taskEndpoint;

    @Inject
    private IAdminEndpoint adminEndpoint;

    @Inject
    private PropertyService propertyService;

    public void init() throws Exception {
        initEndpoints();
    }

    private void initEndpoints() throws Exception {
        publishEndpoint(projectEndpoint);
        publishEndpoint(taskEndpoint);
        publishEndpoint(userEndpoint);
        publishEndpoint(sessionEndpoint);
        publishEndpoint(adminEndpoint);
    }

    private void publishEndpoint(Object object) throws Exception {
        Endpoint.publish("http://" + propertyService.getHost() + ":" + propertyService.getPort() + "/"+ object.getClass().getSimpleName(), object);
    }
}

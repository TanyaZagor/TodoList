package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.*;
import ru.zagorodnikova.tm.service.PropertyService;

import javax.xml.ws.Endpoint;

@Setter
@Getter
@NoArgsConstructor
@Component
public class Bootstrap implements ServiceLocator {

    @Autowired
    private ISessionEndpoint sessionEndpoint;

    @Autowired
    private IUserEndpoint userEndpoint;

    @Autowired
    private IProjectEndpoint projectEndpoint;

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private IAdminEndpoint adminEndpoint;

    @Autowired
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

package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.*;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.*;
import ru.zagorodnikova.tm.endpoint.*;
import ru.zagorodnikova.tm.entity.*;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.service.*;
import ru.zagorodnikova.tm.util.DatabaseUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

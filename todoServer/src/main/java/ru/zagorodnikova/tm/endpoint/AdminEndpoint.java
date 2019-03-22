package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IAdminEndpoint;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class AdminEndpoint implements IAdminEndpoint {

    @NotNull private final ServiceLocator serviceLocator;

    public AdminEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Override
    public void removeAllUsers(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getAdminService().removeAllUsers();
        }

    }

    @Override
    public void save(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().save();
        }
    }

    @Override
    public void load(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().load();
        }

    }

    @Override
    public void saveToJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToJson();
        }
    }

    @Override
    public void loadFromJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromJson();
        }

    }

    @Override
    public void saveToXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToXml();
        }
    }

    @Override
    public void loadFromXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromXml();
        }
    }

    @Override
    public void saveToJsonJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToJsonJaxb();
        }
    }

    @Override
    public void loadFromJsonJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromJsonJaxb();
        }
    }

    @Override
    public void saveToXmlJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToXmlJaxb();
        }
    }

    @Override
    public void loadFromXmlJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromXmlJaxb();
        }
    }

    private boolean checkRole(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getUserService().findOne(session.getUserId()).getRoleType() == RoleType.ADMIN;
    }
}

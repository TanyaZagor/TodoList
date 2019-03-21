package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IAdminEndpoint;
import ru.zagorodnikova.tm.entity.RoleType;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebService;

@WebService
public class AdminEndpoint implements IAdminEndpoint {

    @NotNull
    private ServiceLocator serviceLocator;

    public AdminEndpoint(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }


    @Override
    public void removeAllUsers(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getAdminService().removeAllUsers();
        }

    }

    @Override
    public void save(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().save();
        }
    }

    @Override
    public void load(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().load();
        }

    }

    @Override
    public void saveToJson(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToJson();
        }
    }

    @Override
    public void loadFromJson(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromJson();
        }

    }

    @Override
    public void saveToXml(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToXml();
        }
    }

    @Override
    public void loadFromXml(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromXml();
        }
    }

    @Override
    public void saveToJsonJaxb(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToJsonJaxb();
        }
    }

    @Override
    public void loadFromJsonJaxb(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromJsonJaxb();
        }
    }

    @Override
    public void saveToXmlJaxb(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().saveToXmlJaxb();
        }
    }

    @Override
    public void loadFromXmlJaxb(@NotNull Session session) {
        if (checkRole(session)) {
            serviceLocator.getDomainService().loadFromXmlJaxb();
        }
    }

    private boolean checkRole(@NotNull Session session) {
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getUserService().findOne(session.getUserId()).getRoleType() == RoleType.ADMIN;
    }
}

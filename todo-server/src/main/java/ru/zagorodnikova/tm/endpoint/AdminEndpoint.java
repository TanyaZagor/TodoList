package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.endpoint.IAdminEndpoint;
import ru.zagorodnikova.tm.api.service.IAdminService;
import ru.zagorodnikova.tm.api.service.IDomainService;
import ru.zagorodnikova.tm.api.service.ISessionService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.entity.Session;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class AdminEndpoint implements IAdminEndpoint {

    @Inject
    private IAdminService adminService;

    @Inject
    private IDomainService domainService;

    @Inject
    private ISessionService sessionService;

    @Inject
    private IUserService userService;

    @Override
    public void removeAllUsers(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            adminService.removeAllUsers();
        }
    }

    @Override
    public void save(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.save();
        }
    }

    @Override
    public void load(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.load();
        }
    }

    @Override
    public void saveToJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.saveToJson();
        }
    }

    @Override
    public void loadFromJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.loadFromJson();
        }
    }

    @Override
    public void saveToXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.saveToXml();
        }
    }

    @Override
    public void loadFromXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.loadFromXml();
        }
    }

    @Override
    public void saveToJsonJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.saveToJsonJaxb();
        }
    }

    @Override
    public void loadFromJsonJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.loadFromJsonJaxb();
        }
    }

    @Override
    public void saveToXmlJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.saveToXmlJaxb();
        }
    }

    @Override
    public void loadFromXmlJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (checkRole(session)) {
            domainService.loadFromXmlJaxb();
        }
    }

    private boolean checkRole(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        sessionService.validate(session);
        return userService.findOne(session.getUserId()).getRoleType() == RoleType.ADMIN;
    }
}

package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IAdminEndpoint {

    @WebMethod
    void save(@NotNull Session session);

    @WebMethod
    void load(@NotNull Session session);

    @WebMethod
    void saveToJson(@NotNull Session session);

    @WebMethod
    void loadFromJson(@NotNull Session session);

    @WebMethod
    void saveToXml(@NotNull Session session);

    @WebMethod
    void loadFromXml(@NotNull Session session);

    @WebMethod
    void saveToJsonJaxb(@NotNull Session session);

    @WebMethod
    void loadFromJsonJaxb(@NotNull Session session);

    @WebMethod
    void saveToXmlJaxb(@NotNull Session session);

    @WebMethod
    void loadFromXmlJaxb(@NotNull Session session);
}

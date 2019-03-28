package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IAdminEndpoint {

    @WebMethod
    void removeAllUsers(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void save(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void load(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveToJson(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadFromJson(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveToXml(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadFromXml(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveToJsonJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadFromJsonJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveToXmlJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadFromXmlJaxb(@WebParam(name = "session") @NotNull final Session session) throws Exception;
}

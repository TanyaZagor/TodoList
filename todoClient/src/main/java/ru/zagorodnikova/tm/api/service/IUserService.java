package ru.zagorodnikova.tm.api.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-18T19:04:12.943+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://service.api.tm.zagorodnikova.ru/", name = "IUserService")
@XmlSeeAlso({ObjectFactory.class})
public interface IUserService {

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/updateUserRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/updateUserResponse")
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.UpdateUserResponse")
    public void updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/findAllUsersRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/findAllUsersResponse")
    @RequestWrapper(localName = "findAllUsers", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.FindAllUsers")
    @ResponseWrapper(localName = "findAllUsersResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.FindAllUsersResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.zagorodnikova.tm.api.service.User> findAllUsers(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.zagorodnikova.tm.api.service.RoleType arg0
    );

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/signUpRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/signUpResponse")
    @RequestWrapper(localName = "signUp", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.SignUp")
    @ResponseWrapper(localName = "signUpResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.SignUpResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.zagorodnikova.tm.api.service.User signUp(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    );

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/changePasswordRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/changePasswordResponse")
    @RequestWrapper(localName = "changePassword", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.ChangePassword")
    @ResponseWrapper(localName = "changePasswordResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.ChangePasswordResponse")
    public void changePassword(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/removeAllUsersRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/removeAllUsersResponse")
    @RequestWrapper(localName = "removeAllUsers", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.RemoveAllUsers")
    @ResponseWrapper(localName = "removeAllUsersResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.RemoveAllUsersResponse")
    public void removeAllUsers(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/removeUserRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/removeUserResponse")
    @RequestWrapper(localName = "removeUser", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.RemoveUser")
    @ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.RemoveUserResponse")
    public void removeUser(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://service.api.tm.zagorodnikova.ru/IUserService/signInRequest", output = "http://service.api.tm.zagorodnikova.ru/IUserService/signInResponse")
    @RequestWrapper(localName = "signIn", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.SignIn")
    @ResponseWrapper(localName = "signInResponse", targetNamespace = "http://service.api.tm.zagorodnikova.ru/", className = "ru.zagorodnikova.tm.api.service.SignInResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.zagorodnikova.tm.api.service.User signIn(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );
}

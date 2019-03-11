package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zagorodnikova.tm.util.UtilPassword;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {

    private String id = UUID.randomUUID().toString();
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private RoleType roleType;


    public User(String login, String password, String firstName, String lastName, String email, RoleType roleType) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = UtilPassword.hashPassword(password);
        this.roleType = roleType;
    }


    public void setPassword(String password) {
        this.password = UtilPassword.hashPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roleType=" + roleType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}

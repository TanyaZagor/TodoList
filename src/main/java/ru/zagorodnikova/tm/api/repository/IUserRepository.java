package ru.zagorodnikova.tm.api.repository;

public interface IUserRepository {

    void signUp(String login, String password, String firstName, String lastName, String email);
    void signIn(String login, String password);
    void signOut();
    void changePassword(String login, String oldPassword, String newPassword);
    void update(String firstName, String lastName, String email);
}

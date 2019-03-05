package ru.zagorodnikova.tm.service;

import ru.zagorodnikova.tm.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signIn(String login, String password) {
        if (login == null || login.isEmpty()) return;
        if (password == null || password.isEmpty()) return;
        userRepository.signIn(login, password);
    }

    public void signOut() {
        userRepository.signOut();
    }

    public void signUp(String login, String password, String fistName, String lastName, String email) {
        if (login == null || login.isEmpty()) return;
        if (password == null || password.isEmpty()) return;
        if (fistName == null || fistName.isEmpty()) return;
        if (lastName == null || lastName.isEmpty()) return;
        if (email == null || email.isEmpty()) return;

        userRepository.signUp(login, password, fistName, lastName, email);
    }

    public void changePassword(String login, String oldPassword, String newPassword) {
        if (login == null || login.isEmpty()) return;
        if (oldPassword == null || oldPassword.isEmpty()) return;
        if (newPassword == null || newPassword.isEmpty()) return;
        userRepository.changePassword(login, oldPassword, newPassword);
    }

    public void update(String firstName, String lastName, String email) {
        if (firstName == null || firstName.isEmpty()) return;
        if (lastName == null || lastName.isEmpty()) return;
        if (email == null || email.isEmpty()) return;
        userRepository.update(firstName, lastName, email);
    }
}

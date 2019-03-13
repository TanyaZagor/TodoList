package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;

import java.util.Scanner;

public class TerminalService {

    @NotNull
    private ServiceLocator serviceLocator;

    public TerminalService(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void start() {
        Scanner scanner = serviceLocator.getScanner();
        System.out.println("Welcome");
        String command = "";
        while (!"exit".equals(command)) {
            System.out.println("Command: ");
            command = scanner.nextLine();
            serviceLocator.execute(command);
        }
    }
}
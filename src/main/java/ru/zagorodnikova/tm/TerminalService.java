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
            try {
                serviceLocator.execute(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("wrong data");
            }
        }
    }
}

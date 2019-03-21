package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;

import java.util.Scanner;

public class TerminalService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @NotNull
    private ServiceLocator serviceLocator;

    public TerminalService(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void start(){
        System.out.println("Welcome");
        @NotNull String command = "";
        while (!"exit".equals(command)) {
            System.out.println("Command: ");
            command = scanner.nextLine();
            try {
                serviceLocator.execute(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("wrong data");
            } catch (Exception e) {
                System.out.println("exception");
            }
        }
    }

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}

package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Scanner;

public class TerminalService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @NotNull
    private ServiceLocator serviceLocator;

    public TerminalService(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void start() {
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
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException");
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @NotNull
    public Scanner getScanner() {
        return scanner;
    }

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}

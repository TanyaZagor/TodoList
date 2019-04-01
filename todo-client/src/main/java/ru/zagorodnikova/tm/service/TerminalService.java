package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.ServiceLocator;

import javax.inject.Inject;
import java.util.Scanner;

public class TerminalService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}

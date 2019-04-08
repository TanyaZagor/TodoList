package ru.zagorodnikova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class TerminalService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}

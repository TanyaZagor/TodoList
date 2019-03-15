package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;

public class App {

    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}

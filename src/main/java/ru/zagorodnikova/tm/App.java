package ru.zagorodnikova.tm;


import ru.zagorodnikova.tm.bootstrap.Bootstrap;

public class App {

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(bootstrap);

    }


}

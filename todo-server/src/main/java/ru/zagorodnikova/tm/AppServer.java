package ru.zagorodnikova.tm;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;

public class AppServer {

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}

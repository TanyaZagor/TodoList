package ru.zagorodnikova.tm;

import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import javax.enterprise.inject.se.SeContainerInitializer;

public class AppServer {

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        SeContainerInitializer.newInstance() .addPackages(AppServer.class).initialize()
                .select(Bootstrap.class).get().init();


    }
}

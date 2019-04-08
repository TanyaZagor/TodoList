package ru.zagorodnikova.tm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.util.ApplicationConfiguration;

public class AppServer {

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Bootstrap bootstrap = applicationContext.getBean(Bootstrap.class);
        bootstrap.init();
    }
}

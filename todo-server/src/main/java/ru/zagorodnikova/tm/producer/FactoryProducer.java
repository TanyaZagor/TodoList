package ru.zagorodnikova.tm.producer;

import lombok.NoArgsConstructor;
import ru.zagorodnikova.tm.service.PropertyService;


@NoArgsConstructor
public class FactoryProducer {

    private PropertyService propertyService;

//    @Produces
//    public EntityManagerFactory factory() throws Exception {
//        final Map<String, String> settings = new HashMap<>();
//        settings.put(Environment.DRIVER, propertyService.getJdbcDriver());
//        settings.put(Environment.URL, propertyService.getJdbcUrl());
//        settings.put(Environment.USER, propertyService.getJdbcUsername());
//        settings.put(Environment.PASS, propertyService.getJdbcPassword());
//        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
//        settings.put(Environment.HBM2DDL_AUTO, "update");
//        settings.put(Environment.SHOW_SQL, "true");
//        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//        registryBuilder.applySettings(settings);
//        final StandardServiceRegistry registry = registryBuilder.build();
//        final MetadataSources sources = new MetadataSources(registry);
//        sources.addAnnotatedClass(Task.class);
//        sources.addAnnotatedClass(Project.class);
//        sources.addAnnotatedClass(User.class);
//        sources.addAnnotatedClass(Session.class);
//        final Metadata metadata = sources.getMetadataBuilder().build();
//        return metadata.getSessionFactoryBuilder().build();
//    }
}

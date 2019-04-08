package ru.zagorodnikova.tm.producer;

import javax.persistence.EntityManagerFactory;

public class EntityManagerProducer {

    private EntityManagerFactory entityManagerFactory;
//
//    @NotNull
//    @Produces
//    @TransactionScoped
//    public EntityManager entityManager() throws Exception {
//        return entityManagerFactory.createEntityManager();
//    }
//
//    public void close(@Disposes EntityManager entityManager) {
//        if (entityManager.isOpen()) {
//            entityManager.close();
//        }
//    }
}

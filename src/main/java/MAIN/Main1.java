package MAIN;

import Entity.MyLogger;
import Entity.Product;
import jdk.nashorn.internal.runtime.options.LoggingOption;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main1 {
    public static void main(String []args){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
            //pentru a adauga un produs in baza de date
        Product product1=new Product();

        product1.setName("Ciocolata2");
        product1.setPrice(23.5);
        product1.setData(LocalDateTime.now());

        entityTransaction.begin();
        entityManager.persist(product1);
        entityTransaction.commit();
        entityManager.close();
    }
}

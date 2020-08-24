package MAIN;

import Entity.Employee;
import Entity.MyLogger;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String []args){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();

        Session session = entityManager.unwrap(Session.class);

        // Task 1
        MyLogger.logInfo("Select all employees");
        List<Employee> employees =  session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        printList(employees);

        //Task 3 JPQL method
        MyLogger.logInfo("Sorted JPQL");
        List<Employee> sortedEmployees =  session.createQuery("SELECT e FROM Employee e order by name, salary", Employee.class).getResultList();
        printList(sortedEmployees);

        //Task 3 Lambda Method
        MyLogger.logInfo("Sorted Lambda Method");
        List<Employee> sortedEmployeesLambda = employees.stream().sorted((e1, e2) -> {
            if(e1.getName().equals(e2.getName())){
                return (int)(e1.getSalary() - e2.getSalary());
            }
            return e1.getName().compareTo(e2.getName());
        }).collect(Collectors.toList());
        printList(sortedEmployeesLambda);
    }
    private static <T> void printList(List<T> list){
        for(T e: list){
            System.out.println(e);
        }
    }
}

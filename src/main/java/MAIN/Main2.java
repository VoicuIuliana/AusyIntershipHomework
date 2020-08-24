package MAIN;

import Entity.Department;
import Entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String []args){
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();

        Department department=new Department();
        department.setName("Finance");

        Employee employee1=new Employee();
        employee1.setName("Iulia");
        employee1.setDepartments(department);

        Employee employee2=new Employee();
        employee2.setName("George");
        employee2.setDepartments(department);

        Employee employee3=new Employee();
        employee3.setName("Maria");
        employee3.setDepartments(department);

        List<Employee> employees=new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        department.setEmployee(employees);


        entityTransaction.begin();
          entityManager.persist(department);
          entityManager.persist(employee1);
//        entityManager.persist(employee2);
//        entityManager.persist(employee3);


        Session session = entityManager.unwrap(Session.class);
        // Find employees with name George
        Employee e =  session.createQuery("SELECT e FROM Employee e WHERE e.name = :nm", Employee.class)
                .setParameter("nm", "George")
                .getSingleResult();
        System.out.println("Found employee " + e);
        //Delete employees with name Iulia
        int linesDeleted =  session.createQuery("DELETE FROM Employee e WHERE e.name = :nm")
                .setParameter("nm", "Iulia")
               .executeUpdate();
        System.out.println("deletedLines: " + linesDeleted);

        entityTransaction.commit();
        entityManager.close();
    }
}

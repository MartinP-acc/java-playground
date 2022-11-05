package pl.com.calmandwritecode;

import pl.com.calmandwritecode.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HelloWorldPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = new Employee();
        employee.setFirstName("Krzysiu");
        employee.setLastName("Kowalski");
        employee.setSalary(3400);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager
                .createQuery("select e from "+Employee.class.getName()+" e").getResultList();
        employees.get(0).setLastName("Lasek");

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        for (Employee emp : employees)
            if (!emp.getLastName().isEmpty())System.out.println(emp);

    }
}

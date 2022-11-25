package pl.com.calmandwritecode.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EmployeeTest{

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;
    static int employeeCount;

    @BeforeAll
    static void setBeforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CourseProject");
        entityManager = entityManagerFactory.createEntityManager();
        Set<Employee> employees = loadEmployees();
        saveEmployeesInDb(employees);
        employeeCount = employees.size();
    }

    private static void saveEmployeesInDb(Set<Employee> employees) {
        for (Employee employee : employees){
            entityManager.getTransaction().begin();

            entityManager.persist(employee);
            entityManager.persist(employee.getHomeAddress());

            entityManager.getTransaction().commit();
        }
    }

    private static Set<Employee> loadEmployees() {
        Set<Employee> employees = new HashSet<>();
        employees.add(createNewEmployee("Jurek","Grzyb",3100.0,
                "Rzeszów", "35-900", "Lenartowicza", 12, 33));
        employees.add(createNewEmployee("Marek","Szynszyl",3320.0,
                "Rzeszów", "35-900", "Kochanowskiego", 22, 15));
        employees.add(createNewEmployee("Grzegorz","Kołodziej",2922.0,
                "Tyczyn", "35-800", "Sienkiewicza", 11, 27));
        employees.add(createNewEmployee("Michał","Młynarz",4500.0,
                "Łańcut", "35-444", "Kwiatkowskiego", 63, 1));
        employees.add(createNewEmployee("Rafał","Lucek",4200.0,
                "Rzeszów", "35-346", "Słoneczna", 22, 6));
        employees.add(createNewEmployee("Jan","Nowak",3230.0,
                "Rzeszów", "35-045", "Sportowa", 10, 53));
        employees.add(createNewEmployee("Mirek","Cholewka",4420.0,
                "Rzeszów", "35-019", "Bezowa", 9, 1));
        employees.add(createNewEmployee("Tomasz","Polański",3320.0,
                "Rzeszów", "35-111", "Kucharska", 19, 91));
        employees.add(createNewEmployee("Jacek","Sołtys",3900.0,
                "Rzeszów", "35-900", "Krakowska", 125, 13));
        employees.add(createNewEmployee("Władek","Kawalec",3240.0,
                "Rzeszów", "35-900", "Krakowska", 231, 24));
        return employees;
    }

    private static Employee createNewEmployee(String firstName, String lastName, double salary,
                                              String city, String zipCode, String street,
                                              int strNr, int locNr) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);

        Address address = new Address();
        address.setCity(city);
        address.setZipCode(zipCode);
        address.setStreet(street);
        address.setStreetNr(strNr);
        address.setLocalNr(locNr);

        employee.setHomeAddress(address);
        return employee;
    }

    @Test
    void shouldReturnCorrectNumberOfEmployeesInDatabase(){
        entityManager.getTransaction().begin();
        Long dbEmployeesCount = (Long) entityManager.createQuery("SELECT COUNT(e) FROM Employee e").getSingleResult();
        entityManager.getTransaction().commit();
        assertEquals(employeeCount,dbEmployeesCount);
    }

    @Test
    void shouldChangeEmployeeAddressFieldInDatabase(){
        String newStreet = "Kwaśniewskiego";
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, 7L);
        employee.getHomeAddress().setStreet(newStreet);
        long id = employee.homeAddress.getId();

        entityManager.refresh(employee);

        Address address = entityManager.find(Address.class,id);
        entityManager.getTransaction().commit();

        assertEquals(newStreet,address.getStreet());
    }

    @Test
    void shouldReturnSelectedEmployeesBySurname(){
        entityManager.getTransaction().begin();
        TypedQuery<Employee> query = entityManager.createQuery(
                "select e from Employee e where e.lastName in :names",
                Employee.class
        );
        List<String> names = new ArrayList<>();
        names.add("Grzyb");
        names.add("Młynarz");
        names.add("Nowak");

        query.setParameter("names", names);

        List<Employee> employees = query.getResultList();
        entityManager.getTransaction().commit();

        assertEquals(names.size(),employees.size());
        for (Employee employee : employees) System.out.println(employee.getFirstName()+" "+employee.getLastName());
    }

    @AfterAll
    static void tearDownAfterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
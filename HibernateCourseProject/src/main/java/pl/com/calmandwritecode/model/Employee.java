package pl.com.calmandwritecode.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue()
    protected long id;
    @Column(name = "name")
    protected String firstName;
    @Column(name = "surname")
    protected String lastName;
    protected double salary;
    protected double tax;

    @OneToOne
    @JoinColumn(name = "home_address_id")
    protected Address homeAddress;

    @ElementCollection
    protected Set<Phone> phones = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public double getTax() {
        return tax;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }
}

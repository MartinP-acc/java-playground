package pl.com.calmandwritecode.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue()
    protected long id;
    protected String city;

    @Column(name = "zip_code")
    protected String zipCode;
    protected String street;

    @Column(name = "street_nr")
    protected int streetNr;
    @Column(name = "local_nr")
    protected int localNr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(int streetNr) {
        this.streetNr = streetNr;
    }

    public int getLocalNr() {
        return localNr;
    }

    public void setLocalNr(int localNr) {
        this.localNr = localNr;
    }
}

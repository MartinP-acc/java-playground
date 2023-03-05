package pl.com.calmandwritecode.tacos;

import lombok.Data;

@Data
public class Order {

    private String name;
    private String street;
    private String city;
    private String region;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccv;

}

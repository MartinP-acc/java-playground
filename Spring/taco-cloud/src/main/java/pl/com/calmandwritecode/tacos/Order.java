package pl.com.calmandwritecode.tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.Date;


@Data
public class Order {

    private Long id;
    private Date placedAt;

    @NotBlank(message = "name field can't be empty")
    private String name;

    @NotBlank(message = "street can't be empty")
    private String street;

    @NotBlank(message = "city can't be empty")
    private String city;

    @NotBlank(message = "region can't be empty")
    private String region;

    @NotBlank(message = "zip code can't be empty")
    @Pattern(regexp = "[0-9][1-9]-[0-9][0-9][0-9]",message = "zip code must be set in correct format : 11-111")
    private String zip;

    @CreditCardNumber(message = "invalid credit card number")
    private String ccNumber;

    @Pattern(regexp = "(0[1-9]|1[0-2])(/)(2[3-9]|[3-5][0-9])", message = "valid format is MM/YY")
    private String ccExpiration;

    @Digits(integer = 3,fraction = 0,message = "CCV code have only 3 digits")
    private String ccv;

}

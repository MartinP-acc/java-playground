package testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10","Armii Krajowej , 57"})
    void givenAddressesShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertAll(
                ()->assertNotNull(street),
                ()->assertNotNull(number),
                ()->assertThat(street, containsString("a"))
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void givenAddressesCsvFileShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertAll(
                ()->assertNotNull(street),
                ()->assertNotNull(number),
                ()->assertThat(street, containsString("a"))
        );
    }
}
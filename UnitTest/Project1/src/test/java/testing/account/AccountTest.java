package testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import testing.account.Account;
import testing.account.Address;


//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(), "Check if new account is active");

        //matchery hamcrest
        //assertThat(newAccount.isActive(), equalTo(false));
        //assertThat(newAccount.isActive(), is(false));

        //matchery assertJ
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());

        //matchery hamcrest
        //assertThat(newAccount.isActive(),equalTo(true));
        //assertThat(newAccount.isActive(),is(true));

        //matchery assertJ
        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddress(){

        //given
        Account newAccount = new Account();

        //when
        Address address = newAccount.getDefaultDeliveryAdress();

        //then
        assertNull(address);

        //matcher hamcrest
        //assertThat(address, nullValue());

        //matcher assertJ
        assertThat(address).isNull();
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){

        //given
        Address address = new Address("Pierdziałkowa", "12");
        Account newAccount = new Account();
        newAccount.setDefaultDeliveryAdress(address);

        //when
        Address defAddress = newAccount.getDefaultDeliveryAdress();

        //then
        assertNotNull(defAddress);

        //matcher hamcrest
        //assertThat(defAddress, is(notNullValue()));

        //matcher assertJ
        assertThat(defAddress).isNotNull();
    }

    @RepeatedTest(3)
    void newAccountWithNotNullShouldBeActive(){

        //given
        Address address = new Address("Dąbrowskiego" ,"90/44");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });

    }

}

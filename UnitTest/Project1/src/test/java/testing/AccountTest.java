package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(), "Check if new account is active");
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
    }

}

package testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Test
    void allReturnedAccountsInListShouldBeActive() {
        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList.size()).isEqualTo(2);
    }

    @Test
    void getNoActiveAccount() {
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(Arrays.asList());

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList.size()).isEqualTo(2);
    }

    private List<Account> prepareAccountData(){

        Account account1 = new Account(new Address("Staszica","12,14"));
        Account account2 = new Account();
        Account account3 = new Account(new Address("Lenartowicza","19,42"));
        Account account4 = new Account();

        return Arrays.asList(account1,account2,account3,account4);
    }
}

package testing.account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository{

    @Override
    public List<Account> getAllAccounts() {

        Account account1 = new Account(new Address("Staszica","12,14"));
        Account account2 = new Account();
        Account account3 = new Account(new Address("Lenartowicza","19,42"));
        Account account4 = new Account();

        List<Account> tempList = new ArrayList<>();

        tempList.add(account1);
        tempList.add(account2);
        tempList.add(account3);
        tempList.add(account4);

        return tempList;
    }
}

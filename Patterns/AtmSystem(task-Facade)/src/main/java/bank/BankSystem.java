package bank;

class BankSystem {

    void transferMoney(){
        System.out.println("transfering money");
    }

    boolean validatePin(){
        System.out.println("pin number is correct");
        return true;
    }

    boolean validateTransaction(){
        System.out.println("transaction has been validated");
        return true;
    }

    void getTransactionHistory(){
        System.out.println("printing transaction history on screen");
    }
}

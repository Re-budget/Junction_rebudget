package nordea.junction.rebudget.model;

/**
 * Created by Bel on 25/11/2017.
 */

public class Account {

    private String accountName;
    private String accountIban;
    private String currency;
    private String accountBalance;

    public Account(String accountName, String accountIban, String accountBalance, String currency) {
        this.accountName = accountName;
        this.accountIban = accountIban;
        this.accountBalance = accountBalance;
        this.currency = currency;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountIban() {
        return accountIban;
    }

    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}

package m2_2.m2_2_2;

public class Account {
    private String account;
    private String fullName;
    private double balance;

    public Account() {
    }

    public Account(String account, String fullName, double balance) {
        this.account = account;
        this.fullName = fullName;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                '}';
    }
}

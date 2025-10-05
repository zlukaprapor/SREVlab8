public class CustomerPrinter {

    private Customer customer;
    private Account account;

    public CustomerPrinter(Customer customer, Account account) {
        this.customer = customer;
        this.account = account;
    }

    public String printCustomerDaysOverdrawn() {
        return getFullName() + "Account: IBAN: " + account.getIban()
                + ", Days Overdrawn: " + account.getDaysOverdrawn();
    }

    public String printCustomerMoney() {
        return getFullName() + "Account: IBAN: " + account.getIban()
                + ", Money: " + account.getMoney();
    }

    private String getFullName() {
        return customer.getName() + " " + customer.getSurname() + " ";
    }
}
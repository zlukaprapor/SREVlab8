public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;
    private WithdrawalStrategy withdrawalStrategy;


    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
        this.withdrawalStrategy = WithdrawalStrategyFactory.getStrategy(customerType, account.getType());
    }

    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
        this.withdrawalStrategy = WithdrawalStrategyFactory.getStrategy(CustomerType.COMPANY, account.getType());
    }

    public void withdraw(double sum, String currency) {
        validateCurrency(currency);
        account.withdraw(sum, withdrawalStrategy, companyOverdraftDiscount);
    }

    private void validateCurrency(String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
    }

    // MOVED FROM Account - uses only Customer data
    public String printCustomer() {
        return name + " " + email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String printCustomerDaysOverdrawn() {
        return getFullName() + account.printAccountDaysOverdrawn();
    }

    public String printCustomerMoney() {
        return getFullName() + account.printAccountMoney();
    }

    public String printCustomerAccount() {
        return account.printAccountInfo();
    }

    private String getFullName() {
        return name + " " + surname + " ";
    }
}

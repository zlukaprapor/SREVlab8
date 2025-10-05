public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;


    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    // use only to create companies
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double sum, String currency) {
        validateCurrency(currency);

        if (account.getMoney() < 0) {
            withdrawWithOverdraft(sum);
        } else {
            withdrawNormal(sum);
        }
    }

    private void validateCurrency(String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
    }

    private void withdrawWithOverdraft(double sum) {
        double overdraftFee = calculateOverdraftFee(sum);
        account.setMoney(account.getMoney() - sum - overdraftFee);
    }

    private void withdrawNormal(double sum) {
        account.setMoney(account.getMoney() - sum);
    }

    private double calculateOverdraftFee(double sum) {
        double baseFee = sum * account.overdraftFee();

        if (customerType == CustomerType.COMPANY) {
            return calculateCompanyOverdraftFee(baseFee);
        }

        return baseFee;
    }

    private double calculateCompanyOverdraftFee(double baseFee) {
        if (account.getType().isPremium()) {
            return baseFee * companyOverdraftDiscount / 2;
        }
        return baseFee * companyOverdraftDiscount;
    }

    public String getName() {
        return name;
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
        return getFullName() + getAccountDaysOverdrawnDescription();
    }

    public String printCustomerMoney() {
        return getFullName() + getAccountMoneyDescription();
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoney() + ", Account type: " + account.getType();
    }

    private String getFullName() {
        return name + " " + surname + " ";
    }

    private String getAccountDaysOverdrawnDescription() {
        return "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
    }

    private String getAccountMoneyDescription() {
        return "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
    }
}
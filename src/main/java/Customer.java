public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;
    private CustomerPrinter printer;


    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
        this.printer = new CustomerPrinter(this, account);
    }

    // use only to create companies
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
        this.printer = new CustomerPrinter(this, account);
    }

    public void withdraw(double sum, String currency) {
        validateCurrency(currency);
        processWithdrawal(sum);
    }

    private void validateCurrency(String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
    }

    private void processWithdrawal(double sum) {
        if (account.getType().isPremium()) {
            processPremiumWithdrawal(sum);
        } else {
            processNormalWithdrawal(sum);
        }
    }

    private void processPremiumWithdrawal(double sum) {
        switch (customerType) {
            case COMPANY:
                withdrawCompanyPremium(sum);
                break;
            case PERSON:
                withdrawPersonPremium(sum);
                break;
        }
    }

    private void processNormalWithdrawal(double sum) {
        switch (customerType) {
            case COMPANY:
                withdrawCompanyNormal(sum);
                break;
            case PERSON:
                withdrawPersonNormal(sum);
                break;
        }
    }

    private void withdrawCompanyPremium(double sum) {
        // we are in overdraft
        if (account.getMoney() < 0) {
            // 50 percent discount for overdraft for premium account
            account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee() * companyOverdraftDiscount / 2);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    private void withdrawPersonPremium(double sum) {
        // we are in overdraft
        if (account.getMoney() < 0) {
            account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee());
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    private void withdrawCompanyNormal(double sum) {
        // we are in overdraft
        if (account.getMoney() < 0) {
            // no discount for overdraft for not premium account
            account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee() * companyOverdraftDiscount);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    private void withdrawPersonNormal(double sum) {
        // we are in overdraft
        if (account.getMoney() < 0) {
            account.setMoney((account.getMoney() - sum) - sum * account.overdraftFee());
        } else {
            account.setMoney(account.getMoney() - sum);
        }
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

    // Delegate to CustomerPrinter
    public String printCustomerDaysOverdrawn() {
        return printer.printCustomerDaysOverdrawn();
    }

    public String printCustomerMoney() {
        return printer.printCustomerMoney();
    }

    public String printCustomerAccount() {
        return printer.printCustomerAccount();
    }
}
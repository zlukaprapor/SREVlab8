public class Account {

    private String iban;
    private AccountType type;
    private int daysOverdrawn;
    private Money money;
    private Customer customer;

    public Account(AccountType type, int daysOverdrawn) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;
        result += overdraftCharge();
        return result;
    }

    private double overdraftCharge() {
        if (type.isPremium()) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (type.isPremium()) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public void withdraw(Money amount, WithdrawalStrategy strategy, double companyOverdraftDiscount) {
        if (!money.hasSameCurrency(amount.getCurrency())) {
            throw new RuntimeException("Can't extract withdraw " + amount.getCurrency());
        }
        strategy.withdraw(this, amount, companyOverdraftDiscount);
    }

    public String printAccountInfo() {
        return "Account: IBAN: " + iban + ", Money: " + money + ", Account type: " + type;
    }

    public String printAccountDaysOverdrawn() {
        return "Account: IBAN: " + iban + ", Days Overdrawn: " + daysOverdrawn;
    }

    public String printAccountMoney() {
        return "Account: IBAN: " + iban + ", Money: " + money;
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double amount) {
        if (this.money == null) {
            throw new RuntimeException("Currency not set");
        }
        this.money = new Money(amount, this.money.getCurrency());
    }

    public double getMoney() {
        return money.getAmount();
    }

    public Money getMoneyObject() {
        return money;
    }

    public void setMoneyObject(Money money) {
        this.money = money;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public String getCurrency() {
        return money != null ? money.getCurrency() : null;
    }

    public void setCurrency(String currency) {
        if (this.money == null) {
            this.money = new Money(0, currency);
        } else {
            this.money = new Money(this.money.getAmount(), currency);
        }
    }
}
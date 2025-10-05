public class Money {

    private double amount;
    private String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money subtract(double value) {
        return new Money(this.amount - value, this.currency);
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new RuntimeException("Cannot subtract different currencies");
        }
        return new Money(this.amount - other.amount, this.currency);
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public boolean hasSameCurrency(String currency) {
        return this.currency.equals(currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}

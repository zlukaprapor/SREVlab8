public interface WithdrawalStrategy {
    void withdraw(Account account, Money amount, double companyOverdraftDiscount);
}


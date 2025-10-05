public interface WithdrawalStrategy {
    void withdraw(Account account, double sum, double companyOverdraftDiscount);
}

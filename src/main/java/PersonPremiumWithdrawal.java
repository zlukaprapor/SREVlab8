public class PersonPremiumWithdrawal implements WithdrawalStrategy {

    @Override
    public void withdraw(Account account, double sum, double companyOverdraftDiscount) {
        if (isInOverdraft(account)) {
            double overdraftFee = sum * account.overdraftFee();
            account.setMoney(account.getMoney() - sum - overdraftFee);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    private boolean isInOverdraft(Account account) {
        return account.getMoney() < 0;
    }
}

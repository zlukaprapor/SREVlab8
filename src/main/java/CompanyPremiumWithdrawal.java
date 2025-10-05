public class CompanyPremiumWithdrawal implements WithdrawalStrategy {

    @Override
    public void withdraw(Account account, double sum, double companyOverdraftDiscount) {
        if (isInOverdraft(account)) {
            double overdraftFeeWithPremiumDiscount = sum * account.overdraftFee() * companyOverdraftDiscount / 2;
            account.setMoney(account.getMoney() - sum - overdraftFeeWithPremiumDiscount);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    private boolean isInOverdraft(Account account) {
        return account.getMoney() < 0;
    }
}


public class CompanyNormalWithdrawal implements WithdrawalStrategy {

    @Override
    public void withdraw(Account account, double sum, double companyOverdraftDiscount) {
        if (isInOverdraft(account)) {
            double overdraftFeeWithDiscount = sum * account.overdraftFee() * companyOverdraftDiscount;
            account.setMoney(account.getMoney() - sum - overdraftFeeWithDiscount);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    private boolean isInOverdraft(Account account) {
        return account.getMoney() < 0;
    }
}

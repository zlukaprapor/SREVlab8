public class CompanyNormalWithdrawal implements WithdrawalStrategy {

    @Override
    public void withdraw(Account account, Money amount, double companyOverdraftDiscount) {
        if (account.getMoneyObject().isNegative()) {
            double overdraftFeeWithDiscount = amount.getAmount() * account.overdraftFee() * companyOverdraftDiscount;
            account.setMoney(account.getMoney() - amount.getAmount() - overdraftFeeWithDiscount);
        } else {
            account.setMoney(account.getMoney() - amount.getAmount());
        }
    }
}
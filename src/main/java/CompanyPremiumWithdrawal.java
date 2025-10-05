public class CompanyPremiumWithdrawal implements WithdrawalStrategy {

    @Override
    public void withdraw(Account account, Money amount, double companyOverdraftDiscount) {
        if (account.getMoneyObject().isNegative()) {
            double overdraftFeeWithPremiumDiscount = amount.getAmount() * account.overdraftFee() * companyOverdraftDiscount / 2;
            account.setMoney(account.getMoney() - amount.getAmount() - overdraftFeeWithPremiumDiscount);
        } else {
            account.setMoney(account.getMoney() - amount.getAmount());
        }
    }
}


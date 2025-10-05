public class PersonPremiumWithdrawal implements WithdrawalStrategy {

    @Override
    public void withdraw(Account account, Money amount, double companyOverdraftDiscount) {
        if (account.getMoneyObject().isNegative()) {
            double overdraftFee = amount.getAmount() * account.overdraftFee();
            account.setMoney(account.getMoney() - amount.getAmount() - overdraftFee);
        } else {
            account.setMoney(account.getMoney() - amount.getAmount());
        }
    }
}
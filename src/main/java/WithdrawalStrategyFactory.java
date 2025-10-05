public class WithdrawalStrategyFactory {

    public static WithdrawalStrategy getStrategy(CustomerType customerType, AccountType accountType) {
        if (customerType == CustomerType.PERSON) {
            if (accountType.isPremium()) {
                return new PersonPremiumWithdrawal();
            } else {
                return new PersonNormalWithdrawal();
            }
        } else {
            if (accountType.isPremium()) {
                return new CompanyPremiumWithdrawal();
            } else {
                return new CompanyNormalWithdrawal();
            }
        }
    }
}

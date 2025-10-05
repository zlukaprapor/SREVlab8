public class WithdrawalStrategyFactory {

    public static WithdrawalStrategy getStrategy(CustomerType customerType, boolean isPremium) {
        if (customerType == CustomerType.PERSON) {
            if (isPremium) {
                return new PersonPremiumWithdrawal();
            } else {
                return new PersonNormalWithdrawal();
            }
        } else {
            if (isPremium) {
                return new CompanyPremiumWithdrawal();
            } else {
                return new CompanyNormalWithdrawal();
            }
        }
    }
}
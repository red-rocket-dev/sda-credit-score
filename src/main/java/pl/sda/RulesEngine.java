package pl.sda;

import java.math.BigDecimal;

public class RulesEngine {
    private BigDecimal interest;
    private BigDecimal commission;

    public RulesEngine(BigDecimal interest, BigDecimal commission) {
        this.interest = interest;
        this.commission = commission;
    }

    private boolean isAgeOverTheLimit(Debitor debitor) {
        return debitor.getAge() > 65;
    }

    private boolean isSumOfAllLoansInstallmentsToHigh(Debitor debitor) {
        BigDecimal sumOfAllLoansInstallments = debitor.getSumOfLoanInstallments().add(debitor.loanInstallment());
        final BigDecimal loweringIncomeFactor = new BigDecimal("0.8");
        BigDecimal acceptableLevelOfDebitorIncome = debitor.getIncome().multiply(loweringIncomeFactor);
        int resultOfCompareTo = sumOfAllLoansInstallments.compareTo(acceptableLevelOfDebitorIncome);
        return resultOfCompareTo >= 0;
    }

    private boolean isCredibilityOfDebitorUnderminded(Debitor debitor) {
        return debitor.isRepaymentDelayed();
    }

    private boolean isAgeAndRepaymentPeriodOverTheLimit(Debitor debitor) {
        BigDecimal debitorAge = new BigDecimal(debitor.getAge());
        BigDecimal debitorRepaymentPeriod = new BigDecimal(debitor.getRepaymentPeriod());
        BigDecimal sumOfAgeAndRepaymentPeriod = debitorAge.add(debitorRepaymentPeriod);
        final BigDecimal maxAgeFactor = new BigDecimal("65");
        int resultOfCompareTo = sumOfAgeAndRepaymentPeriod.compareTo(maxAgeFactor);
        return resultOfCompareTo > 0;
    }

    boolean isCreditApproved(Debitor debitor) {
        return !isAgeOverTheLimit(debitor) && !isSumOfAllLoansInstallmentsToHigh(debitor) &&
                !isCredibilityOfDebitorUnderminded(debitor) && !isAgeAndRepaymentPeriodOverTheLimit(debitor);
    }

}
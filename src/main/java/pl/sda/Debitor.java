package pl.sda;

import java.math.BigDecimal;
import java.math.MathContext;

public class Debitor {
    private int age;
    private BigDecimal income;
    private BigDecimal[] loanInstallments;
    private BigDecimal sumOfLoanInstallments;
    private boolean repaymentDelayed;
    private BigDecimal amountOfCredit;
    private int repaymentPeriod;

    public Debitor() {
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal[] getLoanInstallments() {
        return loanInstallments;
    }

    public boolean isRepaymentDelayed() {
        return repaymentDelayed;
    }

    public BigDecimal getAmountOfCredit() {
        return amountOfCredit;
    }

    public int getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setLoanInstallments(BigDecimal[] loanInstallments) {
        this.loanInstallments = loanInstallments;
    }

    public void setRepaymentDelayed(boolean repaymentDelayed) {
        this.repaymentDelayed = repaymentDelayed;
    }

    public void setAmountOfCredit(BigDecimal amountOfCredit) {
        this.amountOfCredit = amountOfCredit;
    }

    public void setRepaymentPeriod(int repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public BigDecimal getSumOfLoanInstallments() {
        return sumOfLoanInstallments;
    }

    public BigDecimal loanInstallment() {
        final BigDecimal monthsInYear = new BigDecimal("12");
        BigDecimal repaymentPeriod = new BigDecimal(this.repaymentPeriod);
        BigDecimal repaymentPeriodInMonths = (repaymentPeriod.multiply(monthsInYear));
        return (amountOfCredit.divide(repaymentPeriod.multiply(repaymentPeriodInMonths), MathContext.DECIMAL128));
    }

    public void setSumOfLoanInstallments(BigDecimal sumOfLoanInstallments) {
        this.sumOfLoanInstallments = sumOfLoanInstallments;
    }
}


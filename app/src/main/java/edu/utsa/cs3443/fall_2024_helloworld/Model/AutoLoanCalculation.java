package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/*** Calculate the monthly payment, total interest paid, and total cost of an auto loan
 *
 * @author Joseph
 */
public class AutoLoanCalculation extends Calculation implements Serializable {
    private double tradeInValue = 0;
    private double downPayment = 0;
    private double extraPayment = 0;
    private double monthlyPayment;
    private double totalInterestPaid;
    private double totalCostOfLoan;
    private double monthlyInterestRate;
    private int monthsTillPaidOff;

    /**
     * Instantiates a new Auto loan calculation.
     *
     * @param loanAmount the loan amount
     * @param loanARP    the loan arp
     * @param loanYears  the loan years
     */
    public AutoLoanCalculation(double loanAmount,double loanARP, double loanYears) {
        super(loanAmount,loanARP,loanYears);
        this.monthlyInterestRate = super.getLoanAPR() / 100 / 12;

    }

    /**
     * Gets trade in value.
     *
     * @return the trade in value
     */
    public double getTradeInValue() {
        return tradeInValue;
    }

    /**
     * Gets down payment.
     *
     * @return the down payment
     */
    public double getDownPayment() {
        return downPayment;
    }


    /**
     * Sets extra payment.
     *
     * @param extraPayment the extra payment
     */
    public void setExtraPayment(double extraPayment) {
        this.extraPayment = extraPayment;
    }

    /**
     * Sets trade in value.
     *
     * @param tradeInValue the trade in value
     */
    public void setTradeInValue(double tradeInValue) {
        this.tradeInValue = tradeInValue;
    }

    /**
     * Sets down payment.
     *
     * @param downPayment the down payment
     */
    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }


    /**
     * Gets monthly payment.
     *
     * @return the monthly payment
     */
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    /**
     * Gets total interest paid.
     *
     * @return the total interest paid
     */
    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    /**
     * Gets total cost of loan.
     *
     * @return the total cost of loan
     */
    public double getTotalCostOfLoan() {
        return totalCostOfLoan;
    }

    /**
     * Gets months till paid off.
     *
     * @return the months till paid off
     */
    public int getMonthsTillPaidOff() {
        return monthsTillPaidOff;
    }

    /**
     * Gets extra payment.
     *
     * @return the extra payment
     */
    public double getExtraPayment() {
        return extraPayment;
    }

    /**
     * Calculate loan payments.
     */
    public void calculateLoanPayments() {

        double numberOfPayments = super.getLoanYears() * 12;

        // Calculate the base monthly payment
        this.monthlyPayment = (super.getLoanAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        // After base payment calculation, factor in extra payments
        calculateExtraPayment();
    }

    /**
     * Calculate total cost of loan, total interest paid and months till paid off
     */
    public void calculateExtraPayment() {
        double totalMonthlyPayment = this.monthlyPayment + this.extraPayment;
        double remainingBalance = super.getLoanAmount() - this.downPayment - this.tradeInValue;
        double totalPaid = 0;
        double interestPaid = 0;
        int months = 0;

        // Simulate loan repayment month by month
        while (remainingBalance > 0) {
            months++;
            double interestForTheMonth = remainingBalance * monthlyInterestRate;
            double principalPayment = totalMonthlyPayment - interestForTheMonth;

            // Ensure the last payment doesn't overpay the remaining balance
            if (principalPayment > remainingBalance) {
                principalPayment = remainingBalance;
                totalMonthlyPayment = principalPayment + interestForTheMonth;
            }

            remainingBalance -= principalPayment;
            totalPaid += totalMonthlyPayment;
            interestPaid += interestForTheMonth;
        }

        // Store the total cost of the loan and interest paid after all payments are made
        this.totalCostOfLoan = totalPaid;
        this.totalInterestPaid = interestPaid;
        this.monthsTillPaidOff = months;
    }
}

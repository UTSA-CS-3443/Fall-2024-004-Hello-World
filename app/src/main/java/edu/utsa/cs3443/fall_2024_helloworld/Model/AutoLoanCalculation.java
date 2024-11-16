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

    public AutoLoanCalculation(double loanAmount,double loanARP, double loanYears) {
        super(loanAmount,loanARP,loanYears);
        this.monthlyInterestRate = super.getLoanAPR() / 100 / 12;

    }

    public double getTradeInValue() {
        return tradeInValue;
    }

    public double getDownPayment() {
        return downPayment;
    }


    public void setExtraPayment(double extraPayment) {
        this.extraPayment = extraPayment;
    }

    public void setTradeInValue(double tradeInValue) {
        this.tradeInValue = tradeInValue;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    public double getTotalCostOfLoan() {
        return totalCostOfLoan;
    }

    public int getMonthsTillPaidOff() {
        return monthsTillPaidOff;
    }

    public double getExtraPayment() {
        return extraPayment;
    }

    public void calculateLoanPayments() {

        double numberOfPayments = super.getLoanYears() * 12;

        // Calculate the base monthly payment
        this.monthlyPayment = (super.getLoanAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        // After base payment calculation, factor in extra payments
        calculateExtraPayment();
    }

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

package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

public class AutoLoanCalculation extends Calculation implements Serializable {
    private double tradeInValue;
    private double downPayment;
    private double loanARP;
    private double totalCost;
    private double extraPayment;
    private double loanAmount;
    private double monthlyPayment;
    private double totalInterestPaid;
    private double totalCostOfLoan;

    public AutoLoanCalculation(double tradeInValue, double downPayment, double loanARP, double totalCost, double extraPayment) {
        this.tradeInValue = tradeInValue;
        this.downPayment = downPayment;
        this.loanARP = loanARP;
        this.totalCost = totalCost;
        this.extraPayment = extraPayment;

        // Calculate the loan amount after trade-in and down payment
        this.loanAmount = totalCost - tradeInValue - downPayment;
        calculateLoanPayments();
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

    public double getExtraPayment() {
        return extraPayment;
    }

    public void calculateLoanPayments() {
        double monthlyInterestRate = calcMonthlyInterestRate(loanARP);
        double numberOfPayments = 60;

        // Calculate the base monthly payment
        this.monthlyPayment = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        // After base payment calculation, factor in extra payments
        calculateExtraPayment();
    }

    public void calculateExtraPayment() {
        double totalMonthlyPayment = this.monthlyPayment + extraPayment;
        double remainingBalance = this.loanAmount;
        double totalPaid = 0;
        double interestPaid = 0;
        int months = 0;

        // Simulate loan repayment month by month
        while (remainingBalance > 0) {
            months++;
            double interestForTheMonth = remainingBalance * calcMonthlyInterestRate(loanARP);
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
    }
}

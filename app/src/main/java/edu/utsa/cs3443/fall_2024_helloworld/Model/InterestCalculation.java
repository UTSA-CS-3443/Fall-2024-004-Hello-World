package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/*** Calculate the compound interest and continuous compound interest
 *
 * @author Joseph
 */
public class InterestCalculation extends Calculation implements Serializable {



    double monthlyPaymentAmount;
    private int monthsTillPaidOff;
    private double totalInterestPaid;
    private double totalCostOfLoan;
    /***
     * Constructor for the InterestCalculation class
     * @param loanAmount the initial balance
     * @param loanAPR the interest rate as a value 1<x<0
     * @param monthlyPaymentAmount how much is paid per month
     */
    public InterestCalculation(double loanAmount, double loanAPR, double monthlyPaymentAmount) {
        super(loanAmount,loanAPR);
        this.monthlyPaymentAmount = monthlyPaymentAmount;

    }

    public double getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public int getMonthsTillPaidOff() {
        return monthsTillPaidOff;
    }

    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    public double getTotalCostOfLoan() {
        return totalCostOfLoan;
    }

    public void calculateInterest() {
        double totalMonthlyPayment = this.monthlyPaymentAmount;
        double remainingBalance = super.getLoanAmount();
        double totalPaid = 0;
        double interestPaid = 0;
        int months = 0;

        // Simulate loan repayment month by month
        while (remainingBalance > 0) {
            months++;
            double interestForTheMonth = remainingBalance * (super.getLoanAPR() / 100 / 12);
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

package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/*** Calculate the compound interest and continuous compound interest
 *
 * @author Joseph
 */
public class InterestCalculation extends Calculation implements Serializable {


    /**
     * The Monthly payment amount.
     */
    double monthlyPaymentAmount;
    /**
     * The Months till paid off.
     */
    private int monthsTillPaidOff;
    /**
     * The Total interest paid.
     */
    private double totalInterestPaid;
    /**
     * The Total cost of loan.
     */
    private double totalCostOfLoan;

    /***
     * Constructor for the InterestCalculation class
     * @param loanAmount the initial balance
     * @param loanAPR the interest rate as a value between 0 and 100
     * @param monthlyPaymentAmount how much is paid per month
     */
    public InterestCalculation(double loanAmount, double loanAPR, double monthlyPaymentAmount) {
        super(loanAmount,loanAPR);
        this.monthlyPaymentAmount = monthlyPaymentAmount;

    }

    /**
     * Gets monthly payment amount.
     *
     * @return the monthly payment amount
     */
    public double getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
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
     * Calculate Total Interest Paid, Total Cost of Loan, and Months Till Paid Off
     */
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

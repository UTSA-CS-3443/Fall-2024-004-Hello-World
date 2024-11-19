package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/*** Calculate the monthly payment, total interest paid, and total cost of a mortgage
 *
 * @author Wheeler
 */
public class MortgageCalculation extends Calculation implements Serializable {
    // Constants
    private static final int MONTHS_IN_YEARS = 12;
    // Instance variables
    /*** The private deposit amount */
    private double depositAmount;
    /*** The private property taxes */
    private double propertyTaxes;
    /*** The private insurance */
    private double insurance;
    /*** The private mortgage insurance (PMI) */
    private double pmi;
    /*** The extra payment to be made each month */
    private double extraPayment = 0;
    /*** The number of months until the mortgage is paid off */
    private int monthsTillPaidOff;
    /*** The monthly interest rate for the mortgage */
    private double monthlyInterestRate;
    /*** The number of payments for the mortgage */
    private double numberOfPayments;
    /*** The monthly payment for the mortgage */
    private double monthlyPayment;
    /*** The total cost of the mortgage including the loan amount, property taxes, insurance, and PMI */
    private double totalInterestPaid;
    /*** The total interest paid over the life of the mortgage */
    private double totalCostOfMortgage;


    /**
     * Gets deposit amount.
     *
     * @return the deposit amount
     */
    public double getDepositAmount() {
        return depositAmount;
    }

    /**
     * Gets property taxes.
     *
     * @return the property taxes
     */
    public double getPropertyTaxes() {
        return propertyTaxes;
    }

    /**
     * Gets insurance.
     *
     * @return the insurance
     */
    public double getInsurance() {
        return insurance;
    }

    /**
     * Gets pmi.
     *
     * @return the pmi
     */
    public double getPmi() {return pmi;}

    /**
     * Instantiates a new Mortgage calculation.
     *
     * @param loanAmount    the loan amount
     * @param loanAPR       the loan apr
     * @param loanYears     the loan years
     * @param depositAmount the deposit amount
     * @param propertyTaxes the property taxes
     * @param insurance     the insurance
     * @param pmi           the pmi
     */
    public MortgageCalculation(double loanAmount, double loanAPR, double loanYears, double depositAmount, double propertyTaxes, double insurance, double pmi   ) {
        super(loanAmount, loanAPR,loanYears);
        this.depositAmount = depositAmount;
        this.propertyTaxes = propertyTaxes;
        this.insurance = insurance;
        this.pmi = pmi;
        // Calculate the monthly interest rate and number of payments
        this.monthlyInterestRate = loanAPR / 100 / MONTHS_IN_YEARS;
        this.numberOfPayments = loanYears * MONTHS_IN_YEARS;
        this.monthlyPayment = calculateMonthlyPayment() + ((propertyTaxes + insurance) / 12) + pmi;


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
     * Gets monthly payment.
     *
     * @return the monthly payment
     */
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    /**
     * Gets total cost of mortgage.
     *
     * @return the total cost of mortgage
     */
    public double getTotalCostOfMortgage() {
        return totalCostOfMortgage;
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
     * Gets total interest paid.
     *
     * @return the total interest paid
     */
    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    /**
     * Calculate monthly payment double.
     *
     * @return the double
     */
    public double calculateMonthlyPayment() {

        return (super.getLoanAmount() - depositAmount) * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

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
     * Calculate Monthly Payment, Total Interest Paid, and Total Cost of Mortgage with Extra Payment
     */
    public void calculateExtraPayment() {

        // Total payment including the extra payment
        double totalMonthlyPayment = calculateMonthlyPayment() + this.extraPayment;
        // Initialize variables
        double remainingBalance = super.getLoanAmount() - this.depositAmount;
        double totalPaid = 0;
        double interestPaid = 0;
        int months = 0;

        // Simulate loan repayment month by month
        while (remainingBalance > 0) {
            months++;
            double interestForTheMonth = remainingBalance * this.monthlyInterestRate;
            double principalPayment = totalMonthlyPayment - interestForTheMonth;

            // Ensure the last payment doesn't overpay the remaining balance
            if (principalPayment > remainingBalance) {
                principalPayment = remainingBalance;
                totalMonthlyPayment = principalPayment + interestForTheMonth;
            }

            // Update the remaining balance
            remainingBalance -= principalPayment;
            totalPaid += totalMonthlyPayment;
            interestPaid += interestForTheMonth;
        }

        this.monthsTillPaidOff = months;
        this.totalCostOfMortgage = totalPaid;
        this.totalInterestPaid = interestPaid;
    }

}

package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;
/*** Calculate the monthly payment, total interest paid, and total cost of a mortgage
 *
 * @author Wheeler
 */
public class MortgageCalculation extends Calculation implements Serializable {

    private static final int MONTHS_IN_YEARS = 12;

    private double depositAmount;
    private double propertyTaxes;
    private double insurance;
    private double pmi;
    private double extraPayment = 0;
    private int monthsTillPaidOff;
    private double monthlyInterestRate;
    private double numberOfPayments;
    private double monthlyPayment;
    private double totalInterestPaid;
    private double totalCostOfMortgage;

    public double getLoanYears() {
        return loanYears;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public double getPropertyTaxes() {
        return propertyTaxes;
    }

    public double getInsurance() {
        return insurance;
    }

    public double getPmi() {return pmi;}

    public MortgageCalculation(double loanAmount, double loanAPR, double loanYears, double depositAmount, double propertyTaxes, double insurance, double pmi   ) {
        super(loanAmount, loanAPR,loanYears);
        this.depositAmount = depositAmount;
        this.propertyTaxes = propertyTaxes;
        this.insurance = insurance;
        this.pmi = pmi;
        this.monthlyInterestRate = loanAPR / 100 / MONTHS_IN_YEARS;
        this.numberOfPayments = loanYears * MONTHS_IN_YEARS;
        this.monthlyPayment = calculateMonthlyPayment() + ((propertyTaxes + insurance) / 12) + pmi;


    }

    public int getMonthsTillPaidOff() {
        return monthsTillPaidOff;
    }

    public double getLoanAmount() {
        return loanAmount;
    }



    public double getLoanAPR() {
        return loanAPR;
    }





    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalCostOfMortgage() {
        return totalCostOfMortgage;
    }

    public double getExtraPayment() {
        return extraPayment;
    }

    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    public double calculateMonthlyPayment() {

        return (loanAmount - depositAmount) * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

    }

    public void setExtraPayment(double extraPayment) {
        this.extraPayment = extraPayment;
    }

    public void calculateExtraPayment() {

        // Total payment including the extra payment
        double totalMonthlyPayment = calculateMonthlyPayment() + this.extraPayment;
        // Initialize variables
        double remainingBalance = this.loanAmount - this.depositAmount;
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

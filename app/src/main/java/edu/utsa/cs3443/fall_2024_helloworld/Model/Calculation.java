package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/** This class is meant to server as a foundation class where other calculator class
 * will extend from.
 *
 * Authors: Collaborative effort of the team
 */
public abstract class Calculation implements Serializable {
    private double loanAmount;
    private double loanAPR;
    private double loanYears;


    public Calculation(double loanAmount, double loanAPR, double loanYears) {
        this.loanAmount = loanAmount;
        this.loanAPR = loanAPR;
        this.loanYears = loanYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanAPR() {
        return loanAPR;
    }

    public void setLoanAPR(double loanAPR) {
        this.loanAPR = loanAPR;
    }

    public double getLoanYears() {
        return loanYears;
    }

    public void setLoanYears(double loanYears) {
        this.loanYears = loanYears;
    }

    /**
     *
     * @param annualRate APR
     * @return monthly interest rate
     */
    public double calcMonthlyInterestRate(double annualRate){
        return annualRate / 12;

    }

    /**
     *
     * @param propertyPrice total price of property
     * @param deposit the deposit amount
     * @return the total loan amount
     */
    public double calcMortgageLoanAmount(double propertyPrice, double deposit){
        return propertyPrice - deposit;
    }

    /**
     *
     * @param loanAmount
     * @param loanAPR
     * @param loanYears
     * @return
     */
    public double calcMortgagePayments(float loanAmount, float loanAPR, int loanYears){
        int totalPayments = loanYears * 12;
        float monthlyRate = loanAPR / 12;
        return calcMortgagePayments(loanAmount, monthlyRate, totalPayments);
    }

    /**
     *
     * @param principal Initial balance
     * @param interestRate interest rate as a value 1<x<0
     * @param timesCompoundedPerYear Times Compounded per year, for example 12 for monthly, or 53 for weekly
     * @param years years
     * @return Total after interest
     */
    public double calcCompoundInterest(double principal, double interestRate, int timesCompoundedPerYear, double years){
        return Math.pow(principal*(1+interestRate/timesCompoundedPerYear),timesCompoundedPerYear*years);
    }

    /**
     *
     * @param principle Initial balance
     * @param periodicInterestRate interest rate over a period
     * @param periods number of periods
     * @return total after interest
     */
    public double calcContinuousCompoundInterest(double principle, double periodicInterestRate, double periods){
        return principle * Math.pow(Math.E,periodicInterestRate*periods);
    }
}

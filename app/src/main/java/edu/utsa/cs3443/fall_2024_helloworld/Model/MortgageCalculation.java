package edu.utsa.cs3443.fall_2024_helloworld.model;

import static java.text.NumberFormat.Field.PERCENT;

import java.io.Serializable;
import java.text.NumberFormat;

public class MortgageCalculation extends Calculation implements Serializable {

    private static final int MONTHS_IN_YEARS = 12;
    private double loanAmount;
    private double loanAPR ;
    private double  loanYears;
    private double depositAmount;
    private double propertyTaxes;
    private double insurance;
    private double pmi;
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

    public double getPmi() {
        return pmi;
    }

    public MortgageCalculation(double loanAmount, double loanAPR, double loanYears, double depositAmount, double propertyTaxes, double insurance, double pmi   ) {
        this.loanAmount = loanAmount;
        this.loanAPR = loanAPR;
        this.loanYears = loanYears;
        this.depositAmount = depositAmount;
        this.propertyTaxes = propertyTaxes;
        this.insurance = insurance;
        this.pmi = pmi;
        this.monthlyInterestRate = loanAPR / 100 / MONTHS_IN_YEARS;
        this.numberOfPayments = loanYears * MONTHS_IN_YEARS;
        this.monthlyPayment = calculateMonthlyPayment();
        this.totalInterestPaid = (monthlyPayment * numberOfPayments) - loanAmount;
        this.totalCostOfMortgage = totalInterestPaid + loanAmount;

    }


    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanAPR() {
        return loanAPR;
    }

    public void setLoanAPR(float loanAPR) {
        this.loanAPR = loanAPR;
    }

    public double getPrivateloanYears() {
        return loanYears;
    }

    public void setPrivateloanYears(int privateloanYears) {
        this.loanYears = privateloanYears;
    }


    public void setMonthlyPayments(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalCostOfMortgage() {
        return totalCostOfMortgage;
    }

    public double calculateMonthlyPayment() {

        double mathPower = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double otherMonthlyCosts = ((propertyTaxes + insurance) / 12) + pmi;
        double mPayments = (loanAmount - depositAmount) * (monthlyInterestRate * mathPower / (mathPower - 1));

        return mPayments + otherMonthlyCosts;

    }

}

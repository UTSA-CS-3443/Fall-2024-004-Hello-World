package edu.utsa.cs3443.fall_2024_helloworld.model;

import static java.text.NumberFormat.Field.PERCENT;

import java.io.Serializable;
import java.text.NumberFormat;

public class MortgageCalculation extends Calculation implements Serializable {

    private static final int MONTHS_IN_YEARS = 12;
    private float loanAmount;
    private float loanAPR ;
    private int  loanYears;
    private float depositAmount;
    private float propertyTaxes;
    private float insurance;
    private float pmi;
    private double monthlyInterestRate;
    private int numberOfPayments;
    private Double monthlyPayment;
    private double totalInterestPaid;
    private double totalCostOfMortgage;

    public int getLoanYears() {
        return loanYears;
    }

    public float getDepositAmount() {
        return depositAmount;
    }

    public float getPropertyTaxes() {
        return propertyTaxes;
    }

    public float getInsurance() {
        return insurance;
    }

    public float getPmi() {
        return pmi;
    }

    public MortgageCalculation(float loanAmount, float loanAPR, int loanYears, float depositAmount, float propertyTaxes, float insurance, float pmi   ) {
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


    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public float getLoanAPR() {
        return loanAPR;
    }

    public void setLoanAPR(float loanAPR) {
        this.loanAPR = loanAPR;
    }

    public int getPrivateloanYears() {
        return loanYears;
    }

    public void setPrivateloanYears(int privateloanYears) {
        this.loanYears = privateloanYears;
    }


    public void setMonthlyPayments(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalCostOfMortgage() {
        return totalCostOfMortgage;
    }

    public Double calculateMonthlyPayment() {

        double mathPower = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double otherMonthlyCosts = ((propertyTaxes + insurance) / 12) + pmi;
        double mPayments = (loanAmount - depositAmount) * (monthlyInterestRate * mathPower / (mathPower - 1));

        return mPayments + otherMonthlyCosts;

    }

}

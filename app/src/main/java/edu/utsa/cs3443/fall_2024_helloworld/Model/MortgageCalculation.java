package edu.utsa.cs3443.fall_2024_helloworld.model;

import static java.text.NumberFormat.Field.PERCENT;

import java.text.NumberFormat;

public class MortgageCalculation extends Calculation {

    private static final int MONTHS_IN_YEARS = 12;
    private float loanAmount;
    private float loanAPR ;
    private int  loanYears;
    private double monthlyInterestRate;
    private int numberOfPayments;
    private String monthlyPayment;

    public MortgageCalculation(float loanAmount, float loanAPR, int loanYears) {
        this.loanAmount = loanAmount;
        this.loanAPR = loanAPR;
        this.loanYears = loanYears;
        this.monthlyInterestRate = loanAPR / 100 / MONTHS_IN_YEARS;
        this.numberOfPayments = loanYears * MONTHS_IN_YEARS;
        this.monthlyPayment = calculateMonthlyPayment();

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


    public void setMonthlyPayments(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public String calculateMonthlyPayment() {

        double mathPower = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double mPayments= loanAmount * (monthlyInterestRate * mathPower / (mathPower - 1));

        return NumberFormat.getCurrencyInstance().format(mPayments);

    }

}

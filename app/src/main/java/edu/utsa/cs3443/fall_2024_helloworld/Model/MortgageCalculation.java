package edu.utsa.cs3443.fall_2024_helloworld.model;

public class MortgageCalculation extends edu.utsa.cs3443.fall_2024_helloworld.Model.MainCalc {

    private float loanAmount;
    private float loanAPR ;
    private int  loanYears;
    private float monthlyPayment;
    private float annualPayment;

    public MortgageCalculation(float loanAmount, float loanAPR, int loanYears) {
        this.loanAmount = loanAmount;
        this.loanAPR = loanAPR;
        this.loanYears = loanYears;
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

    public float getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(float monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public float getAnnualPayment() {
        return annualPayment;
    }

    public void setAnnualPayment(float annualPayment) {
        this.annualPayment = annualPayment;
    }
}

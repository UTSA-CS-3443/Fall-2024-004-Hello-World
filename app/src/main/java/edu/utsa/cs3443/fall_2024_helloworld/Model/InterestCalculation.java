package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;


public class InterestCalculation extends Calculation implements Serializable {
    private double principal;
    private double interestRate;
    private int timesCompoundedPerYear;
    private double years;
    private double compoundInterest;
    private double continuousCompoundInterest;

    public InterestCalculation(double principal, double interestRate, int timesCompoundedPerYear, double years) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.timesCompoundedPerYear = timesCompoundedPerYear;
        this.years = years;

        calculateInterest();
    }

    public double getCompoundInterest() {
        return compoundInterest;
    }

    public double getContinuousCompoundInterest() {
        return continuousCompoundInterest;
    }

    public void calculateInterest() {
        this.compoundInterest = calcCompoundInterest(principal, interestRate, timesCompoundedPerYear, years);
        this.continuousCompoundInterest = calcContinuousCompoundInterest(principal, interestRate, years);
    }
}

package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/*** Calculate the compound interest and continuous compound interest
 *
 * @author Joseph
 */
public class InterestCalculation extends Calculation implements Serializable {
    private double principal;
    private double interestRate;
    private int timesCompoundedPerYear;
    private double years;
    private double compoundInterest;
    private double continuousCompoundInterest;
    /***
     * Constructor for the InterestCalculation class
     * @param principal the initial balance
     * @param interestRate the interest rate as a value 1<x<0
     * @param timesCompoundedPerYear the times compounded per year, for example 12 for monthly, or 53 for weekly
     * @param years the number of years
     */
    public InterestCalculation(double principal, double interestRate, int timesCompoundedPerYear, double years) {
        this.principal = principal;
        this.interestRate = interestRate;
        this.timesCompoundedPerYear = timesCompoundedPerYear;
        this.years = years;

        calculateInterest();
    }
    /***
     * Get the compound interest
     * @return the compound interest
     */
    public double getCompoundInterest() {
        return compoundInterest;
    }
    /***
     * Get the continuous compound interest
     * @return the continuous compound interest
     */
    public double getContinuousCompoundInterest() {
        return continuousCompoundInterest;
    }
    /***
     * Calculate the compound interest
     */
    public void calculateInterest() {
        this.compoundInterest = calcCompoundInterest(principal, interestRate, timesCompoundedPerYear, years);
        this.continuousCompoundInterest = calcContinuousCompoundInterest(principal, interestRate, years);
    }
}

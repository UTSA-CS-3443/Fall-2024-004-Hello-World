package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/*** Calculate the compound interest and continuous compound interest
 *
 * @author Joseph
 */
public class InterestCalculation extends Calculation implements Serializable {

    private int timesCompoundedPerYear;
    private double compoundInterest;
    private double continuousCompoundInterest;
    /***
     * Constructor for the InterestCalculation class
     * @param loanAmount the initial balance
     * @param loanAPR the interest rate as a value 1<x<0
     * @param timesCompoundedPerYear the times compounded per year, for example 12 for monthly, or 53 for weekly
     * @param loanYears the number of years
     */
    public InterestCalculation(double loanAmount, double loanAPR, double loanYears, int timesCompoundedPerYear) {
        super(loanAmount,loanAPR,loanYears);
        this.timesCompoundedPerYear = timesCompoundedPerYear;

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
        this.compoundInterest = calcCompoundInterest(super.getLoanAmount(), super.getLoanAPR(), timesCompoundedPerYear, super.getLoanYears());
        this.continuousCompoundInterest = calcContinuousCompoundInterest(super.getLoanAmount(), super.getLoanAPR(), super.getLoanYears());
    }
}

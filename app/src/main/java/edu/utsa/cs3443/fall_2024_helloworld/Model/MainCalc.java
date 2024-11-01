package edu.utsa.cs3443.fall_2024_helloworld.Model;

/** This class is meant to server as a foundation class where other calculator class
 * will extend from.
 *
 * Authors: Collaborative effort of the team
 */
public class MainCalc {

    private static MainCalc _instance;
    public static MainCalc Instance(){
        if(_instance == null){
            _instance = new MainCalc();
        }
        return _instance;
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

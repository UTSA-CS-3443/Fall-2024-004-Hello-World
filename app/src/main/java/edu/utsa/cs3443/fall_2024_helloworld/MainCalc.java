package edu.utsa.cs3443.fall_2024_helloworld;

/** This class is meant to server as a foundation class where other calculator class
 * will extend from.
 *
 * Authors: Collaborative Effort of the team
 */
public class MainCalc {
    public double calcMonthlyInterestRate(double annualRate){
        return annualRate / 12;

    }
    public double calcMortgageLoanAmount(double propertyPrice, double deposit){
        return propertyPrice - deposit;
    }
}

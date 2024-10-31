package edu.utsa.cs3443.fall_2024_helloworld;

/** This class is meant to server as a foundation class where other calculator class
 * will extend from.
 *
 * Authors: Collaborative effort of the team
 */
public class MainCalc {
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
}

package edu.utsa.cs3443.fall_2024_helloworld.Model;

import java.io.Serializable;

/**
 * This class is meant to server as a foundation class where other calculator class
 * will extend from.
 * <p>
 * Authors: Collaborative effort of the team
 */
public abstract class Calculation implements Serializable {
    private double loanAmount;
    private double loanAPR;
    private double loanYears;


    /**
     * Instantiates a new Calculation.
     *
     * @param loanAmount the loan amount
     * @param loanAPR    the loan apr
     * @param loanYears  the loan years
     */
    public Calculation(double loanAmount, double loanAPR, double loanYears) {
        this.loanAmount = loanAmount;
        this.loanAPR = loanAPR;
        this.loanYears = loanYears;
    }

    /**
     * Instantiates a new Calculation.
     *
     * @param loanAmount the loan amount
     * @param loanAPR    the loan apr
     */
    public Calculation(double loanAmount, double loanAPR) {
        this.loanAmount = loanAmount;
        this.loanAPR = loanAPR;

    }


    /**
     * Gets loan amount.
     *
     * @return the loan amount
     */
    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * Gets loan apr.
     *
     * @return the loan apr
     */
    public double getLoanAPR() {
        return loanAPR;
    }

    /**
     * Gets loan years.
     *
     * @return the loan years
     */
    public double getLoanYears() {
        return loanYears;
    }



}

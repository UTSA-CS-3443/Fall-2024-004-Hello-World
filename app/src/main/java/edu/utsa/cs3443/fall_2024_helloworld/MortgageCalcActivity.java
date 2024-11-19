package edu.utsa.cs3443.fall_2024_helloworld;
import static edu.utsa.cs3443.fall_2024_helloworld.Model.ViewMethods.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Arrays;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

import edu.utsa.cs3443.fall_2024_helloworld.History.HistoryManager;
import edu.utsa.cs3443.fall_2024_helloworld.Model.MortgageCalculation;

/*** MortgageCalcActivity class to handle the mortgage calculator activity
 *
 * @author Wheeler
 */
public class MortgageCalcActivity extends AppCompatActivity implements View.OnClickListener {

    /***
     * The Fields for the mortgage calculator
     */
    int[] fields = {R.id.loanAmount,R.id.loanApr,R.id.loanYears,R.id.deposit,R.id.propertyTaxes,R.id.insurance,R.id.pmi};
    /**
     * The Result fields for the mortgage calculator
     */
    int[] resultFields = {R.id.totalInterestPaid,R.id.totalCostMortgage,R.id.totalMonthlyPayment,R.id.monthsToPayOff};
    /**
     * The Loan amount.
     */
    double loanAmount;
    /**
     * The Loan apr.
     */
    double loanAPR;
    /**
     * The Loan years.
     */
    double loanYears;
    /**
     * The Loan deposit.
     */
    double loanDeposit;
    /**
     * The Loan property tax.
     */
    double loanPropertyTax;
    /**
     * The Loan insurance.
     */
    double loanInsurance;
    /**
     * The Loan pmi.
     */
    double loanPMI;
    /**
     * The Loan extra payment.
     */
    double loanExtraPayment;
    /***
     * Method to handle the on create event
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calc);
        //set up the back and Submit buttons
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        //hide the result fields
        for(int r : resultFields){
            hideField(r,this);
        }
        //if the intent has an extra index then set the fields to the values of the history item passed with intent
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            //get the history item
            MortgageCalculation calculation = (MortgageCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);
            //set the fields to the values of the history item
            setField(fields[0],String.valueOf(calculation.getLoanAmount()),this);
            setField(fields[1],String.valueOf(calculation.getLoanAPR()),this);
            setField(fields[2],String.valueOf(calculation.getLoanYears()),this);
            setField(fields[3],String.valueOf(calculation.getDepositAmount()),this);
            setField(fields[4],String.valueOf(calculation.getPropertyTaxes()),this);
            setField(fields[5],String.valueOf(calculation.getInsurance()),this);
            setField(fields[6],String.valueOf(calculation.getPmi()),this);
            setField(R.id.extraPayments,String.valueOf(calculation.getExtraPayment()),this);
        }

    }
    /***
     * Method to handle the on click event
     * @param v the view
     */
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        //if the submit button is clicked then calculate the mortgage
        if(v.getId() == R.id.submit){
            //check if any fields are empty
            if (Arrays.stream(fields).anyMatch(n -> getTextEdit(n,this).isBlank())) {
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            //get the values from the text fields
            loanAmount = Double.parseDouble(getTextEdit(fields[0],this));
            loanAPR = Double.parseDouble(getTextEdit(fields[1],this));
            loanYears = Double.parseDouble(getTextEdit(fields[2],this));
            loanDeposit = Double.parseDouble(getTextEdit(fields[3],this));
            loanPropertyTax = Double.parseDouble(getTextEdit(fields[4],this));
            loanInsurance = Double.parseDouble(getTextEdit(fields[5],this));
            loanPMI = Double.parseDouble(getTextEdit(fields[6],this));
            MortgageCalculation mCalc = new MortgageCalculation(loanAmount, loanAPR, loanYears,loanDeposit,loanPropertyTax,loanInsurance,loanPMI);
            if(!getTextEdit(R.id.extraPayments,this).isBlank() ){
                mCalc.setExtraPayment(Double.parseDouble(getTextEdit(R.id.extraPayments,this)));
            }
            mCalc.calculateExtraPayment();
            //remove the input fields from the view
            for(int f : fields){
                removeField(f,this);
            }
            //show the result fields
            for(int r : resultFields){
                showField(r,this);
            }
            //disable the submit button and show the results
            hideField(R.id.extraPayments,this);
            disableButton(R.id.submit,this);
            String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mCalc.getTotalInterestPaid());
            setField(R.id.totalInterestPaid,"Total Interest Paid: " + mPaymentsFormatted,this);
            mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mCalc.getTotalCostOfMortgage());
            setField(R.id.totalCostMortgage,"Total Cost of Loan: " + mPaymentsFormatted,this);
            mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mCalc.getMonthlyPayment());
            setField(R.id.totalMonthlyPayment,"Total Monthly Payment: " + mPaymentsFormatted,this);
            setField(R.id.monthsToPayOff,"Months Till Payoff: " + mCalc.getMonthsTillPaidOff(),this);
            HistoryManager.Instance().addHistoryItem(mCalc);
        }
    }
}

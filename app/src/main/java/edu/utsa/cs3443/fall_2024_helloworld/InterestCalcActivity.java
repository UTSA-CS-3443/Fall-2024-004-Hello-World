package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static edu.utsa.cs3443.fall_2024_helloworld.Model.ViewMethods.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Arrays;

import edu.utsa.cs3443.fall_2024_helloworld.History.HistoryManager;
import edu.utsa.cs3443.fall_2024_helloworld.Model.AutoLoanCalculation;
import edu.utsa.cs3443.fall_2024_helloworld.Model.InterestCalculation;

/*** InterestCalcActivity class to handle the interest calculator activity
 *
 * @author Wheeler
 */
public class InterestCalcActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * The Fields IDs for the interest calculator
     */
    int[] fields ={R.id.currentBalance,R.id.monthlyPayment,R.id.creditApr};
    /**
     * The Result fields IDs for the interest calculator
     */
    int[] resultFields = {R.id.interestTotalPaid,R.id.interestTotalCost,R.id.interestMonthsToPayOff};

   /***
     * On create method for the interest calculator
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_calc);
        // Set up the Back and Submit buttons
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        // Hide the result fields
        for(int r : resultFields){
            hideField(r,this);
        }
        // If the intent has an extra index, set the fields to the values of the calculation
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            InterestCalculation calculation = (InterestCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);
            // Set the fields to the values of the calculation
            setField(fields[0],String.valueOf(calculation.getLoanAmount()),this);
            setField(fields[1],String.valueOf(calculation.getMonthlyPaymentAmount()),this);
            setField(fields[2],String.valueOf(calculation.getLoanAPR()),this);


        }

    }
    /***
     * On click method for the interest calculator
     * @param v the view
     */
    @Override
    public void onClick(View v) {

        // If the back button is clicked, go back to the main activity
        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        // If the submit button is clicked, calculate the interest
        if(v.getId() == R.id.submit){

            double loanAmount = Double.parseDouble(getTextEdit(fields[0],this));
            double  loanMonthlyPayments = Double.parseDouble(getTextEdit(fields[1],this));
            double loanARP = Double.parseDouble(getTextEdit(fields[2],this));
            InterestCalculation iCalc = new InterestCalculation(loanAmount,loanARP,loanMonthlyPayments);

            // If any fields are blank, show a toast message
            if (Arrays.stream(fields).anyMatch(n -> getTextEdit(n,this).isBlank())) {
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            iCalc.calculateInterest();
            // If the extra monthly payment field is not blank, set the extra payment
            for(int f : fields){
                removeField(f,this);
            }
            // Show the result fields
            for(int r : resultFields){
                showField(r,this);
            }
            // Set the result fields to the calculated values
            setField(R.id.interestMonthsToPayOff,"Months Till Payoff: " + iCalc.getMonthsTillPaidOff(),this);
            String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(iCalc.getTotalInterestPaid());
            setField(R.id.interestTotalPaid,"Total Interest Paid: " + mPaymentsFormatted,this);
            mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(iCalc.getTotalCostOfLoan());
            setField(R.id.interestTotalCost,"Total Cost of Loan: " + mPaymentsFormatted,this);
            // Add the calculation to the history
            HistoryManager.Instance().addHistoryItem(iCalc);
        }

    }


}
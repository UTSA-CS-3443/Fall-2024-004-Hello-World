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

/*** AutoLoanCalcActivity class to handle the auto loan calculator activity
 *
 * @author Wheeler
 */
public class AutoLoanCalcActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * The Fields.
     */
    int[] fields ={R.id.autoloanAmount,R.id.autoloanAPR,R.id.autoloanYears};
    /**
     * The Other fields.
     */
    int[] otherFields = {R.id.autotradeInValue,R.id.autodownPayment,R.id.autoextraMonthlyAmount};
    /**
     * The Result fields.
     */
    int[] resultFields = {R.id.autoTotalInterestPaid,R.id.autoTotalCost,R.id.autoTotalMonthlyPayment,R.id.autoMonthsToPayOff};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_loan_calc);
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        for(int r : resultFields){
            hideField(r,this);
        }
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            AutoLoanCalculation calculation = (AutoLoanCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);

            setField(R.id.autotradeInValue,String.valueOf(calculation.getTradeInValue()),this);
            setField(fields[0],String.valueOf(calculation.getLoanAmount()),this);
            setField(fields[1],String.valueOf(calculation.getLoanAPR()),this);
            setField(fields[2],String.valueOf(calculation.getLoanYears()),this);
            setField(R.id.autodownPayment,String.valueOf(calculation.getDownPayment()),this);
            setField(R.id.autoextraMonthlyAmount,String.valueOf(calculation.getExtraPayment()),this);

        }

    }

    @Override
    public void onClick(View v) {
        //When backbutton is clicked, return to main activity
        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        //When submit button is clicked, calculate the auto loan payments
        if(v.getId() == R.id.submit){
            //Check if all fields are filled in before calculating
            if (Arrays.stream(fields).noneMatch(n -> getTextEdit(n,this).isBlank())) {
                double loanAmount = Double.parseDouble(getTextEdit(fields[0],this));
                double loanARP = Double.parseDouble(getTextEdit(fields[1],this));
                double loanYears = Double.parseDouble(getTextEdit(fields[2],this));
                if (Arrays.stream(fields).anyMatch(n -> getTextEdit(n,this).isBlank())) {
                    Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Create AutoLoanCalculation object and calculate loan payments
                AutoLoanCalculation aCalc = new AutoLoanCalculation(loanAmount,loanARP,loanYears);
                //Check if optional fields are filled in and set them
                if(!getTextEdit(R.id.autoextraMonthlyAmount,this).isBlank() ){
                    aCalc.setExtraPayment(Double.parseDouble(getTextEdit(R.id.autoextraMonthlyAmount,this)));
                }
                if(!getTextEdit(R.id.autotradeInValue,this).isBlank() ){
                    aCalc.setTradeInValue(Double.parseDouble(getTextEdit(R.id.autotradeInValue,this)));
                }
                if(!getTextEdit(R.id.autodownPayment,this).isBlank() ){
                    aCalc.setDownPayment(Double.parseDouble(getTextEdit(R.id.autodownPayment,this)));
                }
                aCalc.calculateLoanPayments();
                //Hide fields that are not needed and show the results
                for(int f : fields){
                    removeField(f,this);
                }
                //Hide optional fields
                for(int o : otherFields){
                    removeField(o,this);
                }
                //Show results
                for(int r : resultFields){
                    showField(r,this);
                }
                //Disable submit button after calculation.
                disableButton(R.id.submit,this);
                String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(aCalc.getTotalInterestPaid());
                setField(R.id.autoTotalInterestPaid,"Total Interest Paid: " + mPaymentsFormatted,this);
                mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(aCalc.getTotalCostOfLoan());
                setField(R.id.autoTotalCost,"Total Cost of Loan: " + mPaymentsFormatted,this);
                mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(aCalc.getMonthlyPayment());
                setField(R.id.autoTotalMonthlyPayment,"Total Monthly Payment: " + mPaymentsFormatted,this);
                setField(R.id.autoMonthsToPayOff,"Months Till Payoff: " + aCalc.getMonthsTillPaidOff(),this);
                HistoryManager.Instance().addHistoryItem(aCalc);
            } else {
                //If not all fields are filled in, show a toast message
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
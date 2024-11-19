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
    int[] fields ={R.id.currentBalance,R.id.monthlyPayment,R.id.creditApr};
    int[] resultFields = {R.id.interestTotalPaid,R.id.interestTotalCost,R.id.interestMonthsToPayOff};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interest_calc);
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        for(int r : resultFields){
            hideField(r,this);
        }
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            InterestCalculation calculation = (InterestCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);

            setField(fields[0],String.valueOf(calculation.getLoanAmount()),this);
            setField(fields[1],String.valueOf(calculation.getMonthlyPaymentAmount()),this);
            setField(fields[2],String.valueOf(calculation.getLoanAPR()),this);


        }

    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.submit){

            double loanAmount = Double.parseDouble(getTextEdit(fields[0],this));
            double  loanMonthlyPayments = Double.parseDouble(getTextEdit(fields[1],this));
            double loanARP = Double.parseDouble(getTextEdit(fields[2],this));
            InterestCalculation iCalc = new InterestCalculation(loanAmount,loanARP,loanMonthlyPayments);
            if (Arrays.stream(fields).anyMatch(n -> getTextEdit(n,this).isBlank())) {
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            iCalc.calculateInterest();
            for(int f : fields){
                removeField(f,this);
            }

            for(int r : resultFields){
                showField(r,this);
            }
            setField(R.id.interestMonthsToPayOff,"Months Till Payoff: " + iCalc.getMonthsTillPaidOff(),this);
            String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(iCalc.getTotalInterestPaid());
            setField(R.id.interestTotalPaid,"Total Interest Paid: " + mPaymentsFormatted,this);
            mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(iCalc.getTotalCostOfLoan());
            setField(R.id.interestTotalCost,"Total Cost of Loan: " + mPaymentsFormatted,this);

            HistoryManager.Instance().addHistoryItem(iCalc);
        }

    }


}
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


public class MortgageCalcActivity extends AppCompatActivity implements View.OnClickListener {

    int[] fields = {R.id.loanAmount,R.id.loanApr,R.id.loanYears,R.id.deposit,R.id.propertyTaxes,R.id.insurance,R.id.pmi};
    int[] resultFields = {R.id.totalInterestPaid};
    double loanAmount;
    double loanAPR;
    double loanYears;
    double loanDeposit;
    double loanPropertyTax;
    double loanInsurance;
    double loanPMI;
    double loanExtraPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calc);
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        for(int r : resultFields){
            hideField(r,this);
        }
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            MortgageCalculation calculation = (MortgageCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);

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

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        if(v.getId() == R.id.submit){

            if (Arrays.stream(fields).anyMatch(n -> getTextEdit(n,this).isBlank())) {
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

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
            for(int f : fields){
                removeField(f,this);
            }

            for(int r : resultFields){
                showField(r,this);
            }
            hideField(R.id.extraPayments,this);
            disableButton(R.id.submit,this);
            String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mCalc.getTotalInterestPaid());
            setField(R.id.totalInterestPaid,"Total Interest Paid: " + mPaymentsFormatted,this);
            HistoryManager.Instance().addHistoryItem(mCalc);
        }



    }




}

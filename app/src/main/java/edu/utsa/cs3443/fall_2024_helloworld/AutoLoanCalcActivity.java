package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static edu.utsa.cs3443.fall_2024_helloworld.Model.ViewMethods.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import edu.utsa.cs3443.fall_2024_helloworld.History.HistoryManager;
import edu.utsa.cs3443.fall_2024_helloworld.Model.AutoLoanCalculation;

public class AutoLoanCalcActivity extends AppCompatActivity implements View.OnClickListener {
    int[] fields ={R.id.tradeInValue,R.id.downPayment,R.id.loanAPR,R.id.totalCost};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_loan_calc);
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            AutoLoanCalculation calculation = (AutoLoanCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);

//            setField(fields[0],String.valueOf(calculation.getTradeInValue()),this);
//            setField(fields[1],String.valueOf(calculation.getGetDownPayment()),this);
//            setField(fields[2],String.valueOf(calculation.getLoanAPR()),this);
//            setField(fields[3],String.valueOf(calculation.getTotalCost()),this);
//            setField(fields[4],String.valueOf(calculation.getExtraPayment()),this);

        }

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.submit){

            if (Arrays.stream(fields).noneMatch(n -> getTextEdit(n,this).isBlank())) {
                double tradeInValue = Double.parseDouble(getTextEdit(fields[0],this));
                double downPayment = Double.parseDouble(getTextEdit(fields[1],this));
                double loanARP = Double.parseDouble(getTextEdit(fields[2],this));
                double totalCost = Double.parseDouble(getTextEdit(fields[3],this));
                double extraPayment = Double.parseDouble(getTextEdit(fields[4],this));
                //AutoLoanCalculation aCalc = new AutoLoanCalculation(tradeInValue, downPayment, loanARP,totalCost,extraPayment);
                if(!getTextEdit(R.id.extraMonthlyAmount,this).isBlank() ){
                   // aCalc.setExtraPayment(Double.parseDouble(getTextEdit(R.id.extraPayments,this)));
                }
                //HistoryManager.Instance().addHistoryItem(aCalc);
            } else {
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
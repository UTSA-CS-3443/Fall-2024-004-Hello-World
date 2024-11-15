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
    int[] fields ={R.id.downPayment,R.id.loanAPR,R.id.totalCost};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_loan_calc);
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.submit,this);
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            AutoLoanCalculation calculation = (AutoLoanCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);

            setField(R.id.tradeInValue,String.valueOf(calculation.getTradeInValue()),this);
            setField(fields[0],String.valueOf(calculation.getDownPayment()),this);
            setField(fields[1],String.valueOf(calculation.getLoanARP()),this);
            setField(fields[2],String.valueOf(calculation.getTotalCost()),this);
            setField(R.id.extraMonthlyAmount,String.valueOf(calculation.getExtraPayment()),this);

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
                double downPayment = Double.parseDouble(getTextEdit(fields[0],this));
                double loanARP = Double.parseDouble(getTextEdit(fields[1],this));
                double totalCost = Double.parseDouble(getTextEdit(fields[2],this));

                AutoLoanCalculation aCalc = new AutoLoanCalculation(downPayment, loanARP,totalCost);
                if(!getTextEdit(R.id.extraMonthlyAmount,this).isBlank() ){
                    aCalc.setExtraPayment(Double.parseDouble(getTextEdit(R.id.extraPayments,this)));
                }
                if(!getTextEdit(R.id.tradeInValue,this).isBlank() ){
                    aCalc.setTradeInValue(Double.parseDouble(getTextEdit(R.id.tradeInValue,this)));
                }
                HistoryManager.Instance().addHistoryItem(aCalc);
            } else {
                Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
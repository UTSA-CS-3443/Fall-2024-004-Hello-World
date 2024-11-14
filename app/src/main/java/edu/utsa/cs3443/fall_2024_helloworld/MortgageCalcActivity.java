package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Arrays;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

import edu.utsa.cs3443.fall_2024_helloworld.model.MortgageCalculation;


public class MortgageCalcActivity extends AppCompatActivity implements View.OnClickListener {

    int[] fields = {R.id.loanAmount,R.id.loanApr,R.id.loanYears,R.id.deposit,R.id.propertyTaxes,R.id.insurance,R.id.pmi};
    float loanAmount;
    float loanAPR;
    int loanYears;
    float loanDeposit;
    float loanPropertyTax;
    float loanInsurance;
    float loanPMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calc);
        setUpButton(R.id.backbutton);
        setUpButton(R.id.submit);



    }





    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        if(v.getId() == R.id.submit){

                if (Arrays.stream(fields).noneMatch(n -> getTextEdit(n).isBlank())) {
                    loanAmount = Float.parseFloat(getTextEdit(fields[0]));
                    loanAPR = Float.parseFloat(getTextEdit(fields[1]));
                    loanYears = Integer.parseInt(getTextEdit(fields[2]));
                    loanDeposit = Float.parseFloat(getTextEdit(fields[3]));
                    loanPropertyTax = Float.parseFloat(getTextEdit(fields[4]));
                    loanInsurance = Float.parseFloat(getTextEdit(fields[5]));
                    loanPMI = Float.parseFloat(getTextEdit(fields[6]));
                    MortgageCalculation mCalc = new MortgageCalculation(loanAmount, loanAPR, loanYears,loanDeposit,loanPropertyTax,loanInsurance,loanPMI);
                    String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mCalc.getMonthlyPayment());
                    Toast.makeText(v.getContext(), mPaymentsFormatted, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }

        }



    }

    private void setUpButton(int buttonID){
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }
    private void disableButton(int buttonID){
        Button button = findViewById(buttonID);
        button.setEnabled(false);
    }
    private void enableButton(int buttonID){
        Button button = findViewById(buttonID);
        button.setEnabled(true);
    }

    private String getTextEdit(int editID){
        EditText mEdit = findViewById(editID);
        return mEdit.getEditableText().toString();
    }
}

package edu.utsa.cs3443.fall_2024_helloworld;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        setUpButton(R.id.backbutton);
        setUpButton(R.id.submit);
        for(int r : resultFields){
            hideField(r);
        }
        if(getIntent().hasExtra("Index")) {
            int historyIndex = Integer.parseInt(getIntent().getStringExtra("Index"));
            MortgageCalculation calculation = (MortgageCalculation) HistoryManager.Instance().getHistoryItems().get(historyIndex);

            setField(fields[0],String.valueOf(calculation.getLoanAmount()));
            setField(fields[1],String.valueOf(calculation.getLoanAPR()));
            setField(fields[2],String.valueOf(calculation.getLoanYears()));
            setField(fields[3],String.valueOf(calculation.getDepositAmount()));
            setField(fields[4],String.valueOf(calculation.getPropertyTaxes()));
            setField(fields[5],String.valueOf(calculation.getInsurance()));
            setField(fields[6],String.valueOf(calculation.getPmi()));
            setField(R.id.extraPayments,String.valueOf(calculation.getExtraPayment()));
        }

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


        if(v.getId() == R.id.submit){

                if (Arrays.stream(fields).noneMatch(n -> getTextEdit(n).isBlank())) {
                    loanAmount = Double.parseDouble(getTextEdit(fields[0]));
                    loanAPR = Double.parseDouble(getTextEdit(fields[1]));
                    loanYears = Double.parseDouble(getTextEdit(fields[2]));
                    loanDeposit = Double.parseDouble(getTextEdit(fields[3]));
                    loanPropertyTax = Double.parseDouble(getTextEdit(fields[4]));
                    loanInsurance = Double.parseDouble(getTextEdit(fields[5]));
                    loanPMI = Double.parseDouble(getTextEdit(fields[6]));
                    MortgageCalculation mCalc = new MortgageCalculation(loanAmount, loanAPR, loanYears,loanDeposit,loanPropertyTax,loanInsurance,loanPMI);
                    if(!getTextEdit(R.id.extraPayments).isBlank() ){
                        mCalc.setExtraPayment(Double.parseDouble(getTextEdit(R.id.extraPayments)));
                    }
                    mCalc.calculateExtraPayment();
                    for(int f : fields){
                        removeField(f);
                    }

                    for(int r : resultFields){
                        showField(r);
                    }
                    hideField(R.id.extraPayments);
                    disableButton(R.id.submit);
                    String mPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mCalc.getTotalInterestPaid());
                    setField(R.id.totalInterestPaid,"Total Interest Paid: " + mPaymentsFormatted);
                    HistoryManager.Instance().addHistoryItem(mCalc);
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

    private String getTextEdit(int editID){
        EditText mEdit = findViewById(editID);
        return mEdit.getEditableText().toString();
    }

    private void setField(int filedId, String d){
        EditText field = findViewById(filedId);
        field.setText(d);

    }
    private void removeField(int fieldId){

        EditText editText = findViewById(fieldId);
        ((ViewGroup) editText.getParent()).removeView(editText);
    }
    private void showField(int fieldId){

        EditText editText = findViewById(fieldId);
        editText.setVisibility(VISIBLE);
    }
    private void hideField(int fieldId){

        EditText editText = findViewById(fieldId);
        editText.setVisibility(INVISIBLE);
    }

}

package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Mortgage_Calc extends AppCompatActivity implements View.OnClickListener {

    int[] fields = {R.id.loanAmount,R.id.loanApr,R.id.loanYears};
    float loanAmount;
    float loanAPR;
    int loanYears;
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
            //intent.putExtra(key,passedValue);
            startActivity(intent);
        }
        if(!getTextEdit(fields[0]).equalsIgnoreCase("") && !getTextEdit(fields[1]).equalsIgnoreCase("") && !getTextEdit(fields[2]).equalsIgnoreCase("")){
            enableButton(R.id.submit);

        } else {
            disableButton(R.id.submit);
        }
        if(v.getId() == R.id.submit){
            loanAmount = Float.parseFloat(getTextEdit(fields[0]));
            loanAPR = Float.parseFloat(getTextEdit(fields[1]));
            loanYears = Integer.parseInt(getTextEdit(fields[2]));

        }



        if(v.getId() == R.id.submit){
            if(!getTextEdit(fields[0]).equalsIgnoreCase("") && !getTextEdit(fields[1]).equalsIgnoreCase("") && !getTextEdit(fields[2]).equalsIgnoreCase("")) {
                loanAmount = Float.parseFloat(getTextEdit(fields[0]));
                loanAPR = Float.parseFloat(getTextEdit(fields[1]));
                loanYears = Integer.parseInt(getTextEdit(fields[2]));

                Toast.makeText(v.getContext(), String.valueOf(loanAmount/loanYears),Toast.LENGTH_SHORT).show();
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

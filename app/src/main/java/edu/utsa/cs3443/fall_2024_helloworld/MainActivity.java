package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button newBtn;
    String[] calNames = {"Mortgage Calculator", "Auto Loan Calculator", "Interest Calculator", "Recent Calculations"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        androidx.core.splashscreen.SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
                try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setContentView(R.layout.activity_main);
        for(String cal : calNames){
            addButton(cal);
        }
    }

    @Override
    public void onClick(View v) {


        if(v.getTag() == calNames[0]){

           // String passedValue = bank.getCurrentQuestionText() + ": " + String.valueOf(bank.getCurrentQuestionAnswer());
            Intent intent = new Intent(this, MortgageCalcActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);

        }
        if(v.getTag() == calNames[1]){

            // String passedValue = bank.getCurrentQuestionText() + ": " + String.valueOf(bank.getCurrentQuestionAnswer());
            Intent intent = new Intent(this, AutoLoanCalcActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);

        }
        if(v.getTag() == calNames[2]){

            // String passedValue = bank.getCurrentQuestionText() + ": " + String.valueOf(bank.getCurrentQuestionAnswer());
            Intent intent = new Intent(this, InterestCalcActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);

        }

        if(v.getTag() == calNames[3]){

            // String passedValue = bank.getCurrentQuestionText() + ": " + String.valueOf(bank.getCurrentQuestionAnswer());
            Intent intent = new Intent(this, HistoryActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);

        }

    }

    private void addButton(String buttonName) {
        LinearLayout layout = findViewById(R.id.buttonLayout);
        int style = R.style.button;

        newBtn = new Button(new ContextThemeWrapper(this, style), null, style);
        newBtn.setText(buttonName);
        newBtn.setHeight(260);
        newBtn.setTag(buttonName);
        newBtn.setOnClickListener(this);

        layout.addView(newBtn);
    }

}
package edu.utsa.cs3443.fall_2024_helloworld;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import static edu.utsa.cs3443.fall_2024_helloworld.Model.viewMethods.*;
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
            addButton(cal,this);
        }

    }

    @Override
    public void onClick(View v) {


        if(v.getTag() == calNames[0]){
            Intent intent = new Intent(this, MortgageCalcActivity.class);
            startActivity(intent);
        }
        if(v.getTag() == calNames[1]){

            Intent intent = new Intent(this, AutoLoanCalcActivity.class);
            startActivity(intent);

        }
        if(v.getTag() == calNames[2]){

            Intent intent = new Intent(this, InterestCalcActivity.class);
            startActivity(intent);

        }

        if(v.getTag() == calNames[3]){

            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);

        }

    }



}
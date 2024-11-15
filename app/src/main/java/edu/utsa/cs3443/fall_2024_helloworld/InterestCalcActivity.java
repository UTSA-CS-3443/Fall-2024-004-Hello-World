package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static edu.utsa.cs3443.fall_2024_helloworld.Model.ViewMethods.*;

import androidx.appcompat.app.AppCompatActivity;

public class InterestCalcActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interest_calc);
        setUpButton(R.id.backbutton,this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);
        }

    }


}
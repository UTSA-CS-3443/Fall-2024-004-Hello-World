package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Interest_Calc extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interest_calc);
        setUpButton(R.id.backbutton);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);
        }

    }

    private void setUpButton(int buttonID){
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }
}
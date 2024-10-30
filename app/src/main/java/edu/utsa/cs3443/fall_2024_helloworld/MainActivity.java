package edu.utsa.cs3443.fall_2024_helloworld;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button newBtn;
    String[] calNames = {"Mortgage Calculator", "Auto Loan Calculator", "Interest Calculator", "Recent Calculations"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        for(String cal : calNames){
            addButton(cal);
        }
    }

    @Override
    public void onClick(View v) {

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
package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.fall_2024_helloworld.History.HistoryManager;
import edu.utsa.cs3443.fall_2024_helloworld.Model.Calculation;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{
    Button newBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        setUpButton(R.id.backbutton);
        HistoryManager.Instance().Load(getApplicationContext().getFilesDir());
        for (int i = 0; i < HistoryManager.Instance().getHistoryItems().size(); i++) {
            addButton(HistoryManager.Instance().getHistoryItems().get(i), i);
        }
    }

    private void setUpButton(int buttonID){
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() != R.id.backbutton) {

            Button clickedButton = (Button) v;
            if(String.valueOf(clickedButton.getText()).equals("MortgageCalculation")){
                Intent intent = new Intent(this, MortgageCalcActivity.class);
                int index = clickedButton.getId();
                intent.putExtra("Index",String.valueOf(index-1000));
                startActivity(intent);
            }


        }


        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        HistoryManager.Instance().Save(getApplicationContext().getFilesDir());
    }

    private void addButton(Calculation calculation, int index) {
        LinearLayout layout = findViewById(R.id.HistorybuttonLayout);
        int style = R.style.button;

        newBtn = new Button(new ContextThemeWrapper(this, style), null, style);
        String[] objType = calculation.getClass().getName().split("\\.");
        newBtn.setText(getCalculationType(calculation));
        newBtn.setHeight(260);
        newBtn.setTag(calculation);
        newBtn.setId(1000 + index);
        newBtn.setOnClickListener(this);

        layout.addView(newBtn);
    }

    private String getCalculationType(Calculation calculation){
        String[] objType = calculation.getClass().getName().split("\\.");
        return objType[objType.length-1];
    }

}
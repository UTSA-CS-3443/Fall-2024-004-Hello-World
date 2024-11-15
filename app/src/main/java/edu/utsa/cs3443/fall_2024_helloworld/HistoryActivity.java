package edu.utsa.cs3443.fall_2024_helloworld;

import static edu.utsa.cs3443.fall_2024_helloworld.Model.ViewMethods.setUpButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import edu.utsa.cs3443.fall_2024_helloworld.History.HistoryManager;
import edu.utsa.cs3443.fall_2024_helloworld.Model.Calculation;
/*** HistoryActivity class to handle the history activity
 *
 * @author Cole
 * @author Wheeler
 */
public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{
    Button newBtn;
    Map<String,Class<?>> buttonMap = Map.ofEntries(
        Map.entry("MortgageCalculation",MortgageCalcActivity.class),
        Map.entry("AutoLoanCalculation",AutoLoanCalcActivity.class),
        Map.entry("InterestCalculation",InterestCalcActivity.class)
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        setUpButton(R.id.backbutton,this);
        setUpButton(R.id.clearbutton,this);
        HistoryManager.Instance().Load(getApplicationContext().getFilesDir());
        for (int i = HistoryManager.Instance().getHistoryItems().size() - 1; i >= 0 ; i--) {
            addButton(HistoryManager.Instance().getHistoryItems().get(i), i);
        }
    }


    @Override
    public void onClick(View v) {



        if(v.getId() != R.id.backbutton) {

            Button clickedButton = (Button) v;
            if(buttonMap.containsKey(String.valueOf(clickedButton.getText()))){
                Intent intent = new Intent(this, buttonMap.get(String.valueOf(clickedButton.getText())));
                int index = clickedButton.getId();
                intent.putExtra("Index",String.valueOf(index-1000));
                startActivity(intent);
            }
            if(clickedButton.getId() == R.id.clearbutton){
                HistoryManager.Instance().getHistoryItems().clear();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }


            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

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
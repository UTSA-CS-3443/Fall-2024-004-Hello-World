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
    /**
     * The New btn.
     */
    Button newBtn;
    /**
     * The Button map to map the button text to the activity class
     */
    Map<String,Class<?>> buttonMap = Map.ofEntries(
        Map.entry("MortgageCalculation",MortgageCalcActivity.class),
        Map.entry("AutoLoanCalculation",AutoLoanCalcActivity.class),
        Map.entry("InterestCalculation",InterestCalcActivity.class)
    );

    /**
     * On create method to create the history activity
     * @param savedInstanceState
     */
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

    /***
     * On click method to handle the button clicks
     * @param v
     */
    @Override
    public void onClick(View v) {


        // If the button clicked is not the back button
        if(v.getId() != R.id.backbutton) {

            Button clickedButton = (Button) v;
            // If the button clicked is a history button
            if(buttonMap.containsKey(String.valueOf(clickedButton.getText()))){
                Intent intent = new Intent(this, buttonMap.get(String.valueOf(clickedButton.getText())));
                int index = clickedButton.getId();
                intent.putExtra("Index",String.valueOf(index-1000));
                startActivity(intent);
            }
            // If the button clicked is the clear button clear the history
            if(clickedButton.getId() == R.id.clearbutton){
                HistoryManager.Instance().getHistoryItems().clear();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            return;
        }
        // If the back button is clicked go back to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    /***
     * On pause method to save the history
     */
    @Override
    public void onPause(){
        super.onPause();
        HistoryManager.Instance().Save(getApplicationContext().getFilesDir());
    }
    /***
     * Add a button dynamically to the history activity
     * @param calculation
     * @param index
     */
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
    /***
     * Get the calculation type
     * @param calculation
     * @return
     */
    private String getCalculationType(Calculation calculation){
        String[] objType = calculation.getClass().getName().split("\\.");
        return objType[objType.length-1];
    }

}
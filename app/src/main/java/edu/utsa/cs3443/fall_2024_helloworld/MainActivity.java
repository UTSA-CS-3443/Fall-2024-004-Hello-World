package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import static edu.utsa.cs3443.fall_2024_helloworld.Model.ViewMethods.*;

import java.util.LinkedHashMap;
import java.util.Map;

/*** MainActivity class to handle the main activity
 *
 * @author Wheeler
 * @author Cole
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * The Button map to hold the buttons
     */
    Map<String,Class<?>> buttonMap;

    /***
     * Constructor to initialize the button map
     */
    public MainActivity() {
        super();
        // Initialize the button map with the buttons and their respective classes
        buttonMap = new LinkedHashMap<>();
        buttonMap.put("Mortgage Calculator",MortgageCalcActivity.class);
        buttonMap.put("Auto Loan Calculator",AutoLoanCalcActivity.class);
        buttonMap.put("Interest Calculator",InterestCalcActivity.class);
        buttonMap.put("Recent Calculations",HistoryActivity.class);
    }

    /**
     * The Did splash to check if the splash screen has been shown
     */
    static boolean didSplash = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        androidx.core.splashscreen.SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        final View content = findViewById(R.id.linearLayout);
        // Splash screen delay to show the splash screen
        if(!didSplash) {
            didSplash = true;
            content.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException ignored) {}
                            content.getViewTreeObserver().removeOnPreDrawListener(this);
                            return true;
                        }
                    });
        }
        // Set up the buttons for main activity
        for (String cal: buttonMap.keySet()) {
            addButton(cal,this);
        }
    }

    /**
     * On click method to handle the button clicks
     * @param v the view
     */
    @Override
    public void onClick(View v) {

        if(!(v.getTag() instanceof String) || !buttonMap.containsKey(v.getTag())) return;

        Intent intent = new Intent(this, buttonMap.get(v.getTag()));
        startActivity(intent);
    }
}
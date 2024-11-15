package edu.utsa.cs3443.fall_2024_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import static edu.utsa.cs3443.fall_2024_helloworld.Model.viewMethods.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Map<String,Class<?>> buttonMap;

    public MainActivity() {
        super();
        buttonMap = new LinkedHashMap<>();
        buttonMap.put("Mortgage Calculator",MortgageCalcActivity.class);
        buttonMap.put("Auto Loan Calculator",AutoLoanCalcActivity.class);
        buttonMap.put("Interest Calculator",InterestCalcActivity.class);
        buttonMap.put("Recent Calculations",HistoryActivity.class);
    }
    static boolean didSplash = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        androidx.core.splashscreen.SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        final View content = findViewById(R.id.linearLayout);
        // This is garbage, and I'm not sure why we are even doing this.
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
        for (String cal: buttonMap.keySet()) {
            addButton(cal,this);
        }
    }

    /** @noinspection SuspiciousMethodCalls*/
    @Override
    public void onClick(View v) {

        if(!(v.getTag() instanceof String) || !buttonMap.containsKey(v.getTag())) return;

        Intent intent = new Intent(this, buttonMap.get(v.getTag()));
        startActivity(intent);
    }
}
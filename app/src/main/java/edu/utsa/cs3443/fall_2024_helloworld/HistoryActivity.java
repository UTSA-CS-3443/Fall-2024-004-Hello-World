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

import edu.utsa.cs3443.fall_2024_helloworld.History.HistoryManager;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        setUpButton(R.id.backbutton);
        HistoryManager.Instance().Load(getApplicationContext().getFilesDir());
    }

    private void setUpButton(int buttonID){
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backbutton){
            Intent intent = new Intent(this, MainActivity.class);
            //intent.putExtra(key,passedValue);
            startActivity(intent);
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        HistoryManager.Instance().Save(getApplicationContext().getFilesDir());
    }
}
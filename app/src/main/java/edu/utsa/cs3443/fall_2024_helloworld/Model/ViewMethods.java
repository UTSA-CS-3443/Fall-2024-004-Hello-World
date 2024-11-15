package edu.utsa.cs3443.fall_2024_helloworld.Model;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.fall_2024_helloworld.R;

public class ViewMethods extends AppCompatActivity  {



    public static void disableButton(int buttonID, Activity activity){
        Button button = activity.findViewById(buttonID);
        button.setEnabled(false);
    }

    public static String getTextEdit(int editID,Activity activity){
        EditText mEdit = activity.findViewById(editID);
        return mEdit.getEditableText().toString();
    }

    public static void setField(int filedId, String d,Activity activity){
        EditText field = activity.findViewById(filedId);
        field.setText(d);

    }
    public static void removeField(int fieldId,Activity activity){

        EditText editText = activity.findViewById(fieldId);
        ((ViewGroup) editText.getParent()).removeView(editText);
    }
    public static void showField(int fieldId,Activity activity){

        EditText editText = activity.findViewById(fieldId);
        editText.setVisibility(VISIBLE);
    }
    public static void hideField(int fieldId,Activity activity){

        EditText editText = activity.findViewById(fieldId);
        editText.setVisibility(INVISIBLE);
    }
    public static void setUpButton(int buttonID,Activity activity){
        Button button = activity.findViewById(buttonID);
        button.setOnClickListener((View.OnClickListener) activity);
    }

    public static void addButton(String buttonName,Activity activity) {
        Button newBtn;
        LinearLayout layout = activity.findViewById(R.id.buttonLayout);
        int style = R.style.button;

        newBtn = new Button(new ContextThemeWrapper(activity, style), null, style);
        newBtn.setText(buttonName);
        newBtn.setHeight(260);
        newBtn.setTag(buttonName);
        newBtn.setOnClickListener((View.OnClickListener) activity);

        layout.addView(newBtn);
    }


}

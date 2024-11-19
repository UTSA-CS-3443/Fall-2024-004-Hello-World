package edu.utsa.cs3443.fall_2024_helloworld.Model;

import static android.view.View.GONE;
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

/*** ViewMethods class to handle the view methods
 *
 * @author Wheeler
 */
public class ViewMethods extends AppCompatActivity  {


    /***
     * Method to disable a button
     * @param buttonID the id of the button
     * @param activity the activity
     */
    public static void disableButton(int buttonID, Activity activity){
        Button button = activity.findViewById(buttonID);
        button.setEnabled(false);
    }

    /***
     * get the text from an edit text
     * @param editID the id of the edit text
     * @param activity the activity
     * @return the text from the edit text
     */
    public static String getTextEdit(int editID,Activity activity){
        EditText mEdit = activity.findViewById(editID);
        return mEdit.getEditableText().toString();
    }

    /***
     * Set field to string
     * @param filedId the id of the field
     * @param d the string to set
     * @param activity the activity
     */
    public static void setField(int filedId, String d,Activity activity){
        EditText field = activity.findViewById(filedId);
        field.setText(d);

    }

    /***
     * Remove a field
     * @param fieldId the id of the field
     * @param activity the activity
     */
    public static void removeField(int fieldId,Activity activity){

        EditText editText = activity.findViewById(fieldId);
        ((ViewGroup) editText.getParent()).removeView(editText);
    }

    /***
     * Show a field
     * @param fieldId the id of the field
     * @param activity the activity
     */
    public static void showField(int fieldId,Activity activity){

        EditText editText = activity.findViewById(fieldId);
        editText.setVisibility(VISIBLE);
    }

    /***
     * Hide a field
     * @param fieldId the id of the field
     * @param activity the activity
     */
    public static void hideField(int fieldId,Activity activity){

        EditText editText = activity.findViewById(fieldId);
        editText.setVisibility(GONE);
    }

    /***
     * Set up a button
     * @param buttonID the id of the button
     * @param activity the activity
     */
    public static void setUpButton(int buttonID,Activity activity){
        Button button = activity.findViewById(buttonID);
        button.setOnClickListener((View.OnClickListener) activity);
    }

    /***
     * Add a button dynamically to the view
     * @param buttonName the name of the button
     * @param activity the activity
     */
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

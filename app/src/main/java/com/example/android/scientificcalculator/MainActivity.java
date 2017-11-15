package com.example.android.scientificcalculator;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    //Replace edit text with textview
    private EditText expression;
    private double ans;
    private String expressionStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expression = (EditText) findViewById(R.id.Expression);
        disableSoftInputFromAppearing(expression);
    }
    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }
    public void buttonClicked(View view){
        int  x = view.getId();
        Button button = (Button) findViewById(x);
        String toAddText = ButtonFunctions.getText(button);
        int currentCursor = 0;

        //CE button pressed. Change expression with older.
        if(toAddText.equals("clear")){

            if(expressionStr != null && expressionStr.length() > 0){
                currentCursor = expression.getSelectionStart();
                String rightSide = new String("");
                String leftSide = new String("");
                if(currentCursor > 0)
                    rightSide = expressionStr.substring(0,currentCursor-1);
                leftSide = expressionStr.substring(currentCursor,expressionStr.length());
                expressionStr = rightSide + leftSide;
            }
            else
                expressionStr = "";

            expression.setText(expressionStr);
            if(currentCursor < expressionStr.length())
                expression.setSelection(currentCursor);
            else{
                expression.setSelection(expressionStr.length());
            }
        }
        else{
            expressionStr = expression.getText().toString();
            //Get index of cursor.
            currentCursor = expression.getSelectionStart();
            //Place strings in cursor's place.
            expressionStr = expressionStr.substring(0,currentCursor) + toAddText + expressionStr.substring(currentCursor,expressionStr.length());

            //Make the eqn colorful
            Spannable mSpan = expression.getText();

            Spannable toAdd = new SpannableStringBuilder("1",0,1);
            toAdd.setSpan(new ForegroundColorSpan(Color.BLUE),0,toAdd.length(), 0);
            expression.setText(TextUtils.concat(mSpan,toAdd));

            //expression.setText(expressionStr);

            //At last set cursor place to where it belong.
            if(currentCursor < expressionStr.length() - toAddText.length()){//At the cursor's position
                expression.setSelection(currentCursor);
            }
            else{//At the end.
                expression.setSelection(expressionStr.length());
            }
        }
    }
    public void Solve(View view){
        String equation = expression.getText().toString();
        SolveEquation solver = new SolveEquation(equation,ans);
        double val = solver.solvePostFix();
        ans = val;
        expression.setText(Double.toString(val));
        //At last set cursor place to end of text.
        expression.setSelection(expression.length());
    }
}

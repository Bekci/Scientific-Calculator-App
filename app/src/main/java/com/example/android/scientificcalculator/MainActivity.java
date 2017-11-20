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
        ForegroundColorSpan color = ButtonFunctions.getColor(button);
        int currentCursor = 0;

        //CE button pressed. Change expression with older.
        if(toAddText.equals("clear")){
            if(expression.getText() != null) {
                expressionStr = expression.getText().toString();
                if (expressionStr != null && expressionStr.length() > 0) {
                    currentCursor = expression.getSelectionStart();
                    if (currentCursor > 0) {
                        Spannable leftSpan = (Spannable) expression.getText().subSequence(0, currentCursor - 1);
                        Spannable rightSpan = (Spannable) expression.getText().subSequence(currentCursor, expression.length());
                        expression.setText(TextUtils.concat(leftSpan, rightSpan));
                    }
                }
            }
            else
                expression.setText("");

            if(currentCursor < expression.length())
                expression.setSelection(currentCursor-1);
            else{
                expression.setSelection(expression.length());
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

            Spannable left_of_mSpan =(Spannable) mSpan.subSequence(0,currentCursor);
            Spannable right_of_mSpan =(Spannable) mSpan.subSequence(currentCursor,mSpan.length());
            Spannable toAdd = new SpannableStringBuilder(toAddText,0,toAddText.length());
            toAdd.setSpan(color,0,toAdd.length(), 0);
            expression.setText( TextUtils.concat(TextUtils.concat(left_of_mSpan,toAdd),right_of_mSpan));

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
        if(solver.parsingRetVal == -1){//Unknown character in equation.
            Toast.makeText(getApplicationContext(),"Invalid Character", Toast.LENGTH_LONG).show();
        }
        else{
            double val = solver.solvePostFix();
            if(solver.parsingRetVal != -1){
                ans = val;
                expression.setText(Double.toString(val));
                //At last set cursor place to end of text.
                expression.setSelection(expression.length());
            }
            else
                Toast.makeText(getApplicationContext(),"Invalid or Missing Operand", Toast.LENGTH_LONG).show();
        }
    }
}

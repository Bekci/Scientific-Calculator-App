package com.example.android.scientificcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Replace edit text with textview
    private EditText expression;
    private double abs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = (EditText) findViewById(R.id.Expression);
    }
    public void buttonClicked(View view){
        int  x = view.getId();
        Button button = (Button) findViewById(x);
        String toAddText = ButtonFunctions.getText(button);


        String expressionStr = expression.getText().toString();
        //Get index of cursor.
        int currentCursor = expression.getSelectionStart();
        //Place strings in cursor's place.
        expressionStr = expressionStr.substring(0,currentCursor) + toAddText + expressionStr.substring(currentCursor,expressionStr.length());

        expression.setText(expressionStr);

        //At last set cursor place to end of text.
        expression.setSelection(expression.length());
    }
    public void Solve(View view){
        String equation = expression.getText().toString();
        SolveEquation solver = new SolveEquation(equation);
        Toast.makeText(getBaseContext(),equation,Toast.LENGTH_SHORT ).show();
    }
}

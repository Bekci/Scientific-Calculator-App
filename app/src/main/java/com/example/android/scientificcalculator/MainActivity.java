package com.example.android.scientificcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private Stack<String> eqStack = new Stack<>();
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

        //CE button pressed. Change expression with older.
        if(toAddText.equals("clear")){
            if (!eqStack.empty())
                expressionStr = eqStack.pop();
            if (!eqStack.empty())
                expressionStr = eqStack.pop();
            else{
                expressionStr = "";
            }
        }
        else{
            expressionStr = expression.getText().toString();
            //Get index of cursor.
            int currentCursor = expression.getSelectionStart();
            //Place strings in cursor's place.
            expressionStr = expressionStr.substring(0,currentCursor) + toAddText + expressionStr.substring(currentCursor,expressionStr.length());
            //Push new equation to stack.
            eqStack.push(expressionStr);
        }

        expression.setText(expressionStr);
        //At last set cursor place to end of text.
        expression.setSelection(expression.length());
    }
    public void Solve(View view){
        String equation = expression.getText().toString();
        SolveEquation solver = new SolveEquation(equation,ans);
        //Toast.makeText(getBaseContext(),equation,Toast.LENGTH_SHORT ).show();
        double val = solver.solvePostFix();
        ans = val;
        expression.setText(Double.toString(val));
        //At last set cursor place to end of text.
        expression.setSelection(expression.length());
    }
}

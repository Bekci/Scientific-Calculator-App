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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expression = (EditText) findViewById(R.id.Expression);
    }
    public void buttonClicked(View view){
        int  x = view.getId();
        Button button = (Button) findViewById(x);
        String butText = ButtonFunctions.getText(button);
        Toast.makeText(getBaseContext(),butText,Toast.LENGTH_SHORT ).show();
        String expressionStr = expression.getText().toString();
        expressionStr += butText;
        expression.setText(expressionStr);
        //Place strings in cursor's place.
        //At first set cursor place to end of text.

    }
}

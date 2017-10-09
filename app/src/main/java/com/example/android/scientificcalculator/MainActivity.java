package com.example.android.scientificcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void buttonClicked(View view){
        int  x = view.getId();
        Button button = (Button) findViewById(x);
        ButtonFunctions.getText(button);
    }
}

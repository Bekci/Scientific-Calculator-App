package com.example.android.scientificcalculator;

import android.view.View;
import android.widget.Button;

import static com.example.android.scientificcalculator.R.layout.activity_main;

/**
 * Created by Burak on 9.10.2017.
 */

public class ButtonFunctions {
    private static int activity_main;

    public static String getText(Button button){
        return button.getText().toString();
    }
}

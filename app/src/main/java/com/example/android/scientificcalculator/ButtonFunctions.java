package com.example.android.scientificcalculator;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.tag;
import static com.example.android.scientificcalculator.R.layout.activity_main;
import static java.security.AccessController.getContext;

/**
 * Created by Burak on 9.10.2017.
 */

public class ButtonFunctions {

    public static String getText(Button button){
        String toReturn = "";
        String tag = button.getTag().toString();
        if(tag.endsWith("opr") || tag.endsWith("num") || tag.endsWith("point")){
            toReturn = button.getText().toString();
        }
        else if(tag.endsWith("root")){
            toReturn = "sqrt(";
        }
        else if(tag.endsWith("oprp")){
            toReturn = button.getText().toString() + "(";
        }
        else if(tag.endsWith("pow")){
            toReturn = "^(";
        }
        else if(tag.endsWith("fac")){
            toReturn = "!";
        }
        else if(tag.endsWith("exp")){
            toReturn = "e^(";
        }
        else if(tag.endsWith("inv")){
            toReturn = "arc" + tag.toString().substring(0,3) + "(";
        }
        else if(tag.endsWith("ans"))
            toReturn = "ans";
        else{
            toReturn = "Not assigned yet";
        }

        return toReturn;
    }
}

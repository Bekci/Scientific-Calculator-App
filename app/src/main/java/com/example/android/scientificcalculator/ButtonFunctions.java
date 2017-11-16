package com.example.android.scientificcalculator;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
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
        else if(tag.endsWith("abs")){
            toReturn = "abs(";
        }
        else if(tag.endsWith("clear")){
            toReturn = "clear";
        }
        else if(tag.endsWith("oprp")){
            toReturn = button.getText().toString() + "(";
        }
        else if(tag.endsWith("pow")){
            toReturn = "^(";
        }
        else if(tag.endsWith("fac")){
            toReturn = "fac(";
        }
        else if(tag.endsWith("exp")){
            toReturn = "e^(";
        }
        else if(tag.endsWith("enum")){
            toReturn = "e";
        }
        else if(tag.endsWith("inv")){
            toReturn = "arc" + tag.toString().substring(0,3) + "(";
        }
        else if(tag.endsWith("ans"))
            toReturn = "ans";
        else if(tag.endsWith("demit"))
            toReturn = "1/";
        else if(tag.endsWith("pi"))
            toReturn = "π";
        else{
            toReturn = "Not assigned yet";
        }

        return toReturn;
    }
    public static ForegroundColorSpan getColor(Button button){
        ForegroundColorSpan toReturn = new ForegroundColorSpan(Color.BLACK);
        String tag = button.getTag().toString();

        if(tag.endsWith("root") || tag.endsWith("abs") || tag.endsWith("pow") || tag.endsWith("fac") ){
            toReturn = new ForegroundColorSpan(Color.BLUE);
        }
        else if(tag.endsWith("oprp")){
            toReturn = new ForegroundColorSpan(Color.parseColor("#6A1B9A"));
        }
        else if(tag.endsWith("exp")){
            toReturn = new ForegroundColorSpan(Color.parseColor("#FF5722"));
        }
        else if(tag.endsWith("inv")){
            toReturn = new ForegroundColorSpan(Color.parseColor("#3F51B5"));
        }
        else if(tag.endsWith("ans"))
            toReturn = new ForegroundColorSpan(Color.parseColor("#FFC107"));
        else if(tag.endsWith("pi") || tag.endsWith("enum"))
            toReturn = new ForegroundColorSpan(Color.parseColor("#FF5722"));
        else if(tag.endsWith("opr")){
            toReturn = new ForegroundColorSpan(Color.parseColor("#69F0AE"));
        }
        else{
            toReturn = new ForegroundColorSpan(Color.BLACK);
        }


        return toReturn;
    }
}

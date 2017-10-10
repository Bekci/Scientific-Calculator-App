package com.example.android.scientificcalculator;

import java.util.Stack;
import java.util.Vector;

import static java.lang.Integer.parseInt;

/**
 * Created by Burak on 10.10.2017.
 */

public class SolveEquation {
    private Stack<String> stack = new Stack();
    private Vector<Double> numVec = new Vector<>();
    double finalValue;
    private int index;

    SolveEquation(String eq){
        parseEquation(eq);
    }
    private int parseEquation(String eq){
        //Go through the equation and parse num, operand, parentheses.
        index = 0;
        for(index = 0 ; index < eq.length() ;index++){
            char current = eq.charAt(index);
            //Current char is number. Parse number.
            if( current > 47 && current < 58 ){
                parseNum(eq);
            }
            if(index == eq.length())
                break;
        }
        return 0;
    }
    private double parseNum(String eq){
        double toReturn = 0;
        double coefficient = 10;
        boolean haveDot = false;
        while( eq.charAt(index) == '.' || (eq.charAt(index) > 47 && eq.charAt(index) < 58)  ){

            if (eq.charAt(index) == '.'){
                haveDot = true;
                coefficient = 0.1;
                index++;
            }
            if(haveDot == false)
                toReturn = toReturn * coefficient + (eq.charAt(index) - '0');
            else{
                toReturn += (eq.charAt(index) - '0') * coefficient;
                coefficient /= 10.0;
            }
            index++;
            if(index == eq.length())
                break;
        }

        return toReturn;
    }
}

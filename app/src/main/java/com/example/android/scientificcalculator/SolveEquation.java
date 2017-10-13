package com.example.android.scientificcalculator;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import static java.lang.Integer.parseInt;

/**
 * Created by Burak on 10.10.2017.
 */

public class SolveEquation {
    private Stack<String> stack = new Stack();
    private Vector<Double> numVec = new Vector<>();
    private Vector<String> funcVec = new Vector<>();
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
                numVec.add(parseNum(eq));
            }
            else if(current == '(')
                stack.push(Character.toString('('));

            else if(current == 't' || current == 'c' || current == 's' || current == 'l' || current == 'e'){
                String strFunc = parseFunc(eq);
                if(strFunc.compareTo("-1") == 0)
                    handleStack(strFunc);
            }
            else if(current == '+' || current == '-' || current == '*' || current == '/' || current == '%'){
                handleStack(current + "");
            }
            else if(current == ')'){
                handleStack(")");
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
            //Do decimal part.
            if(haveDot == false)
                toReturn = toReturn * coefficient + (eq.charAt(index) - '0');
            //Do floating part.
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
    private String parseFunc(String eq){
        String toReturn  = new String();
        toReturn = "-1";
        if(eq.charAt(index) == 't' && eq.charAt(index+1) == 'a' && eq.charAt(index+2) == 'n'){
            index += 3;
            toReturn ="tan";
        }
        else if(eq.charAt(index) == 'c' && eq.charAt(index+1) == 'o' && eq.charAt(index+2) == 't'){
            index +=3;
            toReturn = "cot";
        }
        else if(eq.charAt(index) == 'c' && eq.charAt(index+1) == 'o' && eq.charAt(index+2) == 's'){
            index +=3;
            toReturn = "cos";
        }
        else if(eq.charAt(index) == 's' && eq.charAt(index+1) == 'i' && eq.charAt(index+2) == 'n'){
            index +=3;
            toReturn = "sin";
        }
        else if(eq.charAt(index) == 'l' && eq.charAt(index+1) == 'o' && eq.charAt(index+2) == 'g'
                && eq.charAt(index+3) == '2'){
            index +=4;
            toReturn = "log2";
        }
        else if(eq.charAt(index) == 'l' && eq.charAt(index+1) == 'o' && eq.charAt(index+2) == 'g'){
            index +=3;
            toReturn = "log";
        }
        else if(eq.charAt(index) == 's' && eq.charAt(index+1) == 'q' && eq.charAt(index+2) == 'r'
                && eq.charAt(index+3) == 't'){
            index +=4;
            toReturn = "sqrt";
        }
        else if(eq.charAt(index) == 'e' && eq.charAt(index+1) == '^') {
            index += 2;
            toReturn = "e^";
        }

        return toReturn;
    }

    private void handleStack(String node){
        if(stack.isEmpty()){ // Stack is empty push function.
            stack.push(node);
        }
        //Stack is not empty.
        else{
            // If character is ) pop from stack to vector untill ( character.
            if(node.compareTo(")") ==0){
                while(stack.peek().compareTo("(") != 0){
                    funcVec.add(stack.pop());
                }
                stack.pop();//For the ( character.
            }
            else{
                String top = stack.peek(); // Get the top element to Compare.

            }
        }
    }
}

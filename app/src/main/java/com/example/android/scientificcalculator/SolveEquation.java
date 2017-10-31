package com.example.android.scientificcalculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

import static android.R.attr.value;

/**
 * Created by Burak on 10.10.2017.
 */

public class SolveEquation {
    private double ans;
    private Stack<String> stack = new Stack();
    private Stack<Double> numStack = new Stack();
    private Vector<String> funcVec = new Vector<>();
    double finalValue;
    private int index;

    SolveEquation(String eq,double inAns){
        ans = inAns;
        if(!stack.empty())stack.clear();
        if(!numStack.empty())numStack.clear();
        if(!funcVec.isEmpty()) funcVec.clear();
        parseEquation(eq);
    }
    private int parseEquation(String eq){
        //Go through the equation and parse num, operand, parentheses.
        index = 0;
        for(index = 0 ; index < eq.length() ;index++){
            char current = eq.charAt(index);
            //Current char is number. Parse number.
            if( current > 47 && current < 58 ){
                funcVec.add(parseNumtoStr(eq));
                index--;//Since parsefunc increments already.
            }
            else if(current == '^')
                handleStack(current + "");
            else if(current == 'e' && index +1 < eq.length() &&eq.charAt(index+1) != '^' ){
                funcVec.add(Double.toString(2.718281828459045));
            }
            else if(current == 'π'){
                funcVec.add(Double.toString(3.141592653589793238));
            }
            else if(current == '(')
                stack.push(Character.toString('('));

            else if(current == 'd' || current ==  'a' || current =='f' || current == 't' || current == 'c' || current == 's' || current == 'l' || current == 'e'){
                String strFunc = parseFunc(eq);
                if(strFunc.compareTo("-1") != 0)
                    handleStack(strFunc);
                index--;//To not skip '(' character
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
        for(int i = 0 ; i <= stack.size() && !stack.empty();i++)
            funcVec.add(stack.pop());
        return 0;
    }
    private double parseNumtoDouble(String eq){
        double toReturn = 0;
        double coefficient = 10;
        boolean haveDot = false;
        index = 0;
        while( eq.charAt(index) == ',' || eq.charAt(index) == '.' || (eq.charAt(index) > 47 && eq.charAt(index) < 58)  ){

            if (eq.charAt(index) == '.' || eq.charAt(index) == ','){
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
    private String parseNumtoStr(String eq){
        String toReturn = "";
        while( eq.charAt(index) == '.' || ( eq.charAt(index) > 47 && eq.charAt(index) < 58)  ){
           toReturn +=eq.charAt(index);
            index++;
            if(index == eq.length())
                break;
        }
        return toReturn;
    }
    private String parseFunc(String eq){
        String toReturn  = new String();
        toReturn = "-1";
        if(eq.charAt(index) == 'a' && eq.charAt(index+1) == 'n' && eq.charAt(index+2) == 's'){
            index +=3;
            toReturn = "ans";
        }
        else if(eq.charAt(index) == 'a' && eq.charAt(index+1) == 'b' && eq.charAt(index+2) == 's'){
            index +=3;
            toReturn = "abs";
        }

        else if(eq.charAt(index) == 'a' && eq.charAt(index+1) == 'r' && eq.charAt(index+2) == 'c'){
            if(eq.charAt(index+3) == 't' && eq.charAt(index+4) == 'a' && eq.charAt(index+5) == 'n'){
                index += 6;
                toReturn ="arctan";
            }
            else if(eq.charAt(index+3) == 'c' && eq.charAt(index+4) == 'o' && eq.charAt(index+5) == 't'){
                index +=6;
                toReturn = "arccot";
            }
            else if(eq.charAt(index+3) == 'c' && eq.charAt(index+4) == 'o' && eq.charAt(index+5) == 's'){
                index +=6;
                toReturn = "arccos";
            }
            else if(eq.charAt(index+3) == 's' && eq.charAt(index+4) == 'i' && eq.charAt(index+5) == 'n'){
                index +=6;
                toReturn = "arcsin";
            }
        }
        else if(eq.charAt(index) == 'd' && eq.charAt(index+1) == 'e' && eq.charAt(index+2) == 'm'){
            index += 3;
            toReturn = "dem";
        }
        else if(eq.charAt(index) == 't' && eq.charAt(index+1) == 'a' && eq.charAt(index+2) == 'n'){
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
        else if(eq.charAt(index) == 'f' && eq.charAt(index+1) == 'a' && eq.charAt(index+2) == 'c'){
            index += 3;
            toReturn ="fac";
        }
        else if(eq.charAt(index) == 'e' && eq.charAt(index+1) == '^') {
            index += 2;
            toReturn = "e^";
        }
        else if(eq.charAt(index) == 'l' && eq.charAt(index+1) == 'n'){
            index +=2;
            toReturn = "ln";
        }

        return toReturn;
    }

    private void handleStack(String node){
        if(node.equals("ans")){
            funcVec.add(Double.toString(ans));
        }
        else if(stack.isEmpty()){ // Stack is empty push function.
            stack.push(node);
        }
        //Stack is not empty.
        else{
            // If character is ) pop from stack to vector until '(' character.
            if(node.compareTo(")") == 0){
                while( !stack.empty() && stack.peek().compareTo("(") != 0){
                    funcVec.add(stack.pop());
                }
                if(!stack.empty())
                    stack.pop();//For the ( character.
            }
            else{
                String top = stack.peek(); // Get the top element to Compare.
                if( priorityMap.getValue(top) >= priorityMap.getValue(node)){ // Top element has equal or bigger priority.
                    funcVec.add(stack.pop());
                    stack.push(node);
                }
                else //New node has bigger priority.
                    stack.push(node);
            }
        }
    }
    private double fac(double x){
        double toRet = 1.0;
        while(x > 1){
            toRet *=x;
            x--;
        }
        return toRet;
    }
    private double abs(double x){
        return x < 0 ? -x : x;
    }
    private double roundNum(double x){
        return (double)Math.round( x * 10000000000d) / 10000000000d;
    }
    public double solvePostFix(){
        finalValue = 0;
        double first = 0,second = 0;
        for( ; !funcVec.isEmpty() ; funcVec.removeElementAt(0)){
            if(funcVec.get(0).charAt(0) > 47 && funcVec.get(0).charAt(0) < 58){//Element is a number
                numStack.push(parseNumtoDouble(funcVec.get(0)));//Push number to stack
            }
            else{
                switch (funcVec.get(0)){
                    case "+":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push(first+second);
                        break;
                    case "-":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push(first-second);
                        break;
                    case "*":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push(roundNum(first*second));
                        break;
                    case "/":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push( roundNum(first/second));
                        break;
                    case "%":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push(first%second);
                        break;
                    case "sin":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.sin( Math.toRadians(second))));
                        break;
                    case "cos":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push(roundNum(Math.cos( Math.toRadians(second) ) ));
                        break;
                    case "tan":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push(  roundNum(Math.tan(Math.toRadians(second)) ));
                        break;
                    case "cot":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(1.0 / Math.tan(Math.toRadians(second))));
                        break;
                    case "arccos":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.toDegrees(Math.acos(second))));
                        break;
                    case "arcsin":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.toDegrees(Math.asin(second))));
                        break;
                    case "abs":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( abs(second) );
                        break;
                    case "arctan":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.toDegrees(Math.atan(second)) ));
                        break;
                    case "arccot":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.toDegrees(1.0 / Math.atan(second))));
                        break;
                    case "^":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push( roundNum(Math.pow(first,second)));
                        break;
                    case "e^":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.exp(second)));
                        break;
                    case "ln":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.log(second)));
                        break;
                    case "log":
                        if (!numStack.empty()) second = numStack.pop();
                        if (!numStack.empty()) first = numStack.pop();
                        numStack.push( roundNum(Math.log10(second) / Math.log10(first)));
                        break;
                    case "log2":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.log10(second) / Math.log10(2.0)));
                        break;
                    case "sqrt":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( roundNum(Math.sqrt(second)));
                        break;
                    case "dem":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push( 1/ second );
                        break;
                    case "fac":
                        if (!numStack.empty()) second = numStack.pop();
                        numStack.push(fac(second));
                        break;
                }
            }
        }
        if (!numStack.empty())
            finalValue = numStack.pop();
    return finalValue;
    }

}

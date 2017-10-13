package com.example.android.scientificcalculator;

import java.util.Map;

/**
 * Created by Burak on 13.10.2017.
 */

//A custom class to handle priorities and operand numbers.
public class priorityMap{

    private String [] keys = {"+","-","%","/","*","sin","cot","cos","ln","log2","log","e^","^",
            "arccos","arccot","arcsin","arctan","("};
    private Integer [] values = {1,1,2,2,2,2,2,2,2,2,2,3,3,2,2,2,2,0};
    private Integer [] elements = {2,2,2,2,2,1,1,1,1,1,1,1,2,1,1,1,1,0};


    public Integer getValue(String k){
        int i = 0;
        while (keys[i].compareTo(k) != 0)
            i++;
        return values[i];
    }
    public Integer getElement(String k){
        int i = 0;
        while (keys[i].compareTo(k) != 0)
            i++;
        return elements[i];
    }

}

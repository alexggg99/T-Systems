/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsystems.javaschool.tasks;

import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author alexey
 * Данный класс реализует интерфейс Calculator
 * получает на входе строку с уравнением и расcчитывает результат с помощью метода evaluate
 * метод возвращает null, если выражение не может быть вычислено.
 */
public class CalculatorImpl implements Calculator{

    Stack<Double> stack = new Stack<>();
    String postfix;

    public CalculatorImpl() {
        stack = new Stack<>();
        postfix = "";
    }
    
    @Override
    public String evaluate(String statement) {
        //transform to postfix
        InToPost convertor = new InToPost(statement);
        postfix = convertor.doTrans();
        double val;
        double tmpResult = 0;
        double num1, num2;
        //equation is incorrect
        if(postfix.isEmpty()) return null;
        String[] tmp = postfix.split(" ");
        for(int j=0; j<tmp.length; j++){
            if(tmp[j].isEmpty())
                return null;
            // if it is not an operator
            if(!tmp[j].equals("+") && !tmp[j].equals("-") && 
                    !tmp[j].equals("*") && !tmp[j].equals("/")){
                try{
                    val = Double.valueOf(tmp[j]);
                }catch(NumberFormatException ex){
                    //if its not a digit return null
                    return null;
                }
                stack.push(val);   
            }else{
                //if its an operator - make calculation
                num2 = Double.valueOf(stack.pop());
                num1 = Double.valueOf(stack.pop());
                if(tmp[j].equals("+")){
                    tmpResult = num1 + num2;
                }
                if(tmp[j].equals("-")){
                    tmpResult = num1 - num2;
                }
                if(tmp[j].equals("*")){
                    tmpResult = num1 * num2;
                }
                if(tmp[j].equals("/")){
                    //divide by zero is forbidden
                    if(num2 == 0)
                        return null;
                    tmpResult = num1 / num2;
                }
            stack.push(tmpResult);
            }   
        }
        //rounding output
        return String.format("%.4f", stack.pop());
    }
    
}


package com.tsystems.javaschool.tasks;

import java.util.Stack;

/**
 * Данный класс преобразует входящую строку к виду обратной польской натации
 * 
 */
public class InToPost {

    private Stack<Character> theStack;
    private String input;
    private String output = "";
    
//--------------------------------------------------------------

    public InToPost(String in) // constructor
    {
        input = in;
        int stackSize = input.length();
        theStack = new Stack<>();
    }
    
//--------------------------------------------------------------

    public String doTrans() // do translation to postfix
    {
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            if(ch == ' ')
                continue;
            if(ch == '+' || ch == '-'){
                output += ' ';
                getOper(ch, 1);   // priority of the operations is 1
                continue;
            }
            if(ch == '*' || ch == '/'){
                output += ' ';
                getOper(ch, 2);   // priority of the operations is 2
                continue;
            }
            if(ch == '('){
                theStack.push(ch);
                continue;
            }
            if(ch == ')'){
                int res = gotParen(ch);
                if(res == 0) return "";
            } else {
                output += ch;   // write it to output
            }
            
            
        }
        while (!theStack.isEmpty()) // pop remaining opers
        {
            output += ' ';
            output += theStack.pop(); // write to output
        }
        return output;    // return postfix
    }

//--------------------------------------------------------------
    
    public void getOper(char opThis, int prec1) {
    // get operator from input
        while (!theStack.isEmpty()) {
            char opTop = theStack.pop();
            if (opTop ==  '(') {          // if it’s a '(' restore       
                theStack.push(opTop);
                break;
            }else{                        // it’s an operator compare precedence
                int prec2;
                if (opTop == '+' || opTop == '-')     // find precedence
                    prec2 = 1;
                else
                    prec2 = 2;
                if (prec2 < prec1){                   // if precedence of new operand less
                    theStack.push(opTop);
                    break;
                }else{                                // if precedence greater
                    output = output + opTop + ' '; 
                }
            }
        } // end while
        theStack.push(opThis);     // push new operator
        } 
    
//--------------------------------------------------------------

    public int gotParen(char ch) {  // get right ')' from input
        //if stack has few than 2 elements
        //equation is incorrect
        if(theStack.size() < 2)
            return 0;
        while (!theStack.isEmpty()) {
            char chx = theStack.pop();
            if (chx == '(') {
                break;
            }else{
                output += ' ';
                output += chx; // output it
            }
        }
        return 1;
    } 
//---------------------------
}

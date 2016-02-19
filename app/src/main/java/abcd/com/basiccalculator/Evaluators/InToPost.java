package abcd.com.basiccalculator.Evaluators;

import java.util.Stack;

public class InToPost {
    private Stack<Character> theStack;
    private String input;
    private String number = "";
    private String output = "";
    public InToPost(String in) {
        input = in;
        theStack = new Stack<>();
    }
    public String doTrans() {
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch (ch) {
                case '+':
                case '-':
                    output = output + number + " ";
                    number = "";
                    gotOper(ch, 1);
                    break;
                case '*':
                case '/':
                    output = output + number + " ";
                    number = "";
                    gotOper(ch, 2);
                    break;
                case '(':
                    theStack.push(ch);
                    break;
                case ')':
                    output = output + number + " ";
                    number = "";
                    gotParen(ch);
                    break;
                default:
                    if (j == input.length() - 1) {
                        output = output + number + ch + " ";
                    }else{
                        number = number + ch;
                    }
                    break;
            }
        }
        while (!theStack.isEmpty()) {
            output = output + " " + theStack.pop();
        }
        return output;
    }
    public void gotOper(char opThis, int prec1) {
        while (!theStack.isEmpty()) {
            char opTop = theStack.pop();
            if (opTop == '(') {
                theStack.push(opTop);
                break;
            }
            else {
                int prec2;
                if (opTop == '+' || opTop == '-')
                    prec2 = 1;
                else
                    prec2 = 2;
                if (prec2 < prec1) {
                    theStack.push(opTop);
                    break;
                }
                else
                    output = output + opTop + " ";
            }
        }
        theStack.push(opThis);
    }
    public void gotParen(char ch){
        while (!theStack.isEmpty()) {
            char chx = theStack.pop();
            if (chx == '(')
                break;
            else
                output = output + chx + " ";
        }
    }
}
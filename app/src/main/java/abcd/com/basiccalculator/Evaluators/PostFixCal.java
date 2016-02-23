package abcd.com.basiccalculator.Evaluators;

import java.util.Stack;


public class PostFixCal {
    private String input;
    private String total;
    double subtotal;
    private Stack<Double> theStack;
    public PostFixCal(String in) {
        input = in;
        theStack = new Stack<>();
    }

    public String calculate() {
        double ls;
        double rs;
        String[] values = input.split("\\s+");
        for (String value : values){
            if (!value.matches("^[\\/\\+\\-\\*]$")){
                theStack.push(Double.parseDouble(value));
            }
            else {
                subtotal = 0;
                rs = theStack.pop();
                ls = theStack.pop();
                switch (value) {
                    case "+":
                        subtotal = ls + rs;
                        theStack.push(subtotal);
                        break;
                    case "-":
                        subtotal = ls - rs;
                        theStack.push(subtotal);
                        break;
                    case "*":
                        subtotal = ls * rs;
                        theStack.push(subtotal);
                        break;
                    case "/":
                        subtotal = ls / rs;
                        theStack.push(subtotal);
                        break;
                }
            }
        }
        subtotal = theStack.pop();
        if (subtotal == (int)subtotal){
            total = Integer.toString((int)subtotal);
        }
        else{
            total = Double.toString(subtotal);
        }
        return total;
    }
}

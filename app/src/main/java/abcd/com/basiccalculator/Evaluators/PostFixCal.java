package abcd.com.basiccalculator.Evaluators;

import java.util.Stack;

public class PostFixCal {
    private String input;
    private String total;
    float subtotal;
    private Stack<Float> theStack;
    public PostFixCal(String in) {
        input = in;
        theStack = new Stack<>();
    }

    public String calculate() {
        float ls;
        float rs;
        String[] values = input.split("\\s+");
        for (String value : values){
            if (!value.matches("^[\\/\\+\\-\\*]$")){
                theStack.push(Float.parseFloat(value));
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
            total = Float.toString(subtotal);
        }
        return total;
    }
}

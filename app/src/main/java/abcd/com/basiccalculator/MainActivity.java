package abcd.com.basiccalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private Button btnadd, btnsub, btnmult, btndiv,btnequal, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btndot, btndel;
    private TextView tvresult;
    private EditText etexpression;
    private boolean blnclear, blnDecimal = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnadd = (Button) findViewById(R.id.btnAdd);
        btnsub = (Button) findViewById(R.id.btnSub);
        btnmult = (Button) findViewById(R.id.btnMult);
        btndiv = (Button) findViewById(R.id.btnDiv);
        btnequal = (Button) findViewById(R.id.btnEqual);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btndot = (Button) findViewById(R.id.btnDot);
        btndel = (Button) findViewById(R.id.btnDel);

        etexpression = (EditText) findViewById(R.id.etExpression);

        tvresult = (TextView) findViewById(R.id.tvResult);

        btnadd.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        btnmult.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnequal.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btndot.setOnClickListener(this);
        btndel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String expression = etexpression.getText().toString();
        int intLength;
        float subtotal;
        String var1 = "", var2 = "", var3 = "";
        switch(view.getId()){
            case R.id.btnDel:
                if (blnclear){
                    clearAll();
                }else{
                    try{
                        etexpression.setText(expression.substring(0,expression.length() - 1));
                        tvresult.setText("");
                    }catch(Exception e){
                        System.out.println("back space too much");
                    }

                }
                break;
            case R.id.btnAdd:
                validInput("+");
                break;
            case R.id.btnSub:
                validInput("-");
                break;
            case R.id.btnMult:
                validInput("x");
                break;
            case R.id.btnDiv:
                validInput("/");
                break;
            case R.id.btnDot:
                validInput(".");
                break;
            case R.id.btn1:
                inputNumbers("1");
                break;
            case R.id.btn2:
                inputNumbers("2");
                break;
            case R.id.btn3:
                inputNumbers("3");
                break;
            case R.id.btn4:
                inputNumbers("4");
                break;
            case R.id.btn5:
                inputNumbers("5");
                break;
            case R.id.btn6:
                inputNumbers("6");
                break;
            case R.id.btn7:
                inputNumbers("7");
                break;
            case R.id.btn8:
                inputNumbers("8");
                break;
            case R.id.btn9:
                inputNumbers("9");
                break;
            case R.id.btn0:
                inputNumbers("0");
                break;
            case R.id.btnEqual:
                expression = expression.replace('x', '*');
                if (expression.matches("^([-+]?[0-9]*\\.?[0-9]+[\\/\\+\\-\\*])+([-+]?[0-9]*\\.?[0-9]+)+$")) {
                    intLength = expression.length();
                    int numOperators = 0, periodCount = 0;
                    subtotal = 0;
                    String operatorSign1 = "", operatorSign2 = "";
                    Boolean error = false;

                    for (int intCount = 0; intCount < intLength; intCount++) {
                        if (expression.substring(intCount, intCount + 1).matches("^[0-9]+$") && numOperators == 0) {
                            var1 = var1 + expression.substring(intCount, intCount + 1);
                            if (expression.substring(intCount + 1, intCount + 2).matches("^[\\/\\+\\-\\*]+$")) {
                                numOperators++;
                            }
                        } else if (expression.substring(intCount, intCount + 1).matches("^[0-9]+$") && numOperators == 2) {
                            var2 = var2 + expression.substring(intCount, intCount + 1);
                            if (intCount + 1 == intLength) {
                                numOperators = 1;
                                break;
                            } else if (expression.substring(intCount + 1, intCount + 2).matches("^[\\/\\+\\-\\*]+$")) {
                                numOperators++;
                            }
                        } else if (expression.substring(intCount, intCount + 1).matches("^[0-9]+$") && numOperators == 4) {
                            var3 = var3 + expression.substring(intCount, intCount + 1);
                            if (intCount + 1 == intLength) {
                                numOperators = 2;
                                break;
                            } else if (expression.substring(intCount + 1, intCount + 2).matches("^[\\/\\+\\-\\*]+$")) {
                                numOperators++;
                            }
                        } else if (expression.substring(intCount, intCount + 1).matches("^[\\/\\+\\-\\*]+$")){
                            if (numOperators == 1) {
                                operatorSign1 = expression.substring(intCount, intCount + 1);
                            } else if (numOperators == 3) {
                                operatorSign2 = expression.substring(intCount, intCount + 1);
                            }
                            numOperators++;
                            periodCount = 0;
                        } else {
                            if (numOperators == 0){
                                var1 = var1 + expression.substring(intCount, intCount + 1);
                                periodCount++;
                            } else if (numOperators == 2){
                                var2 = var2 + expression.substring(intCount, intCount + 1);
                                periodCount++;
                            } else if (numOperators == 4){
                                var3 = var3 + expression.substring(intCount, intCount + 1);
                                periodCount++;
                            }
                        }

                        if (periodCount > 1){
                            error = true;
                            break;
                        }

                        if (numOperators == 5 && !operatorSign2.equals("*") && !operatorSign2.equals("/")) {
                            if (operatorSign1.equals("+")) {
                                subtotal = (Float.parseFloat(var1) + Float.parseFloat(var2));
                                var1 = Float.toString(subtotal);
                                var2 = var3;
                                var3 = "";
                                numOperators = 3;
                                operatorSign1 = operatorSign2;
                            } else if (operatorSign1.equals("-")) {
                                subtotal = (Float.parseFloat(var1) - Float.parseFloat(var2));
                                var1 = Float.toString(subtotal);
                                var2 = var3;
                                var3 = "";
                                numOperators = 3;
                                operatorSign1 = operatorSign2;
                            }
                        }else if (numOperators == 5 && operatorSign2.equals("*")){
                            subtotal = (Float.parseFloat(var2) * Float.parseFloat(var3));
                            var2 = Float.toString(subtotal);
                            var3 = "";
                            numOperators = 3;
                            operatorSign2 = "";
                        }else if (numOperators == 5 && operatorSign2.equals("/")){
                            subtotal = (Float.parseFloat(var2) / Float.parseFloat(var3));
                            var2 = Float.toString(subtotal);
                            var3 = "";
                            numOperators = 3;
                            operatorSign2 = "";
                        }else if (numOperators == 3 && operatorSign1.equals("*")){
                            subtotal = (Float.parseFloat(var1) * Float.parseFloat(var2));
                            var1 = Float.toString(subtotal);
                            var2 = var3;
                            var3 = "";
                            numOperators = 1;
                            operatorSign1 = "";
                        }else if (numOperators == 3 && operatorSign1.equals("/")){
                            subtotal = (Float.parseFloat(var1) / Float.parseFloat(var2));
                            var1 = Float.toString(subtotal);
                            var2 = var3;
                            var3 = "";
                            numOperators = 1;
                            operatorSign1 = "";
                        }
                    }

                    if (numOperators == 1){
                        if (operatorSign1.equals("+")){
                            subtotal = (Float.parseFloat(var1) + Float.parseFloat(var2));
                        } else if (operatorSign1.equals("-")){
                            subtotal = (Float.parseFloat(var1) - Float.parseFloat(var2));
                        } else if (operatorSign1.equals("*")){
                            subtotal = (Float.parseFloat(var1) * Float.parseFloat(var2));
                        } else if (operatorSign1.equals("/")){
                            subtotal = (Float.parseFloat(var1) / Float.parseFloat(var2));
                        }
                    } else if (numOperators == 2){
                        if (operatorSign1.equals("+") && operatorSign2.equals("+")){                          //ADDITION
                            subtotal = Float.parseFloat(var1) + Float.parseFloat(var2) + Float.parseFloat(var3);
                        } else if (operatorSign1.equals("+") && operatorSign2.equals("-")){
                            subtotal = Float.parseFloat(var1) + Float.parseFloat(var2) - Float.parseFloat(var3);
                        } else if (operatorSign1.equals("+") && operatorSign2.equals("*")){
                            subtotal = Float.parseFloat(var1) + (Float.parseFloat(var2) * Float.parseFloat(var3));
                        } else if (operatorSign1.equals("+") && operatorSign2.equals("/")){
                            subtotal = Float.parseFloat(var1) + (Float.parseFloat(var2) / Float.parseFloat(var3));
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("+")){                    //SUBTRACTION
                            subtotal = Float.parseFloat(var1) - Float.parseFloat(var2) + Float.parseFloat(var3);
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("-")){
                            subtotal = Float.parseFloat(var1) - Float.parseFloat(var2) - Float.parseFloat(var3);
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("*")){
                            subtotal = Float.parseFloat(var1) - (Float.parseFloat(var2) * Float.parseFloat(var3));
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("/")){
                            subtotal = Float.parseFloat(var1) - (Float.parseFloat(var2) / Float.parseFloat(var3));
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("+")){                    //MULTIPLICATION
                            subtotal = Float.parseFloat(var1) * Float.parseFloat(var2) + Float.parseFloat(var3);
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("-")){
                            subtotal = Float.parseFloat(var1) * Float.parseFloat(var2) - Float.parseFloat(var3);
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("*")){
                            subtotal = Float.parseFloat(var1) * Float.parseFloat(var2) * Float.parseFloat(var3);
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("/")){
                            subtotal = Float.parseFloat(var1) * Float.parseFloat(var2) / Float.parseFloat(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("+")){                    //DIVISION
                            subtotal = Float.parseFloat(var1) / Float.parseFloat(var2) + Float.parseFloat(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("-")){
                            subtotal = Float.parseFloat(var1) / Float.parseFloat(var2) - Float.parseFloat(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("*")){
                            subtotal = Float.parseFloat(var1) / Float.parseFloat(var2) * Float.parseFloat(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("/")){
                            subtotal = Float.parseFloat(var1) / Float.parseFloat(var2) / Float.parseFloat(var3);
                        }
                    }

                    if (error){
                        tvresult.setText("ERROR");
                    }else{
                        if (subtotal == (int)subtotal) {
                            tvresult.setText(Integer.toString((int)subtotal));
                        } else {
                            tvresult.setText(Float.toString(subtotal));
                        }
                    }
                } else {
                    tvresult.setText("ERROR");
                }
                btndel.setText("Clear");
                blnclear = true;
                blnDecimal = true;
                break;
         }
    }

    private void validInput(String value){
        clearAll();
        String expression = etexpression.getText().toString();
        expression = expression.replace('x', '*');
        try {
            if (value.equals(".")){
                if (expression.substring(expression.length() - 1, expression.length() - 0).matches("^[\\/\\+\\-\\*]+$")){
                    expression = expression + "0.";
                    expression = expression.replace('*', 'x');
                    etexpression.setText(expression);
                    blnDecimal = false;
                }else if (blnDecimal){
                    expression = expression + value;
                    expression = expression.replace('*', 'x');
                    etexpression.setText(expression);
                    blnDecimal = false;
                }
            }else if (!expression.substring(expression.length() - 1, expression.length() - 0).matches("^[\\/\\+\\-\\*\\.]+$")) {
                expression = expression + value;
                expression = expression.replace('*', 'x');
                etexpression.setText(expression);
                blnDecimal = true;
            }
        }catch(Exception e) {
        }
    }

    private void inputNumbers (String value){
        clearAll();
        String expression = etexpression.getText().toString();
        expression = expression + value;
        etexpression.setText(expression);

    }

    private void clearAll(){
        if (blnclear){
            etexpression.setText("");
            tvresult.setText("");
            btndel.setText("Del");
            blnclear = false;
        }
    }
}


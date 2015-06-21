package abcd.com.basiccalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private Button btnadd, btnsub, btnmult, btndiv,btnequal;
    private TextView tvresult;
    private EditText etexpression;

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

        etexpression = (EditText) findViewById(R.id.etExpression);

        tvresult = (TextView) findViewById(R.id.tvResult);

        btnadd.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        btnmult.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnequal.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        String expression = etexpression.getText().toString();
        int intLength;
        float subtotal;
        String var1 = "", var2 = "", var3 = "";
        switch(view.getId()){
            case R.id.btnAdd:
                break;
            case R.id.btnSub:
                break;
            case R.id.btnMult:
                break;
            case R.id.btnDiv:
                try {
                    //int divide = Integer.parseInt(expression) / Integer.parseInt(num2);
                    //tvresult.setText(String.valueOf(divide));
                }catch(Exception e){
                    tvresult.setText("Cannot Divide");
                }
            case R.id.btnEqual:
                if (expression.matches("^([-+]?[0-9]*\\.?[0-9]+[\\/\\+\\-\\*])+([-+]?[0-9]*\\.?[0-9]+)+$")) {
                    intLength = expression.length();
                    int numOperators = 0, periodCount = 0;
                    subtotal = 0;
                    String operatorSign1 = "", operatorSign2 = "";

                    System.out.println("before loop");
                    for (int intCount = 0; intCount < intLength; intCount++) {
                        if (expression.substring(intCount, intCount + 1).matches("[.]")){
                            if (numOperators == 0){
                                var1 = var1 + expression.substring(intCount, intCount + 1);
                                periodCount++;
                                System.out.println("WORKS");
                                break;
                            } else if (numOperators == 3){
                                var2 = var2 + expression.substring(intCount, intCount + 1);
                                periodCount++;
                            } else if (numOperators == 5){
                                var3 = var3 + expression.substring(intCount, intCount + 1);
                                periodCount++;
                            }
                        }else if (expression.substring(intCount, intCount + 1).matches("^[0-9]+$") && numOperators == 0) {
                            var1 = var1 + expression.substring(intCount, intCount + 1);
                            if (!expression.substring(intCount + 1, intCount + 2).matches("^[0-9]+$")) {
                                numOperators++;
                            }
                        } else if (expression.substring(intCount, intCount + 1).matches("^[0-9]+$") && numOperators == 2) {
                            var2 = var2 + expression.substring(intCount, intCount + 1);
                            if (intCount + 1 == intLength) {
                                numOperators = 1;
                                break;
                            } else if (!expression.substring(intCount + 1, intCount + 2).matches("^[0-9]+$")) {
                                numOperators++;
                            }
                        } else if (expression.substring(intCount, intCount + 1).matches("^[0-9]+$") && numOperators == 4) {
                            var3 = expression.substring(intCount, intCount + 1);

                            if (intCount + 1 == intLength) {
                                numOperators = 2;
                                break;
                            } else if (!expression.substring(intCount + 1, intCount + 2).matches("^[0-9]+$")) {
                                numOperators++;
                            }
                        } else {
                            if (numOperators == 1) {
                                operatorSign1 = expression.substring(intCount, intCount + 1);
                            } else if (numOperators == 3) {
                                operatorSign2 = expression.substring(intCount, intCount + 1);
                            }
                            numOperators++;
                        }

                        System.out.println("var1: " + var1);
                        System.out.println("var2: " + var2);
                        System.out.println("var3: " + var3);
                        System.out.println("operatorSign1: " + operatorSign1);
                        System.out.println("operatorSign2: " + operatorSign2);
                        System.out.println("numOperators: " + Integer.toString(numOperators));
                        System.out.println("periodCount: " + Integer.toString(periodCount));

                        if (numOperators == 5 && !operatorSign2.equals("*") && !operatorSign2.equals("/")) {
                            if (operatorSign1.equals("+")) {
                                subtotal = (Integer.parseInt(var1) + Integer.parseInt(var2));
                                var1 = Float.toString(subtotal);
                                var2 = var3;
                                numOperators = 3;
                                operatorSign1 = operatorSign2;
                            } else if (operatorSign1.equals("-")) {
                                subtotal = (Integer.parseInt(var1) - Integer.parseInt(var2));
                                var1 = Float.toString(subtotal);
                                var2 = var3;
                                numOperators = 3;
                                operatorSign1 = operatorSign2;
                            }
                        }else if (numOperators == 5 && operatorSign2.equals("*")){
                            subtotal = (Integer.parseInt(var2) * Integer.parseInt(var3));
                            var2 = Float.toString(subtotal);
                            var3 = "";
                            numOperators = 3;
                            operatorSign2 = "";
                        }else if (numOperators == 5 && operatorSign2.equals("/")){
                            subtotal = (Integer.parseInt(var2) / Integer.parseInt(var3));
                            var2 = Float.toString(subtotal);
                            var3 = "";
                            numOperators = 3;
                            operatorSign2 = "";
                        }else if (numOperators == 3 && operatorSign1.equals("*")){
                            subtotal = (Integer.parseInt(var1) * Integer.parseInt(var2));
                            var1 = Float.toString(subtotal);
                            var2 = var3;
                            numOperators = 1;
                            operatorSign1 = "";
                        }else if (numOperators == 3 && operatorSign1.equals("/")){
                            subtotal = (Integer.parseInt(var1) / Integer.parseInt(var2));
                            var1 = Float.toString(subtotal);
                            var2 = var3;
                            numOperators = 1;
                            operatorSign1 = "";
                        }
                    }

                    if (numOperators == 1){
                        if (operatorSign1.equals("+")){
                            subtotal = (Integer.parseInt(var1) + Integer.parseInt(var2));
                        } else if (operatorSign1.equals("-")){
                            subtotal = (Integer.parseInt(var1) - Integer.parseInt(var2));
                        } else if (operatorSign1.equals("*")){
                            subtotal = (Integer.parseInt(var1) * Integer.parseInt(var2));
                        } else if (operatorSign1.equals("/")){
                            subtotal = (Integer.parseInt(var1) / Integer.parseInt(var2));
                        }
                    } else if (numOperators == 2){
                        if (operatorSign1.equals("+") && operatorSign2.equals("+")){                          //ADDITION
                            subtotal = Integer.parseInt(var1) + Integer.parseInt(var2) + Integer.parseInt(var3);
                        } else if (operatorSign1.equals("+") && operatorSign2.equals("-")){
                            subtotal = Integer.parseInt(var1) + Integer.parseInt(var2) - Integer.parseInt(var3);
                        } else if (operatorSign1.equals("+") && operatorSign2.equals("*")){
                            subtotal = Integer.parseInt(var1) + (Integer.parseInt(var2) * Integer.parseInt(var3));
                        } else if (operatorSign1.equals("+") && operatorSign2.equals("/")){
                            subtotal = Integer.parseInt(var1) + (Integer.parseInt(var2) / Integer.parseInt(var3));
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("+")){                    //SUBTRACTION
                            subtotal = Integer.parseInt(var1) - Integer.parseInt(var2) + Integer.parseInt(var3);
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("-")){
                            subtotal = Integer.parseInt(var1) - Integer.parseInt(var2) - Integer.parseInt(var3);
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("*")){
                            subtotal = Integer.parseInt(var1) - (Integer.parseInt(var2) * Integer.parseInt(var3));
                        } else if (operatorSign1.equals("-") && operatorSign2.equals("/")){
                            subtotal = Integer.parseInt(var1) - (Integer.parseInt(var2) / Integer.parseInt(var3));
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("+")){                    //MULTIPLICATION
                            subtotal = Integer.parseInt(var1) * Integer.parseInt(var2) + Integer.parseInt(var3);
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("-")){
                            subtotal = Integer.parseInt(var1) * Integer.parseInt(var2) - Integer.parseInt(var3);
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("*")){
                            subtotal = Integer.parseInt(var1) * Integer.parseInt(var2) * Integer.parseInt(var3);
                        } else if (operatorSign1.equals("*") && operatorSign2.equals("/")){
                            subtotal = Integer.parseInt(var1) * Integer.parseInt(var2) / Integer.parseInt(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("+")){                    //DIVISION
                            subtotal = Integer.parseInt(var1) / Integer.parseInt(var2) + Integer.parseInt(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("-")){
                            subtotal = Integer.parseInt(var1) / Integer.parseInt(var2) - Integer.parseInt(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("*")){
                            subtotal = Integer.parseInt(var1) / Integer.parseInt(var2) * Integer.parseInt(var3);
                        } else if (operatorSign1.equals("/") && operatorSign2.equals("/")){
                            subtotal = Integer.parseInt(var1) / Integer.parseInt(var2) / Integer.parseInt(var3);
                        }
                    }


                    System.out.println("after loop");

                    tvresult.setText(Float.toString(subtotal));

                } else if (expression.matches("^[0-9]+$")) {
                    tvresult.setText(expression);
                } else {
                    tvresult.setText("ERROR");
                }
         }
    }

    public int total (int var1, int var2, int var3){
        return var1+var2+var3;
    }
}


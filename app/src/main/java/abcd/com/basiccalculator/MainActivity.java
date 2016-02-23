package abcd.com.basiccalculator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import abcd.com.basiccalculator.Evaluators.InToPost;
import abcd.com.basiccalculator.Evaluators.PostFixCal;

public class MainActivity extends Activity implements View.OnClickListener{
    Button btnadd, btnsub, btnmult, btndiv,btnequal, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btndot, btndel, btnLBracket, btnRBracket;
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
        btnLBracket = (Button) findViewById(R.id.btnLBracket);
        btnRBracket = (Button) findViewById(R.id.btnRBracket);

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
        btnLBracket.setOnClickListener(this);
        btnRBracket.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String expression = etexpression.getText().toString();

        switch(view.getId()){
            case R.id.btnDel:
                if (blnclear){
                    clearAll();
                }else{
                    try{
                        if (expression.substring(expression.length() - 1).equals(".")){
                            blnDecimal = true;
                        }
                        else if (expression.substring(expression.length() - 1).matches("^[\\/\\+\\-]|[x]$")){
                            blnDecimal = false;
                        }
                        etexpression.setText(expression.substring(0, expression.length() - 1));
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
            case R.id.btnLBracket:
                validInput("(");
                break;
            case R.id.btnRBracket:
                validInput(")");
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
                try {
                    expression = expression.replace('x', '*');
                    InToPost theTrans = new InToPost(expression);
                    String output = theTrans.doTrans();
                    System.out.println(output);
                    PostFixCal cal = new PostFixCal(output);
                    String total = cal.calculate();
                    tvresult.setText(total);
                }
                catch (Exception e){
                    tvresult.setText("Error");
                }

                btndel.setText("Clear");
                blnclear = true;
                blnDecimal = true;
                break;
         }
    }

    private void validInput(String value) {
        String preans = tvresult.getText().toString();
        String expression = etexpression.getText().toString();
        String last_input = "";

        clearAll();

        if (!preans.equals("")){
            System.out.println(value);
            expression = "";
            blnDecimal = true;
            System.out.println("PREANS");
        }
        try {
            last_input = expression.substring(expression.length() - 1, expression.length());
        } catch (Exception e) {
            System.out.println("Empty expression");
        }
        if (value.equals(".")) {
            if (last_input.matches("^[\\/\\+\\-]|[x]$") || last_input.equals("")) {
                expression = expression  + "0.";
                blnDecimal = false;
            } else if (blnDecimal) {
                expression = expression + value;
                blnDecimal = false;
            }
        } else if (value.equals("(")) {
            if (last_input.matches("^[\\/\\+\\-]|[x]$") || last_input.equals("")) {
                expression = expression + value;
            }
        } else if (last_input.matches("^[^\\/\\+\\-\\.\\(]|[x]$") && !last_input.equals("")) {
            expression = expression + value;
            blnDecimal = true;
        } else if (value.matches("^[\\/\\+\\-]|[x]$") && !preans.equals("")){
            if (Double.parseDouble(preans) < 0){
                expression = "0" + preans + value;
            }else{
                expression = preans + value;
            }
            blnDecimal = true;
        }

        etexpression.setText(expression);
    }

    private void inputNumbers (String value){
        clearAll();
        String expression = etexpression.getText().toString();
        try {
            if (!expression.substring(expression.length() - 1, expression.length()).equals(")")) {
                expression = expression + value;
                etexpression.setText(expression);
            }
        }catch (Exception e){
            expression = expression + value;
            etexpression.setText(expression);
        }
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


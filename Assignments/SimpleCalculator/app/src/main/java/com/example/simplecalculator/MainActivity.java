package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView expression;
    private TextView input;
    private Button CE;
    private Button C;
    private Button BS;
    private Button divide;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button multiply;
    private Button four;
    private Button five;
    private Button six;
    private Button subtract;
    private Button one;
    private Button two;
    private Button three;
    private Button plus;
    private Button negative;
    private Button zero;
    private Button point;
    private Button equal;

    private int x, y, z;
    private String c = "";
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        initializeEvent();
    }

    private void initializeView() {
        expression = findViewById(R.id.expression);
        input = findViewById(R.id.input);
        CE = findViewById(R.id.CE);
        C = findViewById(R.id.C);
        BS = findViewById(R.id.BS);
        divide = findViewById(R.id.divide);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        multiply = findViewById(R.id.multiply);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        subtract = findViewById(R.id.subtract);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        plus = findViewById(R.id.plus);
        negative = findViewById(R.id.negative);
        zero = findViewById(R.id.zero);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);

    }

    private void initializeEvent() {
        CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("0");
                c = "";
                if (expression.getText().toString().contains("=")) {
                    expression.setText("");
                    input.setText("0");
                    x = 0;
                    c = "";
                    operator = "";
                }
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText("");
                input.setText("0");
                x = 0;
                c = "";
                operator = "";
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += seven.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + seven.getText().toString());
                input.setText(input.getText() + seven.getText().toString());

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += eight.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + eight.getText().toString());
                input.setText(input.getText() + eight.getText().toString());
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += nine.getText();
                if (operator.equals(""))
                    if (operator.equals(""))
                        expression.setText(expression.getText() + nine.getText().toString());
                input.setText(input.getText() + nine.getText().toString());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += four.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + four.getText().toString());
                input.setText(input.getText() + four.getText().toString());
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += five.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + five.getText().toString());
                input.setText(input.getText() + five.getText().toString());
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += six.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + six.getText().toString());
                input.setText(input.getText() + six.getText().toString());
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += one.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + one.getText().toString());
                input.setText(input.getText() + one.getText().toString());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += two.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + two.getText().toString());
                input.setText(input.getText() + two.getText().toString());
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += three.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + three.getText().toString());
                input.setText(input.getText() + three.getText().toString());
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c += zero.getText();
                if (operator.equals(""))
                    expression.setText(expression.getText() + zero.getText().toString());
                input.setText(input.getText() + zero.getText().toString());
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (containOperator()) {
                    onOperatorClickMoreThanOnce();
                } else {
                    x = Integer.parseInt(c);
                    c = "";
                    operator = divide.getText().toString();
                    expression.setText(expression.getText() + divide.getText().toString());
                    input.setText("0");
                }

            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (containOperator()) {
                    onOperatorClickMoreThanOnce();
                } else {
                    x = Integer.parseInt(c);
                    c = "";
                    operator = multiply.getText().toString();
                    expression.setText(expression.getText() + "x");
                    input.setText("0");
                }

            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (containOperator()) {
                    onOperatorClickMoreThanOnce();
                } else {
                    x = Integer.parseInt(c);
                    c = "";
                    operator = subtract.getText().toString();
                    expression.setText(expression.getText() + subtract.getText().toString());
                    input.setText("0");
                }

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (containOperator()) {
                    onOperatorClickMoreThanOnce();
                } else {
                    x = Integer.parseInt(c);
                    c = "";
                    operator = plus.getText().toString();
                    expression.setText(expression.getText() + plus.getText().toString());
                    input.setText("0");
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEqualClick();
            }
        });
    }

    private void onOperatorClickMoreThanOnce() {

    }

    private boolean containOperator() {
        String exp = expression.getText().toString();
        return exp.contains("+") || exp.contains("-") || exp.contains("x") || exp.contains("/");
    }

    private void onEqualClick() {
        try {
            expression.setText(expression.getText() + c + equal.getText().toString());
            switch (operator) {
                case "+":
                    y = x + Integer.parseInt(c);
                    input.setText(String.valueOf(y));
                    break;
                case "-":
                    y = x - Integer.parseInt(c);
                    input.setText(String.valueOf(y));
                    break;
                case "*":
                    y = x * Integer.parseInt(c);
                    input.setText(String.valueOf(y));
                    break;
                case "/":
                    if (x % Integer.parseInt(c) == 0)
                        input.setText(String.valueOf(x / Integer.parseInt(c)));
                    else
                        input.setText(String.valueOf(x / Float.parseFloat(c)));
                    break;
                default:
            }
            x = y;
            c = String.valueOf(y);
        } catch (Exception e) {
            input.setText("Error: " + e.getMessage());
        }
    }
}
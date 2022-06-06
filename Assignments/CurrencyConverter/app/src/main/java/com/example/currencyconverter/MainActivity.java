package com.example.currencyconverter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Spinner fromCurrency;
    private Spinner toCurrency;
    private TextView fromValue;
    private TextView toValue;
    private TextView rateTextView;
    private Button CE;
    private RelativeLayout backspace;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button four;
    private Button five;
    private Button six;
    private Button one;
    private Button two;
    private Button three;
    private Button zero;
    private Button point;

    private double fromToUSD = 1.39;
    private double toToUSD = 1.39;

    private boolean fromTo = true;
    private String from = "Australia - Dollar";
    private String to = "Australia - Dollar";

    NumberFormat formatter;

    private HashMap<String, Double> rate = new HashMap<>();
    private HashMap<String, String> abbr = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        formatter = new DecimalFormat("#0.00");
    }

    private void initView() {
        fromCurrency = findViewById(R.id.from_currency);
        toCurrency = findViewById(R.id.to_currency);
        fromValue = findViewById(R.id.from_value);
        toValue = findViewById(R.id.to_value);
        rateTextView = findViewById(R.id.rate);
        rateTextView.setText("1 AUD = 1 AUD");
        CE = findViewById(R.id.CE);
        backspace = findViewById(R.id.backspace);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        zero = findViewById(R.id.zero);
        point = findViewById(R.id.point);
    }

    private void initData() {
        rate.put("Australia - Dollar", 1.39);
        rate.put("Canada - Dollar", 1.26);
        rate.put("China - Yuan", 6.66);
        rate.put("Europe - Euro", 0.93);
        rate.put("Japan - Yen", 130.84);
        rate.put("Korea - Won", 1251.53);
        rate.put("Russia - Rouble", 63.36);
        rate.put("Singapore - Dollar", 1.37);
        rate.put("Switzerland - Franc", 0.96);
        rate.put("Thailand - Baht", 34.29);
        rate.put("United Kingdom - Pound", 0.8);
        rate.put("United States - Dollar", 1d);
        rate.put("Vietnam - Dong", 23160.71);

        abbr.put("Australia - Dollar", "AUD");
        abbr.put("Canada - Dollar", "CAD");
        abbr.put("China - Yuan", "CNY");
        abbr.put("Europe - Euro", "EUR");
        abbr.put("Japan - Yen", "JPY");
        abbr.put("Korea - Won", "KRW");
        abbr.put("Russia - Rouble", "RUB");
        abbr.put("Singapore - Dollar", "SGD");
        abbr.put("Switzerland - Franc", "CHF");
        abbr.put("Thailand - Baht", "THB");
        abbr.put("United Kingdom - Pound", "GBP");
        abbr.put("United States - Dollar", "USD");
        abbr.put("Vietnam - Dong", "VND");
    }

    private void initEvent() {
        CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromValue.setText("0");
                toValue.setText("0");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = getFrom().getText();
                if (text.length() == 1 && text.charAt(0) != '0') text = "0";
                if (text.length() > 1) text = text.subSequence(0, text.length() - 1);
                getFrom().setText(text);
                convert(getFrom().getText());
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "7").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "8").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "9").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "4").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "5").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "6").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "1").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "2").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "3").replaceFirst("^0+(?!$)", ""));
                if (getFrom().getText().toString().charAt(0) == '.')
                    getFrom().setText("0" + getFrom().getText());
                convert(getFrom().getText());
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText((getFrom().getText() + "0").replaceFirst("^0+(?!$)", ""));
                convert(getFrom().getText());
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFrom().setText(getFrom().getText() + ".");
                convert(getFrom().getText());
            }
        });

        fromValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fromTo) {
                    fromTo = true;
                    swap();
                    swapAbbr();
                    setView();
                    setRateTextView();
                }
            }
        });

        toValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fromTo) {
                    fromTo = false;
                    swap();
                    swapAbbr();
                    setView();
                    setRateTextView();
                }
            }
        });

        fromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                try {
                    if (fromTo) {
                        fromToUSD = rate.get(parent.getItemAtPosition(pos));
                        from = parent.getItemAtPosition(pos).toString();
                        convert(fromValue.getText());
                    } else {
                        toToUSD = rate.get(parent.getItemAtPosition(pos));
                        to = parent.getItemAtPosition(pos).toString();
                        convert(toValue.getText());
                    }
                    setRateTextView();
                } catch (Exception ignored) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                try {
                    if (fromTo) {
                        toToUSD = rate.get(parent.getItemAtPosition(pos));
                        to = parent.getItemAtPosition(pos).toString();
                        convert(fromValue.getText());
                    } else {
                        fromToUSD = rate.get(parent.getItemAtPosition(pos));
                        from = parent.getItemAtPosition(pos).toString();
                        convert(toValue.getText());
                    }
                    setRateTextView();
                } catch (Exception ignored) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private TextView getFrom() {
        if (fromTo) return fromValue;
        else return toValue;
    }

    private TextView getTo() {
        if (fromTo) return toValue;
        else return fromValue;
    }

    private void setView() {
        getFrom().setTypeface(null, Typeface.BOLD);
        getTo().setTypeface(null, Typeface.NORMAL);
    }

    private void convert(CharSequence from) {
        getTo().setText("" + formatter.format(Double.valueOf(from.toString()) / fromToUSD * toToUSD));
    }

    private void swap() {
        Double temp = fromToUSD;
        fromToUSD = toToUSD;
        toToUSD = temp;
    }

    private void swapAbbr() {
        String temp = from;
        from = to;
        to = temp;
    }

    private void setRateTextView() {
        String text = "1 " + abbr.get(from) + " = " + formatter.format(1 / fromToUSD * toToUSD) + " " + abbr.get(to);
        rateTextView.setText(text);
    }
}
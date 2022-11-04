package com.example.task_01_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //--- Calculator
    private Calculator calculator;

    //--- Button
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnPlus, btnMinus, btnMultiply, btnDivision, btnEquals;
    private Button btnDot;
    private Button btnCancel;

    //--- TextView
    private TextView tvValue, tvBuffer, tvOperator;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.calculator = new Calculator();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--- Binding button
        btn0 = (Button) this.findViewById(R.id.btn0);
        btn1 = (Button) this.findViewById(R.id.btn1);
        btn2 = (Button) this.findViewById(R.id.btn2);
        btn3 = (Button) this.findViewById(R.id.btn3);
        btn4 = (Button) this.findViewById(R.id.btn4);
        btn5 = (Button) this.findViewById(R.id.btn5);
        btn6 = (Button) this.findViewById(R.id.btn6);
        btn7 = (Button) this.findViewById(R.id.btn7);
        btn8 = (Button) this.findViewById(R.id.btn8);
        btn9 = (Button) this.findViewById(R.id.btn9);

        btnDot = (Button) this.findViewById(R.id.btnDot);

        btnPlus = (Button) this.findViewById(R.id.btnPlus);
        btnMinus = (Button) this.findViewById(R.id.btnMinus);
        btnMultiply = (Button) this.findViewById(R.id.btnMultiply);
        btnDivision = (Button) this.findViewById(R.id.btnDivision);

        btnCancel = (Button) this.findViewById(R.id.btnCancel);
        btnEquals = (Button) this.findViewById(R.id.btnEquals);

        //--- Binding text view
        tvValue = (TextView) this.findViewById(R.id.tvValue);
        tvBuffer = (TextView) this.findViewById(R.id.tvBuffer);
        tvOperator = (TextView) this.findViewById(R.id.tvOperator);
        tvInfo = (TextView) this.findViewById(R.id.tvInfo);

        //--- Init text view
        tvValue.setText("0");
        tvBuffer.setText("");
        tvOperator.setText("");
        tvInfo.setText("");

        //--- Create on click listener
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //--- Значение кнопки получаем с помощью тега.
                //Это позволило существенно сократить код.
                tvInfo.setText(v.getTag().toString());
                clickKey(v.getTag().toString());
            }
        };

        //--- Binding button with on click listener
        btn0.setOnClickListener(oclBtn);
        btn1.setOnClickListener(oclBtn);
        btn2.setOnClickListener(oclBtn);
        btn3.setOnClickListener(oclBtn);
        btn4.setOnClickListener(oclBtn);
        btn5.setOnClickListener(oclBtn);
        btn6.setOnClickListener(oclBtn);
        btn7.setOnClickListener(oclBtn);
        btn8.setOnClickListener(oclBtn);
        btn9.setOnClickListener(oclBtn);

        btnDot.setOnClickListener(oclBtn);

        btnPlus.setOnClickListener(oclBtn);
        btnMinus.setOnClickListener(oclBtn);
        btnMultiply.setOnClickListener(oclBtn);
        btnDivision.setOnClickListener(oclBtn);

        btnEquals.setOnClickListener(oclBtn);

        btnCancel.setOnClickListener(oclBtn);
    }

    private void clickKey(String key) {
        //--- Вся логика калькулятора вынесена в отдельный класс.
        calculator.input(key);

        tvBuffer.setText(calculator.getDisplayBuffer());
        tvValue.setText(calculator.getDisplayValue());
        tvOperator.setText(calculator.getOperand());

    }
}
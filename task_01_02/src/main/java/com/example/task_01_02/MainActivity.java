package com.example.task_01_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView tvColor;
    TextView tvLength;
    TextView tvAction;

    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;

    ConstraintLayout CL;

    float xDown, xUp, yDown, yUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAction = (TextView) this.findViewById(R.id.tvActioin);
        tvColor = (TextView) this.findViewById(R.id.tvColor);
        tvLength = (TextView) this.findViewById(R.id.tvLength);

        CL = (ConstraintLayout) this.findViewById(R.id.clContainer);

        CL.setOnTouchListener(this::onTouch);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event){

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Сохранить начальные координаты...
                xDown = event.getX();
                yDown = event.getY();

                sDown = "Down:\nx: " + xDown + "; y: " + yDown;
                sMove = "";
                sUp = "";

                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();

                sMove = "Move:\nx: " + x + "; y: " + y;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //Сохранить конечные кординаты...
                xUp = event.getX();
                yUp = event.getY();

                sMove = "";
                sUp = "Up:\nx: " + xUp + "; y: " + yUp;

                //Запустить вычисление и изменение цвета...
                changeBackground(20f);
                break;
        }

        tvAction.setText(sDown + "\n\n" + sMove + "\n\n" + sUp);
        return true;
    }

    public void changeBackground (Float allowed){

        if (calcLength() > allowed) {
            //Если длина отрезка больше допуска
            if (Math.abs(yDown - yUp) < allowed) {
                //Горизонтальный отрезок
                tvColor.setText("Color: Зелёный");
                CL.setBackgroundColor(Color.argb(255,0,255, 0));
            } else if (Math.abs(xDown - xUp) < allowed) {
                //Вертикальный отрезок
                tvColor.setText("Color: Жёлтый");
                CL.setBackgroundColor(Color.argb(255,255,255, 0));
            } else {
                //Диагональ
                tvColor.setText("Color: Красный");
                CL.setBackgroundColor(Color.argb(255,255,0, 0));
            }
        }
    }

    public float calcLength (){
        //Рассчет длинны отрезка.
        float a, b, c;

        if (xDown < xUp) {
            a = xUp - xDown;
        } else {
            a = xDown - xUp;
        }

        if (yDown < xUp) {
            b = yUp - xDown;
        } else {
            b = yDown - yUp;
        }

        c = (float) Math.sqrt(Math.pow(a,2) + Math.pow(b,2));

        tvLength.setText("Length: " + c);

        return c;
    }
}
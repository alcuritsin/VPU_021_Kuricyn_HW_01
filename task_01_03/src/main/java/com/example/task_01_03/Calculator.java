package com.example.task_01_03;

public class Calculator {
    //!!! Не реализована работа с кнопками памяти...
    //  Известный баг при умножении двух float есть 'хвост' (2.2 * 3.4 = 7.4800005). Пока не знаю как решить.

    private float value;
    private float buffer;
    private String operand;
    private boolean isError;

    private String displayValue, displayBuffer;

    private boolean isLastOperand;

    public Calculator(){
        reset();
    }

    public void reset(){
        //--- Ининциализация калькулятора
        this.value = 0f;
        this.buffer = 0f;
        this.operand = "";
        this.isError = false;
        //this.isLastOperand = false;

        this.displayValue = "0";
        this.displayBuffer = "";
    }

    public void setValue(Float value){
        //--- Метод скорей для того, чтобы можно было класс использовать как отдельную библиотеку
        //т.е. метод позволяет установить сразу значение первого значения
        this.value = value;
    }

    public void setOperand (String operand) {
        //--- Установка оператора вычисления
        boolean isOperand = false;

        switch (operand) {
            case "+":
                this.operand = "+";
                isOperand = true;
                break;
            case "-":
                this.operand = "-";
                isOperand = true;
                break;
            case "*":
                this.operand = "*";
                isOperand = true;
                break;
            case "/":
                this.operand = "/";
                isOperand = true;
                break;
            case "=":
                this.operand = "=";
                break;
        }

        if (isOperand) {
            this.buffer = this.value;
            this.displayBuffer = this.displayValue;
        }
    }

    public String getDisplayValue(){
        //--- Гетер позволяет смотреть из вне, за значением 'value'
        return this.displayValue;
    }

    public String getDisplayBuffer(){
        //--- Гетер позволяет смотреть из вне, за значением 'buffer'
        return this.displayBuffer;
    }

    public String getOperand(){
        //--- Гетер позволяет смотреть из вне, за значением 'operand' (действие)
        return this.operand;
    }

    public Float getValue() {
        //--- Гетер сырого значения 'value'
        //Если нужно получить для дальнейшего использования...
        return this.value;
    }

    public Float getBuffer(){
        //--- Гетер сырого значения 'buffer'
        //Если нужно получить для дальнейшего использования...
        return this.buffer;
    }

    public Float getResult(){
        //--- Запуск вычислений
        boolean isOperand = false;

        switch (this.operand) {
            case "+":
                this.value = addition();
                isOperand = true;
                break;
            case "-":
                this.value = subtraction();
                isOperand = true;
                break;
            case "*":
                this.value = multiplication();
                isOperand = true;
                break;
            case "/":
                if (division() == null) {
                    this.operand = "E";
                    this.displayValue = "Error";
                } else {
                    this.value = division();
                    isOperand = true;
                }
                break;
        }

        if (isOperand && !this.isError) {
            return this.value;
        }
        //--- Если непредвиденная ситуация
        return null;
    }

    public void input(String key){
        //--- Обработка логики накопления числа при вводе.
        //Плюс обработка остальных действий.
        switch (key){
            case ".":
                if (isError) break;
                lastOperand();
                if (this.displayValue.indexOf(".") < 0 || this.displayValue.equals("0")) {
                    this.displayValue += key;
                }
                break;

            case "0":
                if (isError) break;
                lastOperand();
                if (!this.displayValue.equals("0")) {
                    this.displayValue += key;
                }
                setValue(Float.parseFloat(this.displayValue));
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (isError) break;
                lastOperand();
                if (this.displayValue.equals("0")){
                    //this.buffer = this.value;
                    this.displayValue = key;
                } else {
                    this.displayValue += key;
                }
                setValue(Float.parseFloat(this.displayValue));
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                if (isError) break;
                setOperand(key);
                this.isLastOperand = true;
                break;

            case "=":
                if (isError) break;
                if (!this.operand.equals("=")){
                    this.displayBuffer += " " + this.operand + " " + this.displayValue;
                    if (!(getResult() == null)){
                        this.displayValue = String.valueOf(this.value);
                        setOperand(key);
                    }
                }
                trimDisplayValue();
                break;

            case "C":
                reset();
                break;
        }

        if (displayValue.length() > 12) {
            displayValue = displayValue.substring(0,11);
        }
    }

    private float addition() {
        //--- Сложение
        return buffer + value;
    }

    private float subtraction() {
        //--- Вычитание
        return buffer - value;
    }

    private float multiplication(){
        //--- Умножение
        return buffer * value;
    }

    private Float division() {
        //--- Деление
        if (0 == value){
            this.isError = true;
            return null;
        }
        return buffer / value;
    }

    private void lastOperand(){
        //--- Виксация знака (действия)
        if (this.isLastOperand) {
            this.buffer = this.value;
            this.displayValue = "0";
            this.isLastOperand = false;
        }
    }

    private void trimDisplayValue(){
        //--- Обрезание '.0' если результат вычисления целое число
        int indexDot = this.displayValue.indexOf(".");

        if (indexDot > 0){
            String integerPart = this.displayValue.substring(0, indexDot);
            String fractionPart = this.displayValue.substring(indexDot + 1);

            if (fractionPart.equals("0")) {
                this.displayValue = integerPart;
            }
        }
    }

}

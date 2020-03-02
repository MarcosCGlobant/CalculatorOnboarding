package com.globant.calculator.android.mvp.model;

import static com.globant.calculator.android.utils.Constants.EMPTY_STRING;

public class CalculatorModel {

    private String firstNumber = EMPTY_STRING;
    private String secondNumber = EMPTY_STRING;
    private String operator = EMPTY_STRING;

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void clear(){
        firstNumber = EMPTY_STRING;
        secondNumber = EMPTY_STRING;
        operator = EMPTY_STRING;
    }

}

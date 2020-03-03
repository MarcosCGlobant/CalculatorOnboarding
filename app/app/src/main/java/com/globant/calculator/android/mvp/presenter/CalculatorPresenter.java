package com.globant.calculator.android.mvp.presenter;

import android.icu.text.DecimalFormat;

import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.view.CalculatorView;

import static com.globant.calculator.android.utils.Constants.DECIMAL_FORMAT;
import static com.globant.calculator.android.utils.Constants.DIVIDE;
import static com.globant.calculator.android.utils.Constants.DOT_BUTTON;
import static com.globant.calculator.android.utils.Constants.EMPTY_STRING;
import static com.globant.calculator.android.utils.Constants.MINUS;
import static com.globant.calculator.android.utils.Constants.MULTIPLY;
import static com.globant.calculator.android.utils.Constants.NUMBER_ZERO;
import static com.globant.calculator.android.utils.Constants.PLUS;
import static com.globant.calculator.android.utils.Constants.ZERO_DOUBLE;

public class CalculatorPresenter {

    private DecimalFormat decimalFormatForResults = new DecimalFormat(DECIMAL_FORMAT);
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        view.handleOperations(false);
    }

    public void onClearButtonPress() {
        model.clear();
        view.showOperationPressed(NUMBER_ZERO);
        view.showResult(NUMBER_ZERO);
        view.handleDot(true);
        view.handleOperations(false);
    }

    public void onDeleteButtonPress() {
        String reduction = EMPTY_STRING;

        if (model.getOperator().isEmpty()) {
            reduction = model.getFirstNumber();
            if (!reduction.isEmpty()) {
                reduction = reduction.substring(0, reduction.length() - 1);
                model.setFirstNumber(reduction);
                view.showNumberPressed(model.getFirstNumber());
            }
        } else {
            reduction = model.getSecondNumber();
            if (!reduction.isEmpty()) {
                reduction = reduction.substring(0, reduction.length() - 1);
                model.setSecondNumber(reduction);
                view.showNumberPressed(model.getSecondNumber());
            }
        }
    }

    public void onOperatorPressed(String symbol) {
        if (model.getOperator().isEmpty() || model.getSecondNumber().isEmpty()) {
            model.setOperator(symbol);
            view.showOperationPressed(symbol);
            view.handleDot(true);
        } else if (!model.getSecondNumber().isEmpty()) {
            model.setFirstNumber(calculateResult());
            view.showResult(model.getFirstNumber());
            model.setSecondNumber(EMPTY_STRING);
            model.setOperator(symbol);
            view.showOperationPressed(symbol);
            view.handleDot(true);
        }
    }

    public void onEqualsButtonPressed() {
        if ((!model.getFirstNumber().isEmpty()) && (!model.getSecondNumber().isEmpty())) {
            view.showResult(calculateResult());
            model.clear();
        } else if (model.getOperator().isEmpty()) {
            view.showResult(model.getFirstNumber());
            model.clear();
        } else {
            view.showError();
            onClearButtonPress();
        }
    }

    public void onNumberButtonPress(String number) {
        if ((model.getOperator().isEmpty()) && (!model.getFirstNumber().isEmpty())) {
            model.setFirstNumber(model.getFirstNumber() + number);
            view.showNumberPressed(model.getFirstNumber());
        } else if ((!model.getOperator().isEmpty()) && (!model.getSecondNumber().isEmpty())) {
            model.setSecondNumber(model.getSecondNumber() + number);
            view.showNumberPressed(model.getSecondNumber());
        } else if ((!model.getOperator().isEmpty()) && (model.getSecondNumber().isEmpty())) {
            model.setSecondNumber(number);
            view.showNumberPressed(model.getSecondNumber());
        } else {
            model.setFirstNumber(number);
            view.showNumberPressed(model.getFirstNumber());
        }

        if (number.equals(DOT_BUTTON)) {
            view.handleDot(false);
        }
        view.handleOperations(true);

    }

    private String calculateResult() {

        Double firstNumber = Double.parseDouble(model.getFirstNumber());
        Double secondNumber = Double.parseDouble(model.getSecondNumber());
        Double result = ZERO_DOUBLE;

        switch (model.getOperator()) {
            case PLUS:
                result = firstNumber + secondNumber;
                break;
            case MINUS:
                result = firstNumber - secondNumber;
                break;
            case MULTIPLY:
                result = firstNumber * secondNumber;
                break;
            case DIVIDE:
                if (secondNumber.equals(ZERO_DOUBLE)) {
                    view.showError();
                    onClearButtonPress();
                } else {
                    result = firstNumber / secondNumber;
                }
                break;
        }
        return String.valueOf(decimalFormatForResults.format(result));
    }

}

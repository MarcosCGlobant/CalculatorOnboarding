package com.globant.calculator.android.mvp.presenter;

import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.view.CalculatorView;

import static com.globant.calculator.android.utils.Constants.DIVIDE;
import static com.globant.calculator.android.utils.Constants.MINUS;
import static com.globant.calculator.android.utils.Constants.MULTIPLY;
import static com.globant.calculator.android.utils.Constants.NUMBER_ZERO;
import static com.globant.calculator.android.utils.Constants.PLUS;
import static com.globant.calculator.android.utils.Constants.ZERO_DOUBLE;

public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onClearButtonPress() {
        model.clear();
        view.showOperationPressed(NUMBER_ZERO);
        view.showResult(NUMBER_ZERO);
    }

    public void onOperatorPressed(String symbol) {
        model.setOperator(symbol);
        view.showOperationPressed(symbol);
    }

    public void onEqualsButtonPressed() {
        if (model.getOperator().isEmpty()) {
            view.showResult(model.getFirstNumber());
        } else {
            view.showResult(calculateResult());
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
        return result.toString();
    }

}

package com.globant.calculator.android.mvp.presenter;

import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.view.CalculatorView;

import static com.globant.calculator.android.utils.Constants.DIVIDE;
import static com.globant.calculator.android.utils.Constants.MINUS;
import static com.globant.calculator.android.utils.Constants.MULTIPLY;
import static com.globant.calculator.android.utils.Constants.NUMBER_ZERO;
import static com.globant.calculator.android.utils.Constants.PLUS;
import static com.globant.calculator.android.utils.Constants.ZERO_INT;
import static java.lang.Integer.parseInt;

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
        view.showResult(ZERO_INT);
    }

    public void onOperatorPressed(String symbol) {
        model.setOperator(symbol);
        view.showOperationPressed(symbol);
    }

    public void onEqualsButtonPressed() {
        if (model.getOperator().isEmpty()) {
            view.showResult(parseInt(model.getFirstNumber()));
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

    private Integer calculateResult() {

        String firstNumber = model.getFirstNumber();
        String secondNumber = model.getSecondNumber();
        Integer result = ZERO_INT;

        switch (model.getOperator()) {
            case PLUS:
                result = parseInt(firstNumber) + parseInt(secondNumber);
                break;
            case MINUS:
                result = parseInt(firstNumber) - (parseInt(secondNumber));
                break;
            case MULTIPLY:
                result = parseInt(firstNumber) * (parseInt(secondNumber));
                break;
            case DIVIDE:
                result = parseInt(firstNumber) / (parseInt(secondNumber));
                break;
        }
        return result;
    }

}

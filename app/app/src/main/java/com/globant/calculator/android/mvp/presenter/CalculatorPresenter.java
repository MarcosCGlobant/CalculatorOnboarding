package com.globant.calculator.android.mvp.presenter;

import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.view.CalculatorView;

import static com.globant.calculator.android.utils.Constants.DIVIDE;
import static com.globant.calculator.android.utils.Constants.EMPTY_STRING;
import static com.globant.calculator.android.utils.Constants.MINUS;
import static com.globant.calculator.android.utils.Constants.MULTIPLY;
import static com.globant.calculator.android.utils.Constants.NUMBER_ZERO;
import static com.globant.calculator.android.utils.Constants.PLUS;
import static com.globant.calculator.android.utils.Constants.ZERO_INT;

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

    public void onAddButtonPressed(String symbol) {
        model.setOperator(symbol);
        view.showOperationPressed(PLUS);
    }

    public void onMultiplyButtonPressed(String symbol) {
        model.setOperator(symbol);
        view.showOperationPressed(MULTIPLY);
    }

    public void onSubtractionButtonPressed(String symbol) {
        model.setOperator(symbol);
        view.showOperationPressed(MINUS);
    }

    public void onDivideButtonPressed(String symbol) {
        model.setOperator(symbol);
        view.showOperationPressed(DIVIDE);
    }

    public void onEqualsButtonPressed(String symbol) {

    }

    public void onNumberButtonPress(String number){
        if ((model.getOperator().equals(EMPTY_STRING) ) && (!model.getFirstNumber().equals(EMPTY_STRING) )){
            model.setFirstNumber(model.getFirstNumber() + number);
            view.showNumberPressed(model.getFirstNumber());
        }
        else if ((!model.getOperator().equals(EMPTY_STRING)) && (!model.getSecondNumber().equals(EMPTY_STRING))){
            model.setSecondNumber(model.getSecondNumber() + number);
            view.showNumberPressed(model.getSecondNumber());
        }
        else if ((!model.getOperator().equals(EMPTY_STRING)) && (model.getSecondNumber().equals(EMPTY_STRING))){
            model.setSecondNumber(number);
            view.showNumberPressed(model.getSecondNumber());
        }
        else {
            model.setFirstNumber(number);
            view.showNumberPressed(model.getFirstNumber());
        }
    }

}

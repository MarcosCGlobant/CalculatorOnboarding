package com.globant.calculator.android.mvp.view;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorView extends ActivityView {

    @BindView(R.id.calculation_label)
    TextView calculationLabel;
    @BindView(R.id.input_label)
    TextView inputLabel;
    @BindView(R.id.dot_button)
    Button dotButton;
    @BindView(R.id.add_button)
    Button addButton;
    @BindView(R.id.subtraction_button)
    Button subtractionButton;
    @BindView(R.id.divide_button)
    Button divideButton;
    @BindView(R.id.multiply_button)
    Button multiplyButton;
    @BindView(R.id.equals_button)
    Button equalsButton;

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showResult(String result) {
        calculationLabel.setText(result);
    }

    public void showNumberPressed(String number) {
        inputLabel.setText(number);
    }

    public void showOperationPressed(String symbol) {
        inputLabel.setText(symbol);
    }

    public void showError() {
        Toast.makeText(getContext(), "Operación Invalida", Toast.LENGTH_LONG).show();
    }

    public void handleDot(boolean control) {
        dotButton.setEnabled(control);
    }

    public void handleOperations(boolean control) {
        equalsButton.setEnabled(control);
        subtractionButton.setEnabled(control);
        addButton.setEnabled(control);
        multiplyButton.setEnabled(control);
        divideButton.setEnabled(control);
    }
}
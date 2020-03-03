package com.globant.calculator.android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.presenter.CalculatorPresenter;
import com.globant.calculator.android.mvp.view.CalculatorView;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.globant.calculator.android.utils.Constants.DIVIDE;
import static com.globant.calculator.android.utils.Constants.DOT_BUTTON;
import static com.globant.calculator.android.utils.Constants.MINUS;
import static com.globant.calculator.android.utils.Constants.MULTIPLY;
import static com.globant.calculator.android.utils.Constants.NUMBER_EIGHT;
import static com.globant.calculator.android.utils.Constants.NUMBER_FIVE;
import static com.globant.calculator.android.utils.Constants.NUMBER_FOUR;
import static com.globant.calculator.android.utils.Constants.NUMBER_NINE;
import static com.globant.calculator.android.utils.Constants.NUMBER_ONE;
import static com.globant.calculator.android.utils.Constants.NUMBER_SEVEN;
import static com.globant.calculator.android.utils.Constants.NUMBER_SIX;
import static com.globant.calculator.android.utils.Constants.NUMBER_THREE;
import static com.globant.calculator.android.utils.Constants.NUMBER_TWO;
import static com.globant.calculator.android.utils.Constants.NUMBER_ZERO;
import static com.globant.calculator.android.utils.Constants.PLUS;

public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this));
    }

    @OnClick(R.id.number_one)
    public void oneButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_ONE);
    }

    @OnClick(R.id.number_two)
    public void twoButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_TWO);
    }

    @OnClick(R.id.number_three)
    public void threeButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_THREE);
    }

    @OnClick(R.id.number_four)
    public void fourButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_FOUR);
    }

    @OnClick(R.id.number_five)
    public void fiveButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_FIVE);
    }

    @OnClick(R.id.number_six)
    public void sixButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_SIX);
    }

    @OnClick(R.id.number_seven)
    public void sevenButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_SEVEN);
    }

    @OnClick(R.id.number_eight)
    public void eightButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_EIGHT);
    }

    @OnClick(R.id.number_nine)
    public void nineButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_NINE);
    }

    @OnClick(R.id.number_zero)
    public void zeroButtonPressed() {
        presenter.onNumberButtonPress(NUMBER_ZERO);
    }

    @OnClick(R.id.dot_button)
    public void dotButtonPressed() {
        presenter.onNumberButtonPress(DOT_BUTTON);
    }

    @OnClick(R.id.add_button)
    public void addButtonPressed() {
        presenter.onOperatorPressed(PLUS);
    }

    @OnClick(R.id.multiply_button)
    public void multiplyButtonPressed() {
        presenter.onOperatorPressed(MULTIPLY);
    }

    @OnClick(R.id.subtraction_button)
    public void subtractionButtonPressed() {
        presenter.onOperatorPressed(MINUS);
    }

    @OnClick(R.id.divide_button)
    public void divideButtonPressed() {
        presenter.onOperatorPressed(DIVIDE);
    }

    @OnClick(R.id.equals_button)
    public void equalsButtonPressed() {
        presenter.onEqualsButtonPressed();
    }

    @OnClick(R.id.clear_button)
    public void clearButtonPressed() {
        presenter.onClearButtonPress();
    }

}

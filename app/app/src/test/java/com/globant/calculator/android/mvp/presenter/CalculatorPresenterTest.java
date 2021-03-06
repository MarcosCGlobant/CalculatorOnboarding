package com.globant.calculator.android.mvp.presenter;

import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.view.CalculatorView;

import org.junit.Before;
import org.junit.Test;

import static com.globant.calculator.android.utils.Constants.DIVIDE;
import static com.globant.calculator.android.utils.Constants.DOT_BUTTON;
import static com.globant.calculator.android.utils.Constants.EMPTY_STRING;
import static com.globant.calculator.android.utils.Constants.MINUS;
import static com.globant.calculator.android.utils.Constants.MULTIPLY;
import static com.globant.calculator.android.utils.Constants.NUMBER_ZERO;
import static com.globant.calculator.android.utils.Constants.PLUS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CalculatorPresenterTest {

    private CalculatorModel model = new CalculatorModel();
    private CalculatorView mockedView = mock(CalculatorView.class);
    private CalculatorPresenter presenter;
    private static final String NUMBER_TEST = "50";
    private static final String NUMBER_TEST_WITH_DOT = "50.12";
    private static final String REDUCED_NUMBER_TEST = "5";
    private static final String REDUCED_NUMBER_TEST_WITH_DOT = "50.1";
    private static final String NUMBER_TEST_TIMES_TWO = "100.0";
    private static final String MULTIPLY_RESULT = "25";
    private static final String ADD_TO_MATCH_RESULT = ".0";

    @Before
    public void setUp() {
        presenter = new CalculatorPresenter(model, mockedView);
    }

    @Test
    public void onClearButtonPressedTest() {
        presenter.onClearButtonPress();

        verify(mockedView).showOperationPressed(NUMBER_ZERO);
        verify(mockedView).showResult(NUMBER_ZERO);
        verify(mockedView).handleDot(true);
        verify(mockedView, times(2)).handleOperations(false);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onDeleteButtonPressedFirstNumberIsReducedTest() {
        model.setFirstNumber(NUMBER_TEST);

        presenter.onDeleteButtonPress();

        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(REDUCED_NUMBER_TEST, model.getFirstNumber());

        verify(mockedView).showNumberPressed(REDUCED_NUMBER_TEST);
        verify(mockedView).handleDot(true);
    }

    @Test
    public void onDeleteButtonPressedSecondNumberIsReducedTest() {
        model.setSecondNumber(NUMBER_TEST_WITH_DOT);
        model.setOperator(PLUS);

        presenter.onDeleteButtonPress();

        assertNotEquals(EMPTY_STRING, model.getOperator());
        assertEquals(REDUCED_NUMBER_TEST_WITH_DOT, model.getSecondNumber());

        verify(mockedView).showNumberPressed(REDUCED_NUMBER_TEST_WITH_DOT);
        verify(mockedView).handleDot(false);
    }

    @Test
    public void onOperatorPressedWhenOperatorEmptyShowOperatorAndSetOnModel() {
        model.setFirstNumber(NUMBER_TEST);

        presenter.onOperatorPressed(PLUS);

        assertNotEquals(EMPTY_STRING, model.getOperator());

        verify(mockedView).showOperationPressed(PLUS);
        verify(mockedView).handleDot(true);
    }

    @Test
    public void onOperatorPressedWithSecondNumberShowOperatorAndSetOnModel() {
        model.setFirstNumber(NUMBER_TEST);
        model.setOperator(PLUS);
        model.setSecondNumber(NUMBER_TEST);

        presenter.onOperatorPressed(MINUS);

        verify(mockedView).showResult(NUMBER_TEST_TIMES_TWO);

        assertEquals(EMPTY_STRING, model.getSecondNumber());
        assertNotEquals(EMPTY_STRING, model.getOperator());

        verify(mockedView).showOperationPressed(MINUS);
        verify(mockedView).handleDot(true);
    }

    @Test
    public void onEqualsPressedWithFirstAndSecondNumberAndWithoutThoseNumbersBeenJustDots() {
        model.setFirstNumber(NUMBER_TEST);
        model.setOperator(PLUS);
        model.setSecondNumber(NUMBER_TEST);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showResult(NUMBER_TEST_TIMES_TWO);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onEqualsPressedWithFirstNumberAndNoOperatorAndWithoutNumberBeenADot() {
        model.setFirstNumber(NUMBER_TEST);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showResult(NUMBER_TEST);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onEqualsPressedWithWrongConditionsShowErrorMessage() {
        model.setFirstNumber(NUMBER_TEST);
        model.setOperator(PLUS);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showError();
        verify(mockedView).showOperationPressed(NUMBER_ZERO);
        verify(mockedView).showResult(NUMBER_ZERO);
        verify(mockedView).handleDot(true);
        verify(mockedView, times(2)).handleOperations(false);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onNumberButtonPressedWithFirstNumberAndNoOperatorAddInputAndShowFullNumber() {
        model.setFirstNumber(REDUCED_NUMBER_TEST);

        presenter.onNumberButtonPress(NUMBER_ZERO);

        assertEquals(NUMBER_TEST, model.getFirstNumber());

        verify(mockedView).showNumberPressed(NUMBER_TEST);
        verify(mockedView).handleOperations(true);
    }

    @Test
    public void onNumberButtonPressedWithSecondNumberAndOperatorAddInputAndShowFullNumber() {
        model.setFirstNumber(NUMBER_TEST);
        model.setOperator(PLUS);
        model.setSecondNumber(REDUCED_NUMBER_TEST);

        presenter.onNumberButtonPress(NUMBER_ZERO);

        assertEquals(NUMBER_TEST, model.getSecondNumber());

        verify(mockedView).showNumberPressed(NUMBER_TEST);
        verify(mockedView).handleOperations(true);
    }

    @Test
    public void onNumberButtonPressedWithoutSecondNumberAndWithOperatorAddDotAndShowFullNumber() {
        model.setFirstNumber(NUMBER_TEST);
        model.setOperator(PLUS);

        presenter.onNumberButtonPress(DOT_BUTTON);

        assertEquals(DOT_BUTTON, model.getSecondNumber());

        verify(mockedView).showNumberPressed(DOT_BUTTON);
        verify(mockedView).handleDot(false);
        verify(mockedView).handleOperations(true);
    }

    @Test
    public void onNumberButtonPressedWithoutFirstNumberAddDotAndShowFullNumber() {
        presenter.onNumberButtonPress(DOT_BUTTON);

        assertEquals(DOT_BUTTON, model.getFirstNumber());

        verify(mockedView).showNumberPressed(DOT_BUTTON);
        verify(mockedView).handleDot(false);
        verify(mockedView).handleOperations(true);
    }

    @Test
    public void onEqualsPressedWithFirstAndSecondNumberWithoutThoseNumbersBeenJustDotsAndWithAMinusOperator() {
        model.setFirstNumber(NUMBER_TEST);
        model.setOperator(MINUS);
        model.setSecondNumber(NUMBER_TEST);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showResult(NUMBER_ZERO + ADD_TO_MATCH_RESULT);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onEqualsPressedWithFirstAndSecondNumberWithoutThoseNumbersBeenJustDotsAndWithAMultiplyOperator() {
        model.setFirstNumber(REDUCED_NUMBER_TEST);
        model.setOperator(MULTIPLY);
        model.setSecondNumber(REDUCED_NUMBER_TEST);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showResult(MULTIPLY_RESULT + ADD_TO_MATCH_RESULT);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onEqualsPressedWithFirstAndSecondNumberWithoutThoseNumbersBeenJustDotsAndWithADivideOperator() {
        model.setFirstNumber(MULTIPLY_RESULT);
        model.setOperator(DIVIDE);
        model.setSecondNumber(REDUCED_NUMBER_TEST);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showResult(REDUCED_NUMBER_TEST + ADD_TO_MATCH_RESULT);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }

    @Test
    public void onEqualsPressedWithFirstAndSecondNumberWithoutThoseNumbersBeenJustDotsAndWithADivideOperatorAndAZero() {
        model.setFirstNumber(MULTIPLY_RESULT);
        model.setOperator(DIVIDE);
        model.setSecondNumber(NUMBER_ZERO);

        presenter.onEqualsButtonPressed();

        verify(mockedView).showError();
        verify(mockedView).showOperationPressed(NUMBER_ZERO);
        verify(mockedView).showResult(NUMBER_ZERO);
        verify(mockedView).handleDot(true);
        verify(mockedView, times(2)).handleOperations(false);

        assertEquals(EMPTY_STRING, model.getFirstNumber());
        assertEquals(EMPTY_STRING, model.getOperator());
        assertEquals(EMPTY_STRING, model.getSecondNumber());
    }
}
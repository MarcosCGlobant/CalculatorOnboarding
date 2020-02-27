package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;

public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

}

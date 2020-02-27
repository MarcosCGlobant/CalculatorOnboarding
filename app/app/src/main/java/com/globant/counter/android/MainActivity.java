package com.globant.counter.android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.presenter.CalculatorPresenter;
import com.globant.counter.android.mvp.view.CalculatorView;


public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView());
    }
}

package com.globant.calculator.android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.globant.calculator.android.mvp.model.CalculatorModel;
import com.globant.calculator.android.mvp.presenter.CalculatorPresenter;
import com.globant.calculator.android.mvp.view.CalculatorView;


public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView());
    }
}

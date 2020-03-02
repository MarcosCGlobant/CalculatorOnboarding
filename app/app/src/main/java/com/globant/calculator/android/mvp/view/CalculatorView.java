package com.globant.calculator.android.mvp.view;

import android.app.Activity;
import android.widget.TextView;
import com.example.myapplication.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorView extends ActivityView {

    @BindView(R.id.calculation_label)
    TextView calculationLabel;
    @BindView(R.id.input_label)
    TextView inputLabel;

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showResult(Integer result) {
        calculationLabel.setText(result.toString());
    }

   public void showNumberPressed(String number){
       inputLabel.setText(number);
   }

   public void showOperationPressed(String symbol){
       inputLabel.setText(symbol);
   }

}
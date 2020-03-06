package com.globant.calculator.android.utils;

import java.text.DecimalFormat;

import static com.globant.calculator.android.utils.Constants.DECIMAL_FORMAT;

public class DecimalUtils {

    public String getResultWithNewFormat (Double number){

        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);

        return decimalFormat.format(number);
    }
}

package com.sergej.testsudent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by sergej on 27.01.16.
 */
public class StatisticActivity  extends Activity
{

    private ProgressBar prBarWords;
    private ProgressBar prBarNumbers;
    private TextView tvWordProcent;
    private TextView tvNumProcent;


    public static final String SEND_STATISTIC = "send_statistic";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        prBarNumbers = (ProgressBar)findViewById(R.id.progBarNum);
        prBarWords = (ProgressBar)findViewById(R.id.progBarWord);
        tvNumProcent = (TextView)findViewById(R.id.tvNumProcent);
        tvWordProcent = (TextView)findViewById(R.id.tvWordProcent);

        int[] mas1 = new int[3];
        mas1 = getIntent().getIntArrayExtra(SEND_STATISTIC);
        int countRim = mas1[0];
        int countWord = mas1[1];
        int countAll = mas1[2];

        prBarNumbers.setMax(countAll);
        prBarNumbers.setProgress(countRim);

        prBarWords.setMax(countAll);
        prBarWords.setProgress(countWord);

        if ((countAll != 0)&&(countRim != 0)){
            double d1 = countRim*100/countAll;
            tvNumProcent.setText(String.valueOf(d1 + "%"));
        } else {
            tvNumProcent.setText(String.valueOf(countRim + "%"));
        }

        if ((countAll != 0)&&(countWord != 0)){
            double d = countWord*100/countAll;
            tvWordProcent.setText(String.valueOf(d + "%"));
        } else {
            tvWordProcent.setText(String.valueOf(countWord + "%"));

        }









    }

}

package com.sergej.testsudent;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private Button butGenerate;
    private Button butAccept;
    private Button butStatistica;
    private TextView tvKey;
    private LinearLayout linearLayout;
    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;
    private Button but5;
    private Button but6;
    private Button but8;
    private Button but9;
    private Button but0;
    private Button but7;
    private Button butBackSpace;

    private ArrayList<String> mas1 = new ArrayList<String>();
    private ArrayList<String> mas2 = new ArrayList<String>();
    private ArrayList<TextView> mas3 = new ArrayList<>();
    private ArrayList<String> mas4 = new ArrayList<>();
    private ArrayList<Button> mas5 = new ArrayList<>();
    private ArrayList<String> masRim;
    private ArrayList<String> masWord;

    private int countRim ;
    private int countWord ;
    private int countAll;
    private String posledovatelnost;
    private String proverka;
    private static final int timeToLook = 3;
    private int btnCountClick  = 0;
    private int isMasRim;
    private final static String INSTANCE_STATE = "com.sergej.teststudent.InstanceState";

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)findViewById(R.id.linLayout);
        but1 = (Button)findViewById(R.id.but1);
        but2 = (Button)findViewById(R.id.but2);
        but3 = (Button)findViewById(R.id.but3);
        but4 = (Button)findViewById(R.id.but4);
        but5 = (Button)findViewById(R.id.but5);
        but6 = (Button)findViewById(R.id.but6);
        but7 = (Button)findViewById(R.id.but7);
        but8 = (Button)findViewById(R.id.but8);
        but9 = (Button)findViewById(R.id.but9);
        but0 = (Button)findViewById(R.id.but0);
        butBackSpace = (Button)findViewById(R.id.backspace);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        tvKey = (TextView)findViewById(R.id.key);

        butAccept = (Button)findViewById(R.id.butAccept);
        butGenerate = (Button)findViewById(R.id.butGenerate);
        butStatistica = (Button)findViewById(R.id.butStatistica);
        //pbTime = (ProgressBar)findViewById(R.id.pbTime);

        but1.setOnClickListener(listener);
        but2.setOnClickListener(listener);
        but3.setOnClickListener(listener);
        but4.setOnClickListener(listener);
        but5.setOnClickListener(listener);
        but6.setOnClickListener(listener);
        but7.setOnClickListener(listener);
        but8.setOnClickListener(listener);
        but9.setOnClickListener(listener);
        but0.setOnClickListener(listener);
        butBackSpace.setOnClickListener(btnFunctClick);
        butAccept.setOnClickListener(btnFunctClick);
        butGenerate.setOnClickListener(btnFunctClick);
        butStatistica.setOnClickListener(btnFunctClick);

        //инициализация массивов
        initMas1();
        initMas2();
        initMas3();
        initMas4();
        initMas5();
        masRim = new ArrayList<String>(mas1);
        masWord = new ArrayList<String>(mas2);
        //isMasRim = 0;


        Log.i("btnCount", String.valueOf(btnCountClick));

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_STATISTIC, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            int numIndex = cursor.getColumnIndex(DBHelper.KEY_NUM);
            int wordIndex = cursor.getColumnIndex(DBHelper.KEY_WORD);
            int statallIndex = cursor.getColumnIndex(DBHelper.KEY_ALLSTAT);

            countRim = cursor.getInt(numIndex);
            countWord = cursor.getInt(wordIndex);
            countAll = cursor.getInt(statallIndex);
        } else {
            countRim = 0;
            countWord = 0;
            countAll = 0;
        }
        cursor.close();
        dbHelper.close();


    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String str;
            str = tvKey.getText().toString();
            btnCountClick++;

            if ( btnCountClick <= 6 ) {

                if (isSpace(str) == false){
                    str += " ";
                }
                switch (view.getId()) {
                    case R.id.but1:
                        str += but1.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but2:
                        str += but2.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but3:
                        str += but3.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but4:
                        str += but4.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but5:
                        str += but5.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but6:
                        str += but6.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but7:
                        str += but7.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but8:
                        str += but8.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but9:
                        str += but9.getText().toString();
                        tvKey.setText(str);
                        break;
                    case R.id.but0:
                        str += but0.getText().toString();
                        tvKey.setText(str);
                        break;
                }
                Log.i("btnCount", String.valueOf(btnCountClick));
            } else {
                --btnCountClick;
            }

        };
    };

    View.OnClickListener btnFunctClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backspace: {

                    String str = tvKey.getText().toString();

                    --btnCountClick;
                    if (btnCountClick >= 0) {
                        String help = "";
                        int num = 0;

                        for (int i = str.length() - 1; i >= 0; i--) {
                            if ((str.charAt(i) == ' ')) {
                                num = i;
                                break;
                            }
                        }

                        if (num == 0){
                            tvKey.setText(null);
                        } else {
                            for (int i = 0; i < num; i++) {
                                help += str.charAt(i);
                            }
                            tvKey.setText(help);
                        }

                    } else {
                        btnCountClick++;
                    }
                }
                    break;
                //кнопка - статистика
                case R.id.butStatistica: {
                    int[] mas = new int[3];
                    mas[0] = countRim;
                    mas[1] = countWord;
                    mas[2] = countAll;

                    Intent i = new Intent(MainActivity.this, StatisticActivity.class);
                    i.putExtra(StatisticActivity.SEND_STATISTIC, mas);
                    startActivity(i);
                }
                break;

                //кнопка - генерировать
                case R.id.butGenerate: {
                    butGenerate.setEnabled(false);
                    butAccept.setEnabled(false);


                    linearLayout.setVisibility(View.INVISIBLE);
                    tvKey.setText("");
                    velikiyRandomMas();
                    proverka = "";
                    proverka = textView1.getText().toString();
                    posledovatelnost = "";
                    for (int i = 0; i < mas3.size(); i++) {
                        posledovatelnost += String.valueOf(mas3.get(i).getText());
                    }
                    btnCountClick = 0;
                    //отсчет времени
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mas1.contains(mas3.get(0).getText())) {
                                setButText(masRim);
                            } else {
                                setButText(masWord);
                            }
                            linearLayout.setVisibility(View.VISIBLE);
                            butAccept.setEnabled(true);
                            butGenerate.setEnabled(true);

                            labelSetX();
                        }
                    }, timeToLook * 1000);

                }
                break;

                //кнопка - принять
                case R.id.butAccept: {



                    String answer = String.valueOf(tvKey.getText());
                    String temp = "";

                    for (int i = 0; i < answer.length(); i++) {
                        if (answer.charAt(i) != ' ') {
                            temp += answer.charAt(i);
                        }
                    }
                    answer = temp;

                    if (answer.equals(posledovatelnost)) {
                        Toast.makeText(MainActivity.this, R.string.true_answer, Toast.LENGTH_SHORT).show();

                        if (mas1.contains(proverka)) {
                            countRim++;
                        } else {
                            countWord++;
                        }
                    } else {
                        Toast.makeText(MainActivity.this, R.string.false_answer, Toast.LENGTH_SHORT).show();
                    }

                    countAll++;

                    linearLayout.setVisibility(View.INVISIBLE);
                    tvKey.setText("");

                    butAccept.setEnabled(false);

                }
                break;
            }
        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        database.delete(DBHelper.TABLE_STATISTIC, null, null);

        contentValues.put(DBHelper.KEY_NUM, countRim);
        contentValues.put(DBHelper.KEY_WORD, countWord);
        contentValues.put(DBHelper.KEY_ALLSTAT, countAll);

        database.insert(DBHelper.TABLE_STATISTIC, null, contentValues);
        dbHelper.close();
    }


    //проверка строки на пробел
    public boolean isSpace(String string) {
        if (string.length() != 0) {
            if (string.charAt(string.length() - 1) == ' ') {
                return true;
            }
        }
        return false;
    }

    //великий рандом массивов
    public void velikiyRandomStr(ArrayList<String> arr){
        int count = arr.size();
        for (int i = 0; i < arr.size(); i++){
            int temp =(int)(Math.random()*count);
            String str = arr.get(temp);
            arr.add(str);
            arr.remove(temp);
            count--;
        }
    }

    public void velikiyRandomMas(){
        int temp = (int)(Math.random()*2);
        if (temp == 0){
            velikiyRandomStr(mas1);
            labelSetStr(mas1);
        } else {
            velikiyRandomStr(mas2);
            labelSetStr(mas2);
        }
    }

    //заполнение лейблов
    public void labelSetStr(ArrayList<String> arr){
        velikiyRandomStr(mas4);
        for (int i = 0; i < mas3.size(); i++){
            mas3.get(i).setText(String.valueOf(arr.get(i)));
            mas3.get(i).setTextColor(Color.parseColor(mas4.get(i)));
        }
    }

    public void labelSetX(){
        for (int i = 0; i < mas3.size(); i++){
            mas3.get(i).setText("X");
            mas3.get(i).setTextColor(Color.BLACK);
        }
    }

    //смена текста кнопок
    public void setButText(ArrayList<String> arr){
        for (int i = 0; i < mas5.size(); i++){
            mas5.get(i).setText(arr.get(i));
        }
    }

    public void initMas1(){
        mas1.add("I");
        mas1.add("II");
        mas1.add("III");
        mas1.add("IV");
        mas1.add("V");
        mas1.add("VI");
        mas1.add("VII");
        mas1.add("VIII");
        mas1.add("IX");
        mas1.add("X");
    }

    public void initMas2(){
        mas2.add("один");
        mas2.add("два");
        mas2.add("три");
        mas2.add("четыре");
        mas2.add("пять");
        mas2.add("шесть");
        mas2.add("семь");
        mas2.add("восемь");
        mas2.add("девять");
        mas2.add("нуль");

    }

    public void initMas3(){
        mas3.add(textView1);
        mas3.add(textView2);
        mas3.add(textView3);
        mas3.add(textView4);
        mas3.add(textView5);
        mas3.add(textView6);


    }

    public void initMas4(){
        mas4.add("#000000");
        mas4.add("#FF0000");
        mas4.add("#00FF00");
        mas4.add("#0000FF");
        mas4.add("#FF8000"); //оранжевый
        mas4.add("#FF007F");    // розовый
        mas4.add("#FF00FF");    //
        mas4.add("#6600CC");

    }

    //кнопки
    public void initMas5(){
        mas5.add(but1);
        mas5.add(but2);
        mas5.add(but3);
        mas5.add(but4);
        mas5.add(but5);
        mas5.add(but6);
        mas5.add(but7);
        mas5.add(but8);
        mas5.add(but9);
        mas5.add(but0);

    }


    //сеттеры
    public void setCountWord(int countWord) {
        this.countWord = countWord;
    }

    public void setCounrRim(int counrRim) {
        this.countRim = counrRim;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }
}

package com.sergej.testsudent;

/**
 * Created by sergej on 25.01.16.
 */
public class Statistic {
    private int countAll;
    private int countRim;
    private int countWord;

    public Statistic(int countRim, int countWord, int countAll){
        this.countRim = countRim;
        this.countWord = countWord;
        this.countAll = countAll;
    }

    public int getCountAll() {
        return countAll;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    public int getCountRim() {
        return countRim;
    }

    public void setCountRim(int countRim) {
        this.countRim = countRim;
    }

    public int getCountWord() {
        return countWord;
    }

    public void setCountWord(int countWord) {
        this.countWord = countWord;
    }
}

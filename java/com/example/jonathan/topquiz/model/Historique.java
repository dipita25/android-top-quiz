package com.example.jonathan.topquiz.model;

public class Historique {
    private String mName;
    private int mScore;
    private int mColor;

    public Historique(int color,String name, int score) {
        mName = name;
        mScore = score;
        mColor = color;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}

package com.example.juexingzhe.MyStepView.model;

import android.graphics.drawable.Drawable;

/**
 * Created by juexingzhe on 2017/5/7.
 */

public class StepBean {

    public static final int STEP_COMPLETED = 0;
    public static final int STEP_UNCOMPLETED = 1;

    private String name;
    private int state;
    private Drawable mDrawable;

    public StepBean() {

    }

    public StepBean(String name, int state, Drawable drawable) {
        this.name = name;
        this.state = state;
        this.mDrawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
}

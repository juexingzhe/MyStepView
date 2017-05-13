package com.example.juexingzhe.MyStepView.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.juexingzhe.MyStepView.R;
import com.example.juexingzhe.MyStepView.model.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juexingzhe on 2017/5/7.
 */

public class StepViewIndicator extends View {
    private static final int BASE_INDICATOR_NUM = 180;

    private float mCenterY;

    private float mBigCircleRadius;
    private float mSamllCircleRadius;
    private float mPaddingCircle;
    private List<Float> mBigCircleCenterPosList;//大圆心位置
    private List<Float> mSmallCircleCenterPosList;//小圆心位置

    private Drawable mCompleteSmallCircleDrawable;
    private Drawable mUncompleteSmallCircleDrawable;

    private int mStepNums;

    private List<StepBean> mStepBeanList;

    private int mCompletedPos;

    private onDrawIndicatorListener mOnDrawIndicatorListener;


    public StepViewIndicator(Context context) {
        super(context);
        init();
    }

    public StepViewIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StepViewIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mBigCircleRadius = 0.30f * BASE_INDICATOR_NUM;
        mSamllCircleRadius = 0.12f * BASE_INDICATOR_NUM;
        mPaddingCircle = BASE_INDICATOR_NUM;


        mBigCircleCenterPosList = new ArrayList<>();
        mSmallCircleCenterPosList = new ArrayList<>();


        mCompleteSmallCircleDrawable = ContextCompat.getDrawable(getContext(), R.drawable.complete);
        mUncompleteSmallCircleDrawable = ContextCompat.getDrawable(getContext(), R.drawable.uncomplete);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = BASE_INDICATOR_NUM * 2;

        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(widthMeasureSpec)) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }

        int height = BASE_INDICATOR_NUM;
        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(heightMeasureSpec)) {
            height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterY = getHeight() * 0.5f;

        mBigCircleCenterPosList.clear();
        mSmallCircleCenterPosList.clear();

        for (int i = 0; i < mStepNums; i++) {
            float paddingLeft = (getWidth() - mBigCircleRadius * 2 - mPaddingCircle * (2 * mStepNums - 2)) / 2;

            float bigCircleCenterPos = paddingLeft + mBigCircleRadius + mPaddingCircle * 2 * i;
            mBigCircleCenterPosList.add(bigCircleCenterPos);
            mSmallCircleCenterPosList.add(bigCircleCenterPos + mPaddingCircle);
        }

        if (null != mOnDrawIndicatorListener) {
            mOnDrawIndicatorListener.onDrawIndicator();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mOnDrawIndicatorListener != null) {
            mOnDrawIndicatorListener.onDrawIndicator();
        }

        //画大圆
        for (int i = 0; i < mBigCircleCenterPosList.size(); i++) {
            float bigCircleCenterPos = mBigCircleCenterPosList.get(i);
            Rect rect = new Rect((int) (bigCircleCenterPos - mBigCircleRadius), (int) (mCenterY - mBigCircleRadius),
                    (int) (bigCircleCenterPos + mBigCircleRadius), (int) (mCenterY + mBigCircleRadius));

            StepBean stepBean = mStepBeanList.get(i);
            Drawable drawable = stepBean.getDrawable();

            drawable.setBounds(rect);
            drawable.draw(canvas);
        }

        //画小圆
        for (int i = 0; i < mBigCircleCenterPosList.size() - 1; i++) {
            float smallCircleCenterPos = mSmallCircleCenterPosList.get(i);
            Rect rect = new Rect((int) (smallCircleCenterPos - mSamllCircleRadius), (int) (mCenterY - mSamllCircleRadius),
                    (int) (smallCircleCenterPos + mSamllCircleRadius), (int) (mCenterY + mSamllCircleRadius));

            if (i < mCompletedPos){
                mCompleteSmallCircleDrawable.setBounds(rect);
                mCompleteSmallCircleDrawable.draw(canvas);
            }else {
                mUncompleteSmallCircleDrawable.setBounds(rect);
                mUncompleteSmallCircleDrawable.draw(canvas);
            }
        }


    }

    public void setStepBeanList(List<StepBean> stepBeanList) {
        mStepBeanList = stepBeanList;
        mStepNums = stepBeanList.size();

        if (null != mStepBeanList && mStepNums > 0) {
            for (int i = 0; i < mStepNums; i++) {
                StepBean stepBean = mStepBeanList.get(i);
                if (stepBean.getState() == StepBean.STEP_COMPLETED) {
                    mCompletedPos = i;
                }
            }
        }

        requestLayout();
    }

    public void setOnDrawIndicatorListener(onDrawIndicatorListener onDrawIndicatorListener) {
        mOnDrawIndicatorListener = onDrawIndicatorListener;
    }

    public int getCompletedPos() {
        return mCompletedPos;
    }

    public List<Float> getBigCircleCenterPosList() {
        return mBigCircleCenterPosList;
    }

    public interface onDrawIndicatorListener {
        void onDrawIndicator();
    }

}

package com.example.juexingzhe.MyStepView.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juexingzhe.MyStepView.R;
import com.example.juexingzhe.MyStepView.model.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juexingzhe on 2017/5/7.
 */

public class StepView extends LinearLayout implements StepViewIndicator.onDrawIndicatorListener {


    private RelativeLayout mTextContainer;
    private StepViewIndicator mStepViewIndicator;

    private List<StepBean> mStepBeanList;

    private int mUncompleteTextColor;
    private int mCompleteTextColor;

    private int mTextSize = 14;


    public StepView(Context context) {
        super(context);
        init();
    }

    public StepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.stepview, this);
        mStepViewIndicator = (StepViewIndicator) rootview.findViewById(R.id.stepview_indicator);
        mStepViewIndicator.setOnDrawIndicatorListener(this);

        mTextContainer = (RelativeLayout) rootview.findViewById(R.id.stepview_text_container);
    }

    public StepView setStepBeanList(List<StepBean> stepBeanList) {
        mStepBeanList = stepBeanList;
        mStepViewIndicator.setStepBeanList(stepBeanList);
        return this;
    }

    public StepView setUncompleteTextColor(int uncompleteTextColor) {
        mUncompleteTextColor = uncompleteTextColor;
        return this;
    }

    public StepView setCompleteTextColor(int completeTextColor) {
        mCompleteTextColor = completeTextColor;
        return this;
    }

    public StepView setTextSize(int textSize) {
        if (textSize > 0) {
            mTextSize = textSize;
        }

        return this;
    }


    public static class Builder {

        private int uncompleteTextColor;
        private int completeTextColor;

        private int textSize;

        private List<String> textIndicator;
        private List<Integer> completeDrawableResIdList;
        private List<Integer> uncompleteDrawableResIdList;
        private List<StepBean> stepBeanList;

        private int curPos;


        public Builder() {
            textIndicator = new ArrayList<>();
            completeDrawableResIdList = new ArrayList<>();
            uncompleteDrawableResIdList = new ArrayList<>();
            stepBeanList = new ArrayList<>();
            textSize = 14;
            curPos = 0;
        }

        public Builder setTextIndicator(List<String> textList) {
            textIndicator = textList;
            return this;
        }

        public Builder setCompleteDrawableResIdList(List<Integer> resIdList) {
            completeDrawableResIdList = resIdList;
            return this;
        }

        public Builder setUncompleteDrawableResIdList(List<Integer> resIdList) {
            uncompleteDrawableResIdList = resIdList;
            return this;
        }

        public Builder setUncompleteTextColor(int textColor) {
            uncompleteTextColor = textColor;
            return this;
        }

        public Builder setCompleteTextColor(int textColor) {
            completeTextColor = textColor;
            return this;
        }

        public Builder setTextSize(int txtSize) {
            textSize = txtSize;
            return this;
        }

        public Builder setCurrrentPos(int currrentPos) {
            curPos = currrentPos;
            return this;
        }


        public void build(StepView stepView) {
            stepBeanList.clear();
            String name;
            int state = StepBean.STEP_COMPLETED;
            Drawable drawable;
            StepBean stepBean;
            for (int i = 0; i < textIndicator.size(); i++) {
                name = textIndicator.get(i);
                drawable = ContextCompat.getDrawable(stepView.getContext(), completeDrawableResIdList.get(i));
                if (i > curPos) {
                    state = StepBean.STEP_UNCOMPLETED;
                    drawable = ContextCompat.getDrawable(stepView.getContext(), uncompleteDrawableResIdList.get(i));
                }
                stepBean = new StepBean(name, state, drawable);
                stepBeanList.add(stepBean);
            }

            stepView.setTextSize(textSize)
                    .setCompleteTextColor(completeTextColor)
                    .setUncompleteTextColor(uncompleteTextColor)
                    .setStepBeanList(stepBeanList);
        }

    }

    @Override
    public void onDrawIndicator() {
        if (null != mTextContainer) {
            mTextContainer.removeAllViews();

            List<Float> bigCircleCenterPosList = mStepViewIndicator.getBigCircleCenterPosList();
            int completedPos = mStepViewIndicator.getCompletedPos();
            if (null == bigCircleCenterPosList || null == mStepBeanList || !(bigCircleCenterPosList.size() > 0)) {
                return;
            }
            for (int i = 0; i < mStepBeanList.size(); i++) {
                TextView textView = new TextView(getContext());
                StepBean stepBean = mStepBeanList.get(i);
                textView.setTextSize(mTextSize);
                textView.setText(stepBean.getName());

                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                textView.measure(spec, spec);
                // getMeasuredWidth
                int measuredWidth = textView.getMeasuredWidth();
                textView.setX(bigCircleCenterPosList.get(i) - measuredWidth / 2);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                if (i <= completedPos) {
                    textView.setTextColor(mCompleteTextColor);
                } else {
                    textView.setTextColor(mUncompleteTextColor);
                }

                mTextContainer.addView(textView);

            }
        }
    }
}

package com.example.juexingzhe.MyStepView.presenter;

import android.support.v4.content.ContextCompat;

import com.example.juexingzhe.MyStepView.R;
import com.example.juexingzhe.MyStepView.contract.StepViewContract;
import com.example.juexingzhe.MyStepView.model.StepBean;
import com.example.juexingzhe.MyStepView.view.StepView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juexingzhe on 2017/5/7.
 */

public class StepViewPresenter implements StepViewContract.StepPresenter {

    private StepViewContract.StepCompleteView mStepCompleteView;

    private List<StepBean> mStepBeanList;


    private List<String> mTextIndicator;
    private List<Integer> mCompleteDrawableResIdList;
    private List<Integer> mUncompleteDrawableResIdList;

    private static int mCurrentPos;

    public StepViewPresenter(StepViewContract.StepCompleteView stepCompleteView) {
        mStepCompleteView = stepCompleteView;
        mStepBeanList = new ArrayList<>();
        mTextIndicator = new ArrayList<>();
        mCompleteDrawableResIdList = new ArrayList<>();
        mUncompleteDrawableResIdList = new ArrayList<>();
        mTextIndicator.add("下单");
        mTextIndicator.add("送货");
        mTextIndicator.add("签收");

        mCompleteDrawableResIdList.add(R.drawable.complete);
        mCompleteDrawableResIdList.add(R.drawable.complete);
        mCompleteDrawableResIdList.add(R.drawable.complete);

        mUncompleteDrawableResIdList.add(R.drawable.uncomplete);
        mUncompleteDrawableResIdList.add(R.drawable.uncomplete);
        mUncompleteDrawableResIdList.add(R.drawable.uncomplete);
    }


    @Override
    public void initData(List<String> textIndicators, List<Integer> completeRes, List<Integer> uncompleteRes) {
        if (null != textIndicators) {
            mTextIndicator = textIndicators;
        }

        if (null != completeRes) {
            mCompleteDrawableResIdList = completeRes;
        }

        if (null != uncompleteRes) {
            mUncompleteDrawableResIdList = uncompleteRes;
        }

        getBuilder().build(mStepCompleteView.getStepView());
    }


    @Override
    public void refreshData() {
        refreshCurPos();

        refreshStepView();
    }

    private StepView.Builder getBuilder() {

        return new StepView.Builder().setTextIndicator(mTextIndicator)
                .setCompleteTextColor(ContextCompat.getColor(mStepCompleteView.getStepView().getContext(), R.color.complete_text_color))
                .setUncompleteTextColor(ContextCompat.getColor(mStepCompleteView.getStepView().getContext(), R.color.uncomplete_text_color))
                .setCompleteDrawableResIdList(mCompleteDrawableResIdList)
                .setUncompleteDrawableResIdList(mUncompleteDrawableResIdList);
    }

    private void refreshStepView() {
        getBuilder().setCurrrentPos(mCurrentPos).build(mStepCompleteView.getStepView());
    }

    private void refreshCurPos() {
        mCurrentPos++;

        if (mCurrentPos >= mTextIndicator.size()) {
            mCurrentPos = 0;
        }
    }

}

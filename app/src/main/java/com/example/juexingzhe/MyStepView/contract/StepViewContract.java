package com.example.juexingzhe.MyStepView.contract;

import com.example.juexingzhe.MyStepView.view.StepView;

import java.util.List;

/**
 * Created by juexingzhe on 2017/5/7.
 */

public interface StepViewContract {

    interface StepCompleteView {
        /**
         * set presenter
         *
         * @param stepPresenter
         */
        void setPresenter(StepPresenter stepPresenter);

        StepView getStepView();
    }

    interface StepPresenter {

        void initData(List<String> textIndicators, List<Integer> completeRes, List<Integer> uncompleteRes);

        /**
         * 更新数据
         *
         * @param
         */
        void refreshData();
    }

}

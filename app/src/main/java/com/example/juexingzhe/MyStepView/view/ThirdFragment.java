package com.example.juexingzhe.MyStepView.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.juexingzhe.MyStepView.R;

/**
 * Created by didi on 2017/5/7.
 */

public class ThirdFragment extends BaseFragment implements BaseFragment.ButtonClickListener {


    @Override
    int getResourceId() {
        return R.layout.third_fragment;
    }

    @Override
    int findButton() {
        return R.id.third_btn;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setButtonClickListener(this);
    }

    @Override
    public void onBtnClick(View v) {
        show(new FirstFragment());
    }
}

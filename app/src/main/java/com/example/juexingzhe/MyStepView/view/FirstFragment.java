package com.example.juexingzhe.MyStepView.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.didi.MyStepView.R;


/**
 * Created by didi on 2017/5/7.
 */

public class FirstFragment extends BaseFragment implements BaseFragment.ButtonClickListener{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setButtonClickListener(this);
    }


    @Override
    int getResourceId() {
        return R.layout.first_fragment;
    }

    @Override
    int findButton() {
        return R.id.first_btn;
    }


    @Override
    public void onBtnClick(View v) {
        show(new SecondFragment());
    }
}

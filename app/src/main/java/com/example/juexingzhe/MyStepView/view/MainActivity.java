package com.example.juexingzhe.MyStepView.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.juexingzhe.MyStepView.R;
import com.example.juexingzhe.MyStepView.contract.StepViewContract;
import com.example.juexingzhe.MyStepView.presenter.StepViewPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity implements StepViewContract.StepCompleteView {

    private StepView mStepView;

    private StepViewContract.StepPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPresenter(new StepViewPresenter(this));
        initview();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void initview() {
        mStepView = (StepView) findViewById(R.id.stepview);

        final FirstFragment firstFragment = new FirstFragment();
        firstFragment.setButtonClickListener(firstFragment);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_content, firstFragment).commit();

    }

    private void initData() {
        mPresenter.initData(null, null, null);  //初始化StepView
    }


    @Override
    public void setPresenter(StepViewContract.StepPresenter stepPresenter) {
        mPresenter = stepPresenter;
    }


    public StepView getStepView() {
        return mStepView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRrefreshData(String refreshData){
        if (refreshData == BaseFragment.REFRESH_STEPVIEW){
            mPresenter.refreshData();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

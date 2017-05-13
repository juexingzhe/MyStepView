package com.example.juexingzhe.MyStepView.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.juexingzhe.MyStepView.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by didi on 2017/5/7.
 */

public abstract class BaseFragment extends Fragment {

    public static final String REFRESH_STEPVIEW = "refresh_stepview";

    private Button mButton;
    private ButtonClickListener mButtonClickListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getResourceId(), container, false);
        mButton = (Button) view.findViewById(findButton());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mButtonClickListener) {
                    mButtonClickListener.onBtnClick(v);
                }
                EventBus.getDefault().post(REFRESH_STEPVIEW);
            }
        });
        return view;
    }

    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        mButtonClickListener = buttonClickListener;
    }

    abstract int getResourceId();

    abstract int findButton();

    public interface ButtonClickListener {
        void onBtnClick(View v);
    }


    protected void show(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragment).commit();
    }


}

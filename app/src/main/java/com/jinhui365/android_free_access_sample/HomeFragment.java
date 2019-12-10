package com.jinhui365.android_free_access_sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinhui365.core.JHWebViewManager;

import java.util.Map;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private TextView tvLoginStatus;

    public static HomeFragment getInstance() {
        HomeFragment jhWebFragment = new HomeFragment();
        return jhWebFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragemnt_home, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        tvLoginStatus = rootView.findViewById(R.id.tv_login_status);
        rootView.findViewById(R.id.btn1).setOnClickListener(this);
        rootView.findViewById(R.id.btn2).setOnClickListener(this);
        rootView.findViewById(R.id.btn3).setOnClickListener(this);
        rootView.findViewById(R.id.btn4).setOnClickListener(this);
        rootView.findViewById(R.id.btn5).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                JHWebViewManager.getInstance().push(getContext(),"/channel/product/huoqi");
                break;
            case R.id.btn2:
                JHWebViewManager.getInstance().push(getContext(),"/channel/product/wenjian");
                break;
            case R.id.btn3:
                JHWebViewManager.getInstance().push(getContext(),"/channel/product/ziguansimu");
                break;
            case R.id.btn4:
                JHWebViewManager.getInstance().push(getContext(),"/product/mutual");
                break;
            case R.id.btn5:
                JHWebViewManager.getInstance().push(getContext(),"/channel/product/gujiao");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setStatus();
    }

    private void setStatus() {
        Map<String, Object> params = Util.getUserInfo(getContext());
        if (null == params || params.isEmpty()) {
            tvLoginStatus.setText("登录状态：未登录");
        } else {
            tvLoginStatus.setText("登录状态：已登录");
        }
    }
}


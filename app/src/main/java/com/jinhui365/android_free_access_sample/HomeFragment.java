package com.jinhui365.android_free_access_sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

public class HomeFragment extends Fragment {

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


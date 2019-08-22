package com.jinhui365.android_free_access_sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jinhui365.core.JHWebViewManager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    private EditText etView;
    private Button btnFragment;
    private Button btnActivity;
    private Button btnLogout;
    private Button btnLogin;
    private TextView tvLoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etView = findViewById(R.id.ed_webview);
        btnFragment = findViewById(R.id.btn_fragment);
        btnActivity = findViewById(R.id.btn_activity);
        btnLogout = findViewById(R.id.btn_logout);
        btnLogin = findViewById(R.id.btn_login);
        tvLoginStatus = findViewById(R.id.tv_login_status);


        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etView.getText().toString().trim();
                JinhuiWebviewDemoActivity.startActivity(MainActivity.this, url, null);
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = etView.getText().toString().trim();
                JHWebViewManager.getInstance().push(MainActivity.this, url, null);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.clearUserInfo(MainActivity.this);
                JHWebViewManager.getInstance().logout();
                setStatus();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginModuleActivity.startActivity(MainActivity.this, 2);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setStatus();
    }

    private void setStatus() {
        Map<String, Object> params = Util.getUserInfo(this);
        if (null == params || params.isEmpty()) {
            tvLoginStatus.setText("登录状态：未登录");
        } else {
            tvLoginStatus.setText("登录状态：已登录");
        }
    }
}

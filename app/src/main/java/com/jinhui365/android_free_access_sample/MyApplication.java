package com.jinhui365.android_free_access_sample;

import android.app.Application;

import com.jinhui365.core.JHWebViewConnectJSListener;
import com.jinhui365.core.JHWebViewManager;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, Object> params = new HashMap<>();
        params.put("themeColor", "#DC143C");
        JHWebViewManager.getInstance()
                .setAppKeySecret("ed_website_aaf3327", "7aafd1481030ce5c88750cc90ed7a1b3")
                .setEnvironmentModel(false)
                .setParams(params)
                .setJHWebViewListener(new JHWebViewConnectJSListener() {
                    @Override
                    public void onConnectListener(String type, Map<String, Object> options) {
                        switch (type) {
                            case "login":
                                //调用登录页面
                                LoginModuleActivity.startActivity(getBaseContext(),1);
                                break;
                            case "risk":
                                //调用风险测评页面
                                RiskModuleActivity.startActivity(getBaseContext());
                                break;
                        }
                    }
                })
                .init(this);

        //如果是已登录用户
        loginInfo();

    }

    private void loginInfo() {
        Map<String,Object> thirdInfo = Util.getUserInfo(getBaseContext());
        JHWebViewManager.getInstance().login(thirdInfo);
    }
}

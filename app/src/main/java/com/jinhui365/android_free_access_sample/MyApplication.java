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
        params.put("key1", "value1");
        params.put("key2", "夜轻轻");
        JHWebViewManager.getInstance()
                .setAppKeySecret("sdk_website_176b34", "44cd6b3401ecb470e1f586ec6a67c24b")
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

        // 开头	130102199003075293	11120190811 10011207
        Map<String, Object> thirdInfo = new HashMap<>();
        thirdInfo.put("name", "开头");//姓名
        thirdInfo.put("idNo", "130102199003075293");//身份证号
        thirdInfo.put("mobile", "11120190811");//手机号
        thirdInfo.put("bankAccount", "10011207");//华创资金账户
        JHWebViewManager.getInstance().login(thirdInfo);
    }
}

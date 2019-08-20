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
                .setAppKeySecret("jh2adf1307ec1830bc", "45addb98955e4359a9d7a695979fb27c")
                .setEnvironmentModel(false)
                .setParams(params)
                .setJHWebViewListener(new JHWebViewConnectJSListener() {
                    @Override
                    public void onConnectListener(String type, Map<String, Object> options) {
                        switch (type) {
                            case "login":
                                //调用登录页面
                                LoginModuleActivity.startActivity(getBaseContext());
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
//        loginInfo();

    }

    private void loginInfo() {
        Map<String, Object> thirdInfo = new HashMap<>();
        thirdInfo.put("name", "卫子夫");//姓名
        thirdInfo.put("idNo", "110000197603217303");//身份证号
        thirdInfo.put("mobile", "18515279796");//手机号
        thirdInfo.put("bankAccount", "10004695");//华创资金账户
        JHWebViewManager.getInstance().login(thirdInfo);
    }
}

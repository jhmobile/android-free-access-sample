package com.jinhui.webview.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.jinhui365.core.JHWebViewManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jmtian
 * @desc 模拟登录
 */
public class LoginModuleActivity extends FragmentActivity implements View.OnClickListener {
    private Button btnFail;
    private Button btnSuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_module);
        btnFail = findViewById(R.id.btn_fail);
        btnSuccess = findViewById(R.id.btn_success);

        btnFail.setOnClickListener(this);
        btnSuccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fail:

                break;
            case R.id.btn_success:
                Map<String, Object> thirdInfo = new HashMap<>();
                thirdInfo.put("name", "卫子夫");//姓名
                thirdInfo.put("idNo", "110000197603217303");//身份证号
                thirdInfo.put("mobile", "18515279796");//手机号
                thirdInfo.put("bankAccount", "10004695");//华创资金账户
                JHWebViewManager.getInstance().login(thirdInfo);
                break;
        }
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context,LoginModuleActivity.class);
        context.startActivity(intent);
    }
}

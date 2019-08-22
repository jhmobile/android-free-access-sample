package com.jinhui365.android_free_access_sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jinhui365.core.JHWebView;
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
    private EditText etName, etIdNo, etMobile, etAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_module);
        btnFail = findViewById(R.id.btn_fail);
        btnSuccess = findViewById(R.id.btn_success);
        etName = findViewById(R.id.et_name);
        etIdNo = findViewById(R.id.et_idNo);
        etMobile = findViewById(R.id.et_mobile);
        etAccount = findViewById(R.id.et_account);

        btnFail.setOnClickListener(this);
        btnSuccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fail:
                finish();
                break;
            case R.id.btn_success:
                loginSuccess();
                break;
        }
    }

    private void loginSuccess() {
        String name = etName.getText().toString().trim();
        String idNo = etIdNo.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String account = etAccount.getText().toString().trim();

        Map<String, Object> thirdInfo = new HashMap<>();
        thirdInfo.put("name", name);//姓名
        thirdInfo.put("idNo", idNo);//身份证号
        thirdInfo.put("mobile", mobile);//手机号
        thirdInfo.put("bankAccount", account);//华创资金账户
        Util.saveUserInfo(this, thirdInfo);
        JHWebViewManager.getInstance().login(thirdInfo);
        finish();
    }


    public static void startActivity(Context context, int fromFlag) {
        Intent intent = new Intent(context, LoginModuleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fromFlag", fromFlag);
        context.startActivity(intent);
    }
}

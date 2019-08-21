package com.jinhui365.android_free_access_sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.jinhui365.core.JHWebViewManager;

/**
 * @author jmtian
 * @desc 模拟风险测评
 */
public class RiskModuleActivity extends FragmentActivity implements View.OnClickListener {
    private Button btnFail;
    private Button btnSuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_module);

        btnFail = findViewById(R.id.btn_fail);
        btnSuccess = findViewById(R.id.btn_success);

        btnFail.setOnClickListener(this);
        btnSuccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fail://风险测评失败
                JHWebViewManager.getInstance().connectResult(-1, "fail", "risk", null);
                finish();
                break;
            case R.id.btn_success://风险测评成功
                JHWebViewManager.getInstance().connectResult(0, "success", "risk", null);
                finish();
                break;
        }
        finish();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RiskModuleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

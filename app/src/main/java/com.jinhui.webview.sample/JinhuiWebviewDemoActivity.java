package com.jinhui.webview.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhui365.core.JHWebFragment;

import java.util.HashMap;


public class JinhuiWebviewDemoActivity extends FragmentActivity {
    private JHWebFragment jhWebFragment;
    private TextView textView;

    private String loadUrl;
    private HashMap<String,Object> params;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        Intent intent = getIntent();
        if (intent.hasExtra("url")) {
            loadUrl = intent.getStringExtra("url");
        }
        if (intent.hasExtra("params")) {
            params = (HashMap<String, Object>) intent.getSerializableExtra("params");
        }


        textView = findViewById(R.id.tv_tab);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JinhuiWebviewDemoActivity.this,"模拟Tab导航",Toast.LENGTH_SHORT).show();
            }
        });

        addJhFragment();

    }

    private void addJhFragment() {
        jhWebFragment = JHWebFragment.getInstance(loadUrl,params);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content,jhWebFragment)
                .commit();
    }

    public static void startActivity(Context context, String url, HashMap<String, Object> params) {
        Intent intent = new Intent(context, JinhuiWebviewDemoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("params", params);
        context.startActivity(intent);
    }
}

package com.jinhui.webview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jinhui.webview.sample.R;
import com.jinhui365.util.webview.JHWebViewManager;

public class MainActivity extends AppCompatActivity {
    private EditText etView;
    private Button btnFragment;
    private Button btnActivity;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etView = findViewById(R.id.ed_webview);
        btnFragment = findViewById(R.id.btn_fragment);
        btnActivity = findViewById(R.id.btn_activity);
        btnLogout = findViewById(R.id.btn_logout);

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
                Log.e("XXXXXX","        btnActivity.setOnClickListener(new View.OnClickListener() {\n");
                String url = etView.getText().toString().trim();
                JHWebViewManager.getInstance().push(MainActivity.this, url, null);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JHWebViewManager.getInstance().logout();
            }
        });
    }
}

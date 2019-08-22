package com.jinhui365.android_free_access_sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jinhui365.core.JHWebFragment;

import java.util.HashMap;


public class JinhuiWebviewDemoActivity extends FragmentActivity {
    private JHWebFragment jhWebFragment;
    private HomeFragment homeFragment;
    private TextView tab1, tab2;

    private String loadUrl;
    private HashMap<String, Object> params;

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


        tab1 = findViewById(R.id.tv_tab1);
        tab2 = findViewById(R.id.tv_tab2);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setBackgroundColor(getResources().getColor(R.color.C6));
                tab2.setBackgroundColor(getResources().getColor(R.color.C11));
                switchFragment(homeFragment).commit();
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setBackgroundColor(getResources().getColor(R.color.C11));
                tab2.setBackgroundColor(getResources().getColor(R.color.C6));
                switchFragment(jhWebFragment).commit();
                jhWebFragment.refresh();
            }
        });
        addJhFragment();
    }

    private void addJhFragment() {
        jhWebFragment = JHWebFragment.getInstance(loadUrl, params);
        homeFragment = HomeFragment.getInstance();
        tab1.setBackgroundColor(getResources().getColor(R.color.C6));
        tab2.setBackgroundColor(getResources().getColor(R.color.C11));
        switchFragment(homeFragment).commit();
    }

    private Fragment currentFragment;

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fl_content, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }


    public static void startActivity(Context context, String
            url, HashMap<String, Object> params) {
        Intent intent = new Intent(context, JinhuiWebviewDemoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("params", params);
        context.startActivity(intent);
    }
}

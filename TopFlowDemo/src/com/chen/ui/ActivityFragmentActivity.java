package com.chen.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.chen.R;
import com.chen.util.activity.AppManager;
import com.chen.util.activity.BasicActivity;

public class ActivityFragmentActivity extends BasicActivity implements
        OnClickListener {

    private Button btn_open, btn_killtop, btn_killall, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        
        initView();
        initEvent();
    }

    @Override
    protected void initView() {
        super.initView();
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_killtop = (Button) findViewById(R.id.btn_killtop);
        btn_killall = (Button) findViewById(R.id.btn_killall);
        btn_exit = (Button) findViewById(R.id.btn_exit);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btn_open.setOnClickListener(this);
        btn_killtop.setOnClickListener(this);
        btn_killall.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.btn_open:
            openActivity(ActivityFragmentActivity.class);
            break;
        case R.id.btn_killtop:
            AppManager.getInstance().killTopActivity();
            break;
        case R.id.btn_killall:
            AppManager.getInstance().killAllActivity();
            break;
        case R.id.btn_exit:
            AppManager.getInstance().AppExit(this);
            break;
        }
    }

}

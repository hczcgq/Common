package com.chen.ui;

import com.chen.R;
import com.chen.view.wheel.OnWheelChangedListener;
import com.chen.view.wheel.WheelView;
import com.chen.view.wheel.adapter.ArrayWheelAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class WheelDialogAvtivity extends Activity {
    private WheelView mWheelView;

    private Button btn_done;

    private String[] numbers = { "男", "女" };

    @SuppressWarnings({ "deprecation", "static-access" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_popwindow_wheel);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 设置窗口的大小及透明度
        layoutParams.width = LayoutParams.FILL_PARENT;
        layoutParams.height = layoutParams.FILL_PARENT;
        window.setAttributes(layoutParams);

        btn_done = (Button) findViewById(R.id.btn_done);
        mWheelView = (WheelView) findViewById(R.id.id_province);
        mWheelView.setViewAdapter(new ArrayWheelAdapter<String>(
                WheelDialogAvtivity.this, numbers));
        // 设置可见条目数量
        mWheelView.setVisibleItems(7);
        
       mWheelView.addChangingListener(new OnWheelChangedListener() {
        
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            Toast.makeText(WheelDialogAvtivity.this,
                    "当前选中:" + numbers[wheel.getCurrentItem()],
                    Toast.LENGTH_SHORT).show();
        }
    });

        btn_done.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onfinish();
                Toast.makeText(WheelDialogAvtivity.this,
                        "当前选中:" + numbers[mWheelView.getCurrentItem()],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int height = findViewById(R.id.pop_layout).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (y < height) {
                onfinish();
            }
        }
        return true;
    }

    private void onfinish() {
        finish();
        overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
    }

}

package com.chen.ui;

import com.chen.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;

public class PopupWindowDialogAvtivity extends Activity {

    private Button btn_one, btn_two, btn_three, btn_cancel;

    @SuppressWarnings({ "deprecation", "static-access" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_popwindow_upload_image);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 设置窗口的大小及透明度
        layoutParams.width = LayoutParams.FILL_PARENT;
        layoutParams.height = layoutParams.FILL_PARENT;
        window.setAttributes(layoutParams);

        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_one.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onfinish();
            }
        });
        btn_two.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onfinish();
            }
        });
        btn_three.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onfinish();
            }
        });
        btn_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onfinish();
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

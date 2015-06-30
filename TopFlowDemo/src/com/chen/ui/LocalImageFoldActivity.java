package com.chen.ui;
import java.util.List;

import com.chen.R;
import com.chen.adapter.LocalImageFoleAdapter;
import com.chen.util.image.ImageFloder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LocalImageFoldActivity extends Activity{
    private ListView mListDir;

    private List<ImageFloder> mImageFloders;

    @SuppressWarnings({ "unchecked", "deprecation", "static-access" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_localimage_list_dir);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 设置窗口的大小及透明度
        layoutParams.width = LayoutParams.FILL_PARENT;
        layoutParams.height = layoutParams.FILL_PARENT;
        window.setAttributes(layoutParams);

        mImageFloders = (List<ImageFloder>) getIntent().getExtras()
                .getSerializable("foles");

        mListDir = (ListView) findViewById(R.id.id_list_dir);
        if (mImageFloders != null && mImageFloders.size() > 0) {
            mListDir.setAdapter(new LocalImageFoleAdapter(this, mImageFloders));
        }
        mListDir.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Intent intent = new Intent();
                intent.putExtra("imagefole", mImageFloders.get(arg2));
                setResult(22, intent);
                onfinish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int height = mListDir.getTop();
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

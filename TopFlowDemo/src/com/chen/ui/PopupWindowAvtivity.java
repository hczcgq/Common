package com.chen.ui;

import java.util.ArrayList;
import java.util.List;

import com.chen.R;
import com.chen.adapter.PopWindowAdapter;
import com.chen.view.MenuPopWindow;
import com.chen.view.MyGridView;
import com.chen.view.UploadPopWindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PopupWindowAvtivity extends Activity {

    private MyGridView mGridView;

    private UploadPopWindow mUploadPopWindow;

    private MenuPopWindow mMenuPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_popwindow);

        mGridView = (MyGridView) findViewById(R.id.gridview);

        List<String> items = fillList();
        mGridView.setAdapter(new PopWindowAdapter(this, items));

        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
                if (position == 0) {
                    imitationQQUploadImage();
                } else if (position == 1) {
                    Intent intent = new Intent(PopupWindowAvtivity.this,
                            PopupWindowDialogAvtivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_bottom_in,
                            R.anim.push_bottom_out);
                } else if (position == 2) {
                    rightMenu(arg1);
                } else if (position == 3) {
                    Intent intent = new Intent(PopupWindowAvtivity.this,
                            WheelDialogAvtivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_bottom_in,
                            R.anim.push_bottom_out);
                }
            }
        });
    }

    private List<String> fillList() {
        List<String> items = new ArrayList<String>();
        items.add("Popwindow仿QQ上传图片");
        items.add("Dialog仿QQ上传图片");
        items.add("Popwindow右上角菜单");
        items.add("底部滚轮");
        return items;
    }

    /**
     * 仿QQ上传图片
     */
    private void imitationQQUploadImage() {
        mUploadPopWindow = new UploadPopWindow(this, uploadImageItemsOnClick,
                "Take Photo", "Choose Photo", null);
        mUploadPopWindow.showAtLocation(this.findViewById(R.id.main),
                Gravity.BOTTOM, 0, 0);
    }

    private OnClickListener uploadImageItemsOnClick = new OnClickListener() {

        public void onClick(View v) {
            mUploadPopWindow.dismiss();
            switch (v.getId()) {
            case R.id.btn_two: // 相册
                Toast.makeText(PopupWindowAvtivity.this, "相册", 0).show();
                break;
            case R.id.btn_one: // 拍照
                Toast.makeText(PopupWindowAvtivity.this, "拍照", 0).show();
                break;
            default:
                break;
            }
        }
    };

    protected void rightMenu(View arg1) {
        List<String> items = new ArrayList<String>();
        items.add("扫一扫");
        items.add("加好友");
        items.add("建讨论组");
        items.add("我的电脑");
        items.add("面对面快传");
        mMenuPopWindow = new MenuPopWindow(this, arg1, items, menuItemOnClick);
        // mMenuPopWindow.showAtLocation(this.findViewById(R.id.main),
        // Gravity.BOTTOM, 0, 0);
    }

    private OnItemClickListener menuItemOnClick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            mMenuPopWindow.dismiss();

        }
    };

}

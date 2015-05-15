package com.chen.view;

import com.chen.R;
import com.chen.util.StringUtils;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 仿QQ上传图片
 * 
 * @author chenguoquan
 *
 */
public class UploadPopWindow extends PopupWindow {
    private Button btn_one, btn_two, btn_three, btn_cancel;

    private View mMenuView;

    private TextView three_line;

    @SuppressWarnings("deprecation")
    public UploadPopWindow(Activity context, OnClickListener itemsOnClick,
            String oneText, String twoText, String threeText) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater
                .inflate(R.layout.view_popwindow_upload_image, null);
        btn_one = (Button) mMenuView.findViewById(R.id.btn_one);
        btn_two = (Button) mMenuView.findViewById(R.id.btn_two);
        btn_three = (Button) mMenuView.findViewById(R.id.btn_three);
        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
        three_line = (TextView) mMenuView.findViewById(R.id.three_line);

        if (!StringUtils.isEmpty(oneText)) {
            btn_one.setText(oneText.toString());
        }

        if (!StringUtils.isEmpty(twoText)) {
            btn_two.setText(twoText.toString());
        }

        if (!StringUtils.isEmpty(threeText)) {
            btn_three.setText(threeText.toString());
            three_line.setVisibility(View.VISIBLE);
        } else {
            btn_three.setVisibility(View.GONE);
            three_line.setVisibility(View.GONE);
        }
        // 取消按钮
        btn_cancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置按钮监听
        btn_one.setOnClickListener(itemsOnClick);
        btn_two.setOnClickListener(itemsOnClick);
        btn_three.setOnClickListener(itemsOnClick);
        btn_cancel.setOnClickListener(itemsOnClick);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                        if (listener != null) {
                            listener.onTouchListen();
                        }
                    }
                }
                return true;
            }
        });

    }

    private PopListener listener;

    public void setOnToucheListen(PopListener listener) {
        this.listener = listener;
    }

    public interface PopListener {
        public void onTouchListen();
    }
}
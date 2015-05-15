package com.chen.view;

import java.util.List;
import com.chen.R;
import com.chen.adapter.MenuPopWindowAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;

/**
 * popwindow右上角菜单
 * 
 * @author chenguoquan
 *
 */
public class MenuPopWindow extends PopupWindow {

    private View mMenuView;

    private ListView mListView;;

    public MenuPopWindow(Activity context, View parent,List<String> datas,
            OnItemClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.view_popwindow_menu, null);
        mListView = (ListView) mMenuView.findViewById(R.id.list);
        mListView.setOnItemClickListener(itemsOnClick);
        mListView.setAdapter(new MenuPopWindowAdapter(context, datas));
        // 设置的View
        this.setContentView(mMenuView);
        // 设置弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.showAsDropDown(parent);
        // 设置允许在外点击消失
        this.setOutsideTouchable(true);
    }
}
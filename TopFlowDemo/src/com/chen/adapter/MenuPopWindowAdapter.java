package com.chen.adapter;

import java.util.List;
import com.chen.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MenuPopWindowAdapter extends BaseAdapterHelpter<String>{

    private Context context;
    private List<String> datas;
    public MenuPopWindowAdapter(Context context, List<String> datas) {
        super(context, datas);
        this.context=context;
        this.datas=datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderHelper holder=ViewHolderHelper.get(context, convertView, parent, R.layout.view_popwindow_menu_item, position);
        ((TextView)holder.getView(R.id.name)).setText(datas.get(position));
        return holder.getConvertView();
    }

}

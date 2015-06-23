package com.chen.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.chen.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListViewCheckedAdapter extends BaseAdapter{
    @SuppressWarnings("unused")
    private Context context;

    private String[] items;

    private LayoutInflater inflater;
    
    List<Boolean> mChecked;
    
    public HashMap<Integer,String> map;
    

    public ListViewCheckedAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        
        map=new HashMap<Integer, String>();
        mChecked = new ArrayList<Boolean>();  
        for(int i=0;i<items.length;i++){  
            mChecked.add(false);  
        }  
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.view_listview_select_item, null);
            holder.select=(CheckBox) convertView.findViewById(R.id.select);
            holder.textView=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.textView.setText(items[position]);
        
        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                mChecked.set(position, cb.isChecked());
                if(cb.isChecked()) {
                    map.put(position, items[position]);
                }else {
                    map.remove(position);
                }
            }
        });
        holder.select.setChecked(mChecked.get(position));
        
        return convertView;
    }

    public class ViewHolder {
        CheckBox select;
        TextView textView;
    }
}

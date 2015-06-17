package com.chen;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VoiceAdapter extends BaseAdapter{
    private ArrayList<String> voiceArrayList;
    private LayoutInflater inflater;
    public VoiceAdapter(Context context,
            ArrayList<String> voiceArrayList) {
       this.voiceArrayList=voiceArrayList;
       inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return voiceArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return voiceArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView=inflater.inflate(R.layout.view_voice_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.name.setText(voiceArrayList.get(position));
        
        return convertView;
    }
    
    class ViewHolder{
        TextView name;
    }

}

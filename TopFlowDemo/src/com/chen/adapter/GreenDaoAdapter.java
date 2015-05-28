package com.chen.adapter;

import java.util.List;

import com.chen.R;
import com.chen.dao.Person;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GreenDaoAdapter extends BaseAdapterHelpter<Person> {

    private Context context;

    private List<Person> datas;

    public GreenDaoAdapter(Context context, List<Person> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderHelper holder = ViewHolderHelper.get(context, convertView,
                parent, R.layout.view_greendao_item, position);
        Person person = datas.get(position);
        ((TextView) holder.getView(R.id.name)).setText(person.getName());
        ((TextView) holder.getView(R.id.mobile)).setText(person.getMobile());
        return holder.getConvertView();
    }
}

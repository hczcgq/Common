package com.chen.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.chen.R;
import com.chen.dao.Person;

public class FilterSearchAdapter extends BaseAdapterHelpter<Person> implements
        Filterable {
    private List<Person> datas;

    private Context context;

    private PersonFilter filter;

    public FilterSearchAdapter(Context context, List<Person> datas) {
        super(context, datas);
        this.datas = datas;
        this.context = context;
    }
    
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderHelper holder = ViewHolderHelper.get(context, convertView,
                parent, R.layout.view_greendao_item, position);
        System.out.println(position);
        Person person = datas.get(position);
        ((TextView) holder.getView(R.id.name)).setText(person.getName());
        return holder.getConvertView();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new PersonFilter(datas);
        }
        return filter;
    }

    private class PersonFilter extends Filter {

        private List<Person> original;

        public PersonFilter(List<Person> list) {
            this.original = list;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            System.out.println("constraint:" + constraint);
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = original;
                results.count = original.size();
            } else {
                List<Person> mList = new ArrayList<Person>();
                for (Person p : original) {
                    if (p.getName().contains(constraint.toString())) {
                        mList.add(p);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                FilterResults results) {
            datas = (List<Person>) results.values;
            System.out.println(datas.size());
            for(int i=0;i<datas.size();i++) {
                System.out.println(datas.get(i).getName());
            }
            notifyDataSetChanged();
        }

    }

}

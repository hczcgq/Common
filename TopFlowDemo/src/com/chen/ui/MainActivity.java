package com.chen.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> items = fillList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position == 0) {
            Intent intent = new Intent(this, PopupWindowAvtivity.class);
            startActivity(intent);
        }
    }

    private List<String> fillList() {
        List<String> items = new ArrayList<String>();
        items.add("PopupWindow各种方式");
        items.add("星期二");
        items.add("星期三");
        items.add("星期四");
        items.add("星期五");
        items.add("星期六");
        items.add("星期日");
        return items;
    }
}

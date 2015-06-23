package com.chen.ui;

import java.util.HashMap;
import java.util.Map;

import com.chen.R;
import com.chen.adapter.ListViewCheckedAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListViewMultiSelectActivity extends Activity {

    private ListView mListView;
    
    private ListViewCheckedAdapter adapter;
    
    private String[] items= {"chenguoquan","chenguoquan","chenguoquan","chenguoquan","chenguoquan","chenguoquan","chenguoquan"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_listview_select);
        
        mListView =(ListView) findViewById(R.id.listview);
        
        adapter=new ListViewCheckedAdapter(this, items);
        mListView.setAdapter(adapter );
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        HashMap<Integer,String> map=adapter.map;
        StringBuilder string_describe = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            string_describe.append(entry.getValue() + ",");
        }
        System.out.println(string_describe.toString());
    }
}

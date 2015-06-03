package com.chen.ui;

import java.util.ArrayList;
import java.util.List;
import com.chen.R;
import com.chen.adapter.FilterSearchAdapter;
import com.chen.dao.Person;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

public class FilterSearchActivity extends Activity {

    private FilterSearchAdapter listAdapter;

    private List<Person> array = new ArrayList<Person>();

    private ListView listView;

    private EditText search_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_filter_search);

        listView = (ListView) findViewById(R.id.list);
        search_edit = (EditText) findViewById(R.id.searchbox);
        initData();

    }

    private void initData() {

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            if (i == 0) {
                person.setName("chenguoquan");
            } else if (i == 1) {
                person.setName("chensiwei");
            } else if (i == 2) {
                person.setName("leishuimiao");
            } else if (i == 3) {
                person.setName("fengsong");
            } else if (i == 4) {
                person.setName("kongyi");
            } else {
                person.setName("huiming");
            }
            array.add(person);
        }

        listAdapter = new FilterSearchAdapter(this, array);
        listView.setAdapter(listAdapter);
        search_edit.addTextChangedListener(filterTextWatcher);
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                int count) {
            listAdapter.getFilter().filter(s); // 这里传入数据就可以了
        }

    };
}

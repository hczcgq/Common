package com.chen.ui;

import java.util.ArrayList;
import java.util.List;

import com.chen.R;
import com.chen.adapter.GreenDaoAdapter;
import com.chen.dao.Person;
import com.chen.db.PersonDbService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class GreenDaoActivity extends Activity {
    private PersonDbService instance;

    private List<Person> personList;

    private ListView mListView;

    private GreenDaoAdapter adapter;

    public static final int REQUEST_ADD = 1;

    public static final int INTENT_ADD = 1;

    public static final int INTENT_UPDATE = 2;

    Handler handle = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (personList != null) {
                refreshAdapter();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_greendao_main);
        instance = PersonDbService.getInstance(this);
        personList = new ArrayList<Person>();

        mListView = (ListView) findViewById(R.id.list);
        

        new Thread(new Runnable() {

            @Override
            public void run() {
                fenye(0, 2);
            }
        }).start();

        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Intent intent = new Intent(GreenDaoActivity.this, GreenDaoAddActivity.class);
                intent.putExtra("type", INTENT_UPDATE);
                intent.putExtra("id", personList.get(arg2).getId());
                startActivityForResult(intent, REQUEST_ADD);
            }
        });

        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    final int arg2, long arg3) {
                AlertDialog.Builder builder = new Builder(GreenDaoActivity.this);
                builder.setMessage("Comfirm to delete?");

                builder.setTitle("Notice");

                builder.setPositiveButton("Yes", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        instance.deleteBykey(personList.get(arg2).getId());
                        personList.remove(arg2);
                        refreshAdapter();
                    }
                });

                builder.setNegativeButton("No", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
                return true;
            }
        });
    }
    
    
    public void fenye(int offset,int pagesize) {
        String sql = "limit "+offset+","+(offset+pagesize);
        System.out.println(sql);
        List<Person> persons=instance.queryWithParem(sql);
        personList.addAll(persons);
        handle.sendEmptyMessage(100);
    }

    public void add(View view) {
        Intent intent = new Intent(this, GreenDaoAddActivity.class);
        intent.putExtra("type", INTENT_ADD);
        startActivityForResult(intent, REQUEST_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == REQUEST_ADD) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    personList.clear();
                    personList.addAll(instance.loadAll());
                    handle.sendEmptyMessage(100);
                }
            }).start();
        }
    }

    protected void refreshAdapter() {
        if (adapter == null) {
            adapter = new GreenDaoAdapter(this, personList);
            mListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}

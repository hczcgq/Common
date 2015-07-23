package com.chen.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import com.chen.R;
import com.chen.view.MySwipeListView;
import com.chico.pulltorefresh.library.PullToRefreshLayout;
import com.chico.pulltorefresh.library.PullToRefreshLayout.OnRefreshListener;
import com.fortysevendeg.android.swipelistview.BaseSwipeListViewListener;

public class ListViewSwipeActivity extends Activity {
    private MySwipeListView mListView;

    private PullToRefreshLayout mPullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_swipe);

        mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_refresh_layout);
        mPullToRefreshLayout
        .setRefreshMode(PullToRefreshLayout.PULL_DOWN);
        mPullToRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                mPullToRefreshLayout
                        .loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
        mListView = (MySwipeListView) findViewById(R.id.list);

        // final SwipeListView swipeListView = (SwipeListView)
        // findViewById(R.id.list);
        final List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 100; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", "List Item No:" + i + 100);
            map.put("remove", i);
            dataList.add(map);
        }

        final SimpleAdapter adapter = new SimpleAdapter(this, dataList,
                R.layout.view_swipe_item, new String[] { "text", "remove" },
                new int[] { R.id.text, R.id.remove });
        adapter.setViewBinder(new ViewBinder() {
            @Override
            public boolean setViewValue(View view, final Object data,
                    String textRepresentation) {
                switch (view.getId()) {
                case R.id.remove:
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String, Object> removeItem = null;
                            for (Map<String, Object> map : dataList) {
                                if (map.get("remove").equals(data)) {
                                    removeItem = map;
                                    break;
                                }
                            }
                            dataList.remove(removeItem);
                            adapter.notifyDataSetChanged();
                            // 关闭SwipeListView
                            mListView.closeOpenedItems();
                        }
                    });
                    break;
                case R.id.text:
                    ((TextView) view).setText((String) data);
                    break;
                }
                return true;
            }
        });
        mListView.setAdapter(adapter);

        mListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onChoiceChanged(int position, boolean selected) {
                System.out.println("onChoiceChanged:" + position + ", "
                        + selected);
            }

            @Override
            public void onChoiceEnded() {
                System.out.println("onChoiceEnded");
            }

            @Override
            public void onChoiceStarted() {
                System.out.println("onChoiceStarted");
            }

            @Override
            public void onClickBackView(int position) {
                System.out.println("onClickBackView:" + position);
            }

            @Override
            public void onClickFrontView(int position) {
                System.out.println("onClickFrontView:" + position);
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
                System.out.println("onClosed:" + position + "," + fromRight);
            }

            @Override
            public void onDismiss(int[] arg0) {
                System.out.println("onDismiss");
            }

            @Override
            public void onFirstListItem() {
                System.out.println("onFirstListItem");
            }

            @Override
            public void onLastListItem() {
                System.out.println("onLastListItem");
            }

            @Override
            public void onListChanged() {
                System.out.println("onListChanged");
            }

            @Override
            public void onMove(int position, float x) {
                System.out.println("onMove:" + position + "," + x);
            }

            @Override
            public void onOpened(int position, boolean toRight) {
                System.out.println("onOpened:" + position + "," + toRight);
            }

            @Override
            public void onStartClose(int position, boolean right) {
                System.out.println("onStartClose:" + position + "," + right);
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                System.out.println("onStartOpen:" + position + "," + action
                        + "," + right);
            }
        });
    }
}

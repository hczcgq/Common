package com.chen.view;

import android.content.Context;
import android.util.AttributeSet;

import com.chico.pulltorefresh.library.Pullable;
import com.fortysevendeg.android.swipelistview.SwipeListView;

public class MySwipeListView extends SwipeListView implements Pullable {

    public MySwipeListView(Context context, int swipeBackView,
            int swipeFrontView) {
        super(context, swipeBackView, swipeFrontView);
    }

    public MySwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MySwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canPullDown() {
        if (getCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (getFirstVisiblePosition() == 0
                && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (getLastVisiblePosition() == (getCount() - 1)) {
            // 滑到底部了
            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
                    && getChildAt(
                            getLastVisiblePosition()
                                    - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
                return true;
        }
        return false;
    }

}

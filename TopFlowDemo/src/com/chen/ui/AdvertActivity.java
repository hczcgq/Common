package com.chen.ui;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;
import net.youmi.android.spot.SpotManager;

import com.chen.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class AdvertActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_advert);

    }

    public void youmiAds(View view) {
        SpotManager.getInstance(this).loadSpotAds();
        // 插屏出现动画效果，0:ANIM_NONE为无动画，1:ANIM_SIMPLE为简单动画效果，2:ANIM_ADVANCE为高级动画效果
        SpotManager.getInstance(this)
                .setAnimationType(SpotManager.ANIM_ADVANCE);
        // 设置插屏动画的横竖屏展示方式，如果设置了横屏，则在有广告资源的情况下会是优先使用横屏图。
        SpotManager.getInstance(this).setSpotOrientation(
                SpotManager.ORIENTATION_PORTRAIT);
        // 实例化LayoutParams(重要)
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告条的悬浮位置
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
        // 实例化广告条
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        // 调用Activity的addContentView函数
        // 监听广告条接口
        adView.setAdListener(new YoumiMonitor(this));
        this.addContentView(adView, layoutParams);

    }

    public void shenmiAds(View view) {
        // 实例化LayoutParams(重要)
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告条的悬浮位置
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角

        BannerMonitor monitor = new BannerMonitor(
                "F143B2A1D64642DF82D446C9D5851943", this);
        com.snmi.adsdk.banner.AdView mAdView = new com.snmi.adsdk.banner.AdView(
                "F143B2A1D64642DF82D446C9D5851943", this,
                "389CB0DF0BEE4EF2AC065AA5260BE472", true, true, monitor);
        mAdView.setAdspaceWidth(-1);
        this.addContentView(mAdView, layoutParams);
    }

    @Override
    public void onBackPressed() {
        // 如果有需要，可以点击后退关闭插播广告。
        if (!SpotManager.getInstance(this).disMiss()) {
            // 弹出退出窗口，可以使用自定义退屏弹出和回退动画,参照demo,若不使用动画，传入-1
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        // 如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
        SpotManager.getInstance(this).onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        SpotManager.getInstance(this).onDestroy();
        super.onDestroy();
    }

    class YoumiMonitor implements AdViewListener {

        private Context mContext;

        public YoumiMonitor(Context context) {
            this.mContext = context;
        }

        @Override
        public void onReceivedAd(AdView paramAdView) {
            Toast.makeText(mContext, "请求广告成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSwitchedAd(AdView paramAdView) {
            Toast.makeText(mContext, "广告条切换", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailedToReceivedAd(AdView paramAdView) {
            Toast.makeText(mContext, "请求广告失败", Toast.LENGTH_SHORT).show();
        }

    }

    class BannerMonitor implements com.snmi.adsdk.BannerListener {

        @SuppressWarnings("unused")
        private String loactionID;

        private Context mContext;

        public BannerMonitor(String locationID, Context context) {
            this.loactionID = locationID;
            this.mContext = context;
        }

        @Override
        public void bannerClicked() {
            Toast.makeText(mContext, "banner点击了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void bannerClosed() {
            Toast.makeText(mContext, "banner关闭了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void bannerShown(String json) {
            Toast.makeText(mContext, "banner展示了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void noAdFound() {
            Toast.makeText(mContext, "banner无广告", Toast.LENGTH_SHORT).show();
        }
    }
}

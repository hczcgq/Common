package com.chen.ui;

import com.chen.R;
import com.chen.util.PreferencesUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    
    private ImageView mSplashItem_iv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_splash);

        mSplashItem_iv = (ImageView) findViewById(R.id.splash_loading_item);
        
        
        int current_version = getVersion(this);
        PreferencesUtils.PREFERENCE_NAME = "SP_LOGIN";
        boolean isExistFirst = PreferencesUtils.checkExist(this, "ISFIRST");
        boolean isExistVersion = PreferencesUtils.checkExist(this, "VERSION");
        if (isExistVersion) {
            int version = PreferencesUtils.getInt(this, "VERSION");
            if (current_version > version && isExistFirst) {
                PreferencesUtils.revomeKey(this, "ISFIRST");
            }
        }

        PreferencesUtils.putInt(this, "VERSION", current_version);

        isExistFirst = PreferencesUtils.checkExist(this, "ISFIRST");
        if (!isExistFirst) {

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    
//                }
//            }, 2000);
            loadAmin(SlidePageViewDemoActivity.class);
        } else {

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    loadAmin(SlidePageViewDemoActivity.class);
//                }
//            }, 2000);
            loadAmin(SlidePageViewDemoActivity.class);
        }
    }

    /**
     * 获取当前应用内部的的版本号
     * 
     * @return
     */
    private int getVersion(Context context) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void loadAmin(final Class<?> class1) {
        Animation translate = AnimationUtils.loadAnimation(this,
                R.anim.splash_loading);
        translate.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(SplashActivity.this,class1);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                SplashActivity.this.finish();
            }
        });
        mSplashItem_iv.setAnimation(translate);
    }
}

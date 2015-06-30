package com.chen;


import net.youmi.android.AdManager;

import com.chen.dao.DaoMaster;
import com.chen.dao.DaoMaster.OpenHelper;
import com.chen.dao.DaoSession;
import com.chen.util.ConstantUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;  
    private static DaoMaster daoMaster;  
    private static DaoSession daoSession;  
      
    @Override  
    public void onCreate() {  
        super.onCreate();  
        if(mInstance == null)  
            mInstance = this; 
        com.snmi.adsdk.Ad.initial(this); //申米广告
        AdManager.getInstance(this).init("85aa56a59eac8b3d", "a14006f66f58d5d7", false); //有米广告
        initImageLoader(getApplicationContext());
    }  
    
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() 
                .build();
        ImageLoader.getInstance().init(config);
    }
      
    /** 
     * 取得DaoMaster 
     *  
     * @param context 
     * @return 
     */  
    public static DaoMaster getDaoMaster(Context context) {  
        if (daoMaster == null) {  
            OpenHelper helper = new DaoMaster.DevOpenHelper(context,ConstantUtil.DB_NAME, null);  
            daoMaster = new DaoMaster(helper.getWritableDatabase());  
        }  
        return daoMaster;  
    }  
      
    /** 
     * 取得DaoSession 
     *  
     * @param context 
     * @return 
     */  
    public static DaoSession getDaoSession(Context context) {  
        if (daoSession == null) {  
            if (daoMaster == null) {  
                daoMaster = getDaoMaster(context);  
            }  
            daoSession = daoMaster.newSession();  
        }  
        return daoSession;  
    }  
}

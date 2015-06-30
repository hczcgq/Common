package com.chen.http;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

public interface HttpRequest {

    /**
     * post请求服务
     * 
     * @param context
     * @param url
     * @param json
     * @return
     */
    public String postByHttpClient(Context context, String url, String json);

    /**
     * post上传文件
     * 
     * @param context
     * @param url
     * @param json
     * @return
     */
    public String postFileByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath);
    
    /**
     * post上传Voice文件
     * 
     * @param context
     * @param url
     * @param json
     * @return
     */
    public String postVoiceByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath);

    /**
     * 下载图片
     * 
     * @param context
     * @param url
     * @return
     */
    public Bitmap downloadImageByHttp(Context context, String url);
    
    /**
     * post上传文件带进度条
     */
    public String URLDownloadFile(Context context, String url,
             String filepath,Handler handler);
    
    /**
     * post上传文件带进度条
     */
    public String postFileWithProByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath,Handler handler);
}

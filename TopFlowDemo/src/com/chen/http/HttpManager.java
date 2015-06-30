package com.chen.http;
import java.util.HashMap;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

public class HttpManager implements HttpRequest{
    
    @Override
    public String postByHttpClient(Context context, String url, String json) {
        return HttpFactory.PostFromWebByHttpClient(context, url, json);
    }

    @Override
    public String postFileByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        return HttpFactory.postFileByHttpClient(context, url, map, filepath);
    }

    
    @Override
    public Bitmap downloadImageByHttp(Context context, String url) {
        return HttpFactory.downloadImageByHttp(context, url);
    }

    @Override
    public String postVoiceByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        return HttpFactory.postVoiceByHttpClient(context, url, map, filepath);
    }

    @Override
    public String postFileWithProByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath, Handler handler) {
        return HttpFactory.postFileWithProByHttpClient(context, url, map, filepath, handler);
    }

    @Override
    public String URLDownloadFile(Context context, String url, String filepath,
            Handler handler) {
        return HttpFactory.downloadAttachFile(context,url,filepath,handler);
    }

}

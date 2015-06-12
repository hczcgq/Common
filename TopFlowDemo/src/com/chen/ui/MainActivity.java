package com.chen.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> items = fillList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

        new Thread(new Runnable() {

            @Override
            public void run() {
                getPDAServerData();
            }
        }).start();
        
        System.out.println(getApplicationContext().getDatabasePath("person_db"));
        
        File file=new File(getApplicationContext().getDatabasePath("person_db")+".db");
        System.out.println(file.exists());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position == 0) {
            Intent intent = new Intent(this, MainDialogAvtivity.class);
            startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(this,
                    MainUploadAndDownloadAvtivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(this, GreenDaoActivity.class);
            startActivity(intent);
        }else if(position==3) {
            Intent intent = new Intent(this, FilterSearchActivity.class);
            startActivity(intent);
        }else if(position==4) {
            Intent intent = new Intent(this, AdvertActivity.class);
            startActivity(intent);
        }else if(position==5) {
            Intent intent = new Intent(this,  SplashActivity.class);
            startActivity(intent);
        }else if(position==6) {
            Intent intent = new Intent(this,  LocalImageActivity.class);
            startActivity(intent);
        }else if(position==7) {
            Intent intent = new Intent(this,  ActivityFragmentActivity.class);
            startActivity(intent);
        }else if(position==8) {
            Intent intent = new Intent(this,  AsyncTaskActivity.class);
            startActivity(intent);
        }
    }

    private List<String> fillList() {
        List<String> items = new ArrayList<String>();
        items.add("对话框");
        items.add("上传下载");
        items.add("GreenDAO数据库ORM框架");
        items.add("过滤搜索");
        items.add("广告接入");
        items.add("引导页");
        items.add("本地相册");
        items.add("Activity基类-框架");
        items.add("AsyncTask停止");
        return items;
    }

    private void getPDAServerData() {
        System.out.println("dsdfsad");
        String result = null;
        try {

            // 创建一个HttpClient对象
            HttpClient httpclient = new DefaultHttpClient();
            // 远程登录URL
            String processURL = "http://hm-soft-pc:8080/AndroidStruts2JSON/login?userName=chenguoquan&password=123456";
            // 创建HttpGet对象
            HttpGet request = new HttpGet(processURL);
            // 请求信息类型MIME每种响应类型的输出（普通文本、html 和 XML，json）。允许的响应类型应当匹配资源类中生成的 MIME
            // 类型
            // 资源类生成的 MIME 类型应当匹配一种可接受的 MIME 类型。如果生成的 MIME 类型和可接受的 MIME 类型不
            // 匹配，那么将
            // 生成 com.sun.jersey.api.client.UniformInterfaceException。例如，将可接受的
            // MIME 类型设置为 text/xml，而将
            // 生成的 MIME 类型设置为 application/xml。将生成 UniformInterfaceException。
            request.addHeader("Accept", "text/json");
            // 获取响应的结果
            HttpResponse response = httpclient.execute(request);
            // 获取HttpEntity
            HttpEntity entity = response.getEntity();
            // 获取响应的结果信息
            String json = EntityUtils.toString(entity, "UTF-8");
            System.out.println("json:" + json);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

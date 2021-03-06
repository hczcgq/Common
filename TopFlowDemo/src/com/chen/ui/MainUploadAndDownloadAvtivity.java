package com.chen.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import com.chen.R;
import com.chen.adapter.PopWindowAdapter;
import com.chen.util.ConstantUtil;
import com.chen.util.FileUtil;
import com.chen.util.StringUtils;
import com.chen.view.MyGridView;
import com.chen.view.ProgressWheel;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("deprecation")
public class MainUploadAndDownloadAvtivity extends Activity {
    private MyGridView mGridView;

    private ProgressWheel progressWheel;

    private boolean downAttach = true;

    private boolean isCancled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_popwindow);

        mGridView = (MyGridView) findViewById(R.id.gridview);
        progressWheel = (ProgressWheel) findViewById(R.id.progress_bar);

        List<String> items = fillList();
        mGridView.setAdapter(new PopWindowAdapter(this, items));

        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
                if (position == 0) {
                    downloadWithProgress();
                } else if (position == 1) {
                    uploadAttach();
                } else if (position == 2) {

                } else if (position == 3) {

                }
            }

            
        });
    }

    private List<String> fillList() {
        List<String> items = new ArrayList<String>();
        items.add("带进度条下载");
        items.add("上传文件");
        return items;
    }

    /**
     * 带进度条下载
     */
    protected void downloadWithProgress() {
        String url = "http://p.gdown.baidu.com/138ad35674ae05b7e2b9ef22705082e6839b00ed92e08480d8a75f5c73abf9157d13bc07d43b8276e119e52276ca7f523d22bb037ec2efaa361b7e89dde68c71bd5134188f5a2a07882a6145fc79da9c5ef33c4c74e4f0df34d99abeae4afaecf068094e5faec7f12f3cd08d6872e77c0df3b7152fafe6ff1e3cbf77a11bea1613809f8c37646b6b0022364a820bdd0c3e8d624eefb779c97d3bed0ce4866b024bbb3e9e611009ec529d8cfa288e130dd137e2416e36f0a05d7eeb370ebcdca840b9df10a63ec88dcaafbc1bea99a647a4d30e89bdc43d8a";
        String name = "LazyWeather.apk";

        // String url =
        // "http://yinyueshiting.baidu.com/data2/music/134274216/103788310800128.mp3?xcode=3c7bddbe70a2b6900fd1a9e14bd081d7&amp;song_id=1037883";
        // String name = "Just Dance.mp3";
        new downloadAttachTask(url, name).execute();
    }
    
    private void uploadAttach() {
        new uploadAttach().execute();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 按下的如果是BACK，同时没有重复
            if (isCancled) {
                progressWheel.setVisibility(View.VISIBLE);
                isCancled = false;
                downAttach = false;
                return false;
            } else {
                finish();
                return true;
            }
        }
        return false;
    }

    /**
     * 下载文件
     * 
     * @author chenguoquan
     *
     */
    class downloadAttachTask extends AsyncTask<String, Integer, String> {

        String attach_fileurl;

        String file_name;

        String file_path;

        String file_size;

        public downloadAttachTask(String attach_fileurl, String file_name) {
            this.attach_fileurl = attach_fileurl;
            this.file_name = file_name;
        }

        @SuppressWarnings("resource")
        @Override
        protected String doInBackground(String... arg0) {
            File localFile = new File(ConstantUtil.ATTACH_PATH);
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            // 路径
            file_path = ConstantUtil.ATTACH_PATH + file_name;
            if (new File(file_path).exists()) {
                return "0";
            }
            String result = null;
            int count = 0, lastCount = 0;
            int divide = 1;
            try {
                URL myURL = new URL(attach_fileurl);
                URLConnection conn = myURL.openConnection();
                conn.connect();
                int fileSize = conn.getContentLength(); // 获取文件大小
                InputStream is = conn.getInputStream();
                File file = new File(file_path);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream fos = new FileOutputStream(file);
                // 把数据存入路径+文件名
                byte buf[] = new byte[1024];
                int downLoadFileSize = 0;
                do {
                    
                    if (downAttach) {
                        // 循环读取
                        int numread = is.read(buf);
                        if (numread == -1) {
                            break;
                        }
                        fos.write(buf, 0, numread);
                        downLoadFileSize += numread;
                        count = 100 * downLoadFileSize / fileSize;
                        publishProgress(count);
                        if (count - lastCount > divide) {
                            lastCount = count;
                        }
                    } else {
                        break;
                    }
                } while (true);
                if (downAttach) {
                    result = "0";
                } else {
                    result = "-1";
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressWheel.setVisibility(View.GONE);
            if (result.equals("0")) {
                isCancled = false;
                FileUtil.openFile(MainUploadAndDownloadAvtivity.this, file_path);
            } else if (result.equals("-1")) {
                downAttach = true;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressWheel.setVisibility(View.VISIBLE);
            progressWheel.resetCount();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressWheel.setProgress(values[0]);
        }
    }
    
    
    class uploadAttach extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... arg0) {
            String filepath=ConstantUtil.ATTACH_PATH+"LazyWeather.apk";
            
            try {
                FileUtils.readFileToString(new File(filepath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url="http://180.153.53.247:82/ylms/inter/upDataFile";
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("username", "t_user");
            map.put("password", "t_password");
            return postVoiceByHttpClient(MainUploadAndDownloadAvtivity.this, url, map, filepath);
        }
        
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null) {
                System.out.println("结果："+result);
            }
        }
        
    }
    
    
    /**
     * 上传图片文件
     * @param context
     * @param url
     * @param map
     * @param filepath
     * @return
     */
    public static String postFileByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        String result = null;
        HttpClient client = getHttpClient(context);
        HttpPost post = new HttpPost(url);
        String[] filestr = FileUtil.discussType(filepath);
        String file_type = null;
        String image_type = null;
        if (filestr.length == 2) {
            file_type = filestr[0];
            image_type = filestr[1];
        }
        File file = new File(filepath);
        FileBody fileBody = new FileBody(file, "profile." + file_type, "image/"
                + image_type, "UTF-8");

        MultipartEntity entity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);

        @SuppressWarnings("rawtypes")
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry items = (Map.Entry) iterator.next();
            try {
                entity.addPart((String) items.getKey(), new StringBody(
                        (String) items.getValue())); // 添加请求参数
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            entity.addPart("Content-Type", new StringBody(
                    "multipart/form-data;charset=utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        entity.addPart("file", fileBody);
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(),
                        DEFAULT_PARAMS_ENCODING);
                result = StringUtils.removeBOM(result);
                result = StringUtils.delEscapeCode(result);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        }catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 上传附件文件
     * 
     * @param context
     * @param url
     * @param map
     * @param filepath
     * @return
     */
    public  String postVoiceByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        String result = null;
        HttpClient client = getHttpClient(context);
        HttpPost post = new HttpPost(url);
        File file = new File(filepath);
        FileBody fileBody = new FileBody(file);
        MultipartEntity entity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);
        @SuppressWarnings("rawtypes")
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry items = (Map.Entry) iterator.next();
            try {
                entity.addPart((String) items.getKey(), new StringBody(
                        (String) items.getValue()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            entity.addPart("Content-Type", new StringBody(
                    "multipart/form-data;charset=utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        entity.addPart("file", fileBody);
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(),
                        DEFAULT_PARAMS_ENCODING);
                result = StringUtils.removeBOM(result);
                result = StringUtils.delEscapeCode(result);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        }catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final int DEFAULT_MAX_CONNECTIONS = 30;
    private static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    private static DefaultHttpClient sHttpClient;
    private static synchronized HttpClient getHttpClient(Context context) {
        final HttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);
        // 超时设置
        /* 从连接池中取连接的超时时间 */
        ConnManagerParams.setTimeout(httpParams, 1000);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,
                new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(httpParams,
                DEFAULT_MAX_CONNECTIONS);
        // 设置组件参数, HTTP协议的版本
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
        HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
        HttpClientParams.setRedirecting(httpParams, false);
        HttpProtocolParams.setUserAgent(httpParams, "Android client");
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_SOCKET_TIMEOUT);
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(httpParams,
                DEFAULT_SOCKET_TIMEOUT);
        HttpConnectionParams.setTcpNoDelay(httpParams, true);
        HttpConnectionParams.setSocketBufferSize(httpParams,
                DEFAULT_SOCKET_BUFFER_SIZE);
        // 设置我们的HttpClient支持HTTP和HTTPS两种模式
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", PlainSocketFactory
                .getSocketFactory(), 443));
        // 使用线程安全的连接管理来创建HttpClient
        ClientConnectionManager manager = new ThreadSafeClientConnManager(
                httpParams, schemeRegistry);
        sHttpClient = new DefaultHttpClient(manager, httpParams);
        return sHttpClient;
    }
}

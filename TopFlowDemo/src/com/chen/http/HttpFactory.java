package com.chen.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.chen.util.FileUtil;
import com.chen.util.StringUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

@SuppressWarnings("deprecation")
public class HttpFactory {
    private static final int DEFAULT_MAX_CONNECTIONS = 30;

    private static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;

    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;

    private static DefaultHttpClient sHttpClient;

    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    
    private static long totalSize = 0;  //记录传输的进度
    
    /**
     * 上传图片返回进度
     * @param context
     * @param url
     * @param map
     * @param filepath
     * @param handler
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String postFileWithProByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath,final Handler handler) {
        String result=null;
        HttpClient client=getHttpClient(context);
        HttpPost post = new HttpPost(url);
        String[] filestr = FileUtil.discussType(filepath);
        String file_type = null;
        String image_type = null;
        if (filestr.length == 2) {
            file_type = filestr[0];
            image_type = filestr[1];
        }
        File file = new File(filepath);
        FileBody fileBody = new FileBody(file, "profile."
                + file_type, "image/" + image_type, "UTF-8");

        CustomMultiPartEntity entity = new CustomMultiPartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE,
                new CustomMultiPartEntity.ProgressListener() {

                    @Override
                    public void transferred(long num) {
                        Message message=new Message();
                        message.what=100;
                        message.arg1=(int) ((num / (float) totalSize) * 99);
                        handler.sendMessage(message);
                    }
                });

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry items = (Map.Entry) iterator.next();
            try {
                entity.addPart((String) items.getKey(),
                        new StringBody((String) items.getValue()));
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
        totalSize = entity.getContentLength();
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(
                        response.getEntity(), HTTP.UTF_8);
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
     * 上传图片文件
     * 
     * @param context
     * @param url
     * @param map
     * @param filepath
     * @return
     */
    public static String postFileByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        String result=null;
        HttpClient client=getHttpClient(context);
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
                        (String) items.getValue()));  //添加请求参数
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
     * 上传文件
     * @param context
     * @param url
     * @param map
     * @param filepath
     * @return
     */
    public static String postVoiceByHttpClient(Context context, String url,
            HashMap<String, String> map, String filepath) {
        String result=null;
        HttpClient client=getHttpClient(context);
        HttpPost post = new HttpPost(url);
        File file = new File(filepath);
        FileBody fileBody = new FileBody(file);
        MultipartEntity entity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE,null, Charset.forName("UTF-8"));
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

    /**
     * 下载图片
     * 
     * @param context
     * @param url
     * @return
     */
    public static Bitmap downloadImageByHttp(Context context, String url) {
        Bitmap bitmap = null;
        HttpClient client = getHttpClient(context); 
        HttpGet httpRequest = new HttpGet(url);
        try {
            // 请求httpClient ，取得HttpRestponse
            HttpResponse httpResponse = client.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 取得相关信息 取得HttpEntiy
                HttpEntity httpEntity = httpResponse.getEntity();
                // 获得一个输入流
                InputStream is = httpEntity.getContent();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    
    
    
    /**
     * 带进度条下载文件
     * 
     * @param url
     * @param path
     * @param context
     * @param handler
     * @return
     */
    @SuppressWarnings("resource")
    public static String downloadAttachFile(Context context, String fileurl,
            String savefile, Handler handler) {
        String result=null;
        int count = 0, lastCount = 0;
        int divide = 1;
        try {
            fileurl=URLEncoder.encode(fileurl,"utf-8").replaceAll("\\+", "%20").replaceAll("%3A", ":").replaceAll("%2F", "/");
            URL myURL = new URL(fileurl);
            URLConnection conn = myURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            int fileSize = conn.getContentLength();// 根据响应获取文件大小
            if (fileSize <= 0)
                throw new RuntimeException("无法获知文件大小 ");
            if (is == null)
                throw new RuntimeException("stream is null");
            File file = new File(savefile);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            // 把数据存入路径+文件名
            byte buf[] = new byte[1024];
            int downLoadFileSize = 0;
            do {
                // 循环读取
                int numread = is.read(buf);
                if (numread == -1) {
                    break;
                }
                fos.write(buf, 0, numread);
                downLoadFileSize += numread;
                count = 100 * downLoadFileSize / fileSize;
                if (count - lastCount > divide) {
                    lastCount = count;
                    System.out.println("下载进度：" + count);
                    Message message = new Message();
                    message.what = 100;
                    message.arg1 = count;
                    handler.sendMessage(message);
                }
            } while (true);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * post请求服务器
     * 
     * @param context
     * @param url
     * @param json
     * @return
     */
    public static String PostFromWebByHttpClient(Context context, String url,
            String json) {
        String result=null;
        HttpClient client = getHttpClient(context);
        HttpPost post = new HttpPost(url);
        // 设置头文件
        Map<String, String> headers = getHeader();
        Set<String> setHead = headers.keySet();
        Iterator<String> iteratorHead = setHead.iterator();
        while (iteratorHead.hasNext()) {
            String headName = iteratorHead.next();
            String headValue = (String) headers.get(headName);
            post.setHeader(headName, headValue);
        }
        StringEntity se = null;
        try {
            se = new StringEntity(json, "UTF-8");
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(se);
        post.getAllHeaders();
        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                 result = EntityUtils.toString(response.getEntity(),
                        DEFAULT_PARAMS_ENCODING);
                 result=StringUtils.removeBOM(result);
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
     * 设置http头信息
     * @return
     */
    public static Map<String, String> getHeader(){
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Accept", "application/json, application/json");
        heads.put("Content_Type", "application/json; charset=utf-8");
        heads.put("Expect", "100-continue");
        return heads;
    }
    
    
    /**
     * 
     * 创建httpClient实例
     * 
     * @param context
     * @return
     */
    public static synchronized HttpClient getHttpClient(Context context) {
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
        schemeRegistry.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 443)); 
        // 使用线程安全的连接管理来创建HttpClient  
        ClientConnectionManager manager = new ThreadSafeClientConnManager(
                httpParams, schemeRegistry);
        sHttpClient = new DefaultHttpClient(manager, httpParams);
        return sHttpClient;
    }

}

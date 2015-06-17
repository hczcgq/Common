package com.chen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class VoiceRecordActivity extends Activity implements
        OnClickListener {

    private Button btn_cancel, btn_done;

    private ImageButton ib_start;

    private TextView tv_time, tv_date;

    private boolean isRecording = true;

    private int hour = 0, second = 0, minute = 0; // 录音时分秒

    private Timer timer; // 计时器

    private boolean sdcardExit; // sdcard是否存在

    private File recordDir; // 录音保存路径

    private final String SUFFIX = ".amr";

    private File recordFile; // 录音文件

    private final String recordName = "voice_"; // 录音文件名称

    private MediaRecorder mMediaRecorder;

    private boolean isPause;

    private ArrayList<String> list;

    @SuppressWarnings("static-access")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_voice_record);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 设置窗口的大小及透明度
        layoutParams.width = LayoutParams.FILL_PARENT;
        layoutParams.height = layoutParams.FILL_PARENT;
        window.setAttributes(layoutParams);

        initViewAndEvent();
        initData();

    }

    private void initViewAndEvent() {
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_done = (Button) findViewById(R.id.btn_done);
        ib_start = (ImageButton) findViewById(R.id.ib_start);

        btn_cancel.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        ib_start.setOnClickListener(this);
    }

    private void initData() {
        tv_date.setText(getSimpleDate().replace("-", "/"));
        list = new ArrayList<String>();
        // 判断sd Card是否插入
        sdcardExit = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        // 取得sd card路径作为录音文件的位置
        if (sdcardExit) {
            String pathStr = Environment.getExternalStorageDirectory()
                    .getPath() + "/VoiceMagnage/temp";
            recordDir = new File(pathStr);
            if (!recordDir.exists()) {
                recordDir.mkdirs();
            }else {
                delAllFile(pathStr);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_cancel:
            stopRecord();
            finish();
            overridePendingTransition(R.anim.push_bottom_in,
                    R.anim.push_bottom_out);
            break;
        case R.id.btn_done:
            if (isRecording) {
                isRecording = false;
            } else {
                isRecording = true;
                stopRecord();
            }
            doneRecord();
            break;
        case R.id.ib_start:
            if (isRecording) {
                isRecording = false;
                ib_start.setBackgroundResource(R.drawable.record_stop);
                startRecord();
            } else {
                list.add(recordFile.getPath());
                isPause = true;
                isRecording = true;
                ib_start.setBackgroundResource(R.drawable.record_start);
                stopRecord();
            }
            break;
        }
    }

    private void doneRecord() {
        if (recordFile != null && recordFile.exists()) {
            if (isPause) {
                getInputCollection(list, true);
            }

            StringBuffer buffer = new StringBuffer();
            if (hour != 0) {
                buffer.append(hour + "时");
            }

            if (minute != 0) {
                buffer.append(minute + "分");
            }

            if (second != 0) {
                buffer.append(second + "秒");
            }

            Intent intent = new Intent();
            intent.putExtra("voice", recordFile.getAbsolutePath());
            intent.putExtra("time", buffer.toString());
            setResult(100, intent);
            finish();
        } else {
            Toast.makeText(this, "请点击中间按钮开始,开始录音",Toast.LENGTH_SHORT).show();
        }
    }
    

    private void getInputCollection(List<String> list, boolean isAddLastRecord) {

        // 创建音频文件,合并的文件放这里
        recordFile = new File(recordDir, recordName
                + System.currentTimeMillis() + SUFFIX);
        FileOutputStream fileOutputStream = null;

        if (!recordFile.exists()) {
            try {
                recordFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(recordFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // list里面为暂停录音 所产生的 几段录音文件的名字，中间几段文件的减去前面的6个字节头文件

        for (int i = 0; i < list.size(); i++) {
            File file = new File((String) list.get(i));
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] myByte = new byte[fileInputStream.available()];
                // 文件长度
                int length = myByte.length;
                // 头文件
                if (i == 0) {
                    while (fileInputStream.read(myByte) != -1) {
                        fileOutputStream.write(myByte, 0, length);
                    }
                }
                // 之后的文件，去掉头文件就可以了
                else {
                    while (fileInputStream.read(myByte) != -1) {

                        fileOutputStream.write(myByte, 6, length - 6);
                    }
                }
                fileOutputStream.flush();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 结束后关闭流
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 合成一个文件后，删除之前暂停录音所保存的零碎合成文件
        deleteListRecord(isAddLastRecord);

    }

    private void deleteListRecord(boolean isAddLastRecord) {
        for (int i = 0; i < list.size(); i++) {
            File file = new File((String) list.get(i));
            if (file.exists()) {
                file.delete();
            }
        }

    }

    @Override
    protected void onStop() {
        if (mMediaRecorder != null && isRecording) {
            // 停止录音
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        super.onStop();
    }

    private void stopRecord() {
        if (mMediaRecorder != null && isRecording) {
            // 停止录音
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 开始录音
     */
    private void startRecord() {

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                second++;
                if (second >= 60) {
                    second = 0;
                    minute++;
                }
                if (minute >= 60) {
                    minute = 0;
                    hour++;
                }
                handler.sendEmptyMessage(1);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);

        try {
            if (!sdcardExit) {
                Toast.makeText(this, "请插入SD card",Toast.LENGTH_SHORT).show();
                return;
            }
            // 创建音频文件
            recordFile = new File(recordDir, recordName
                    + System.currentTimeMillis() + SUFFIX);

            mMediaRecorder = new MediaRecorder();
            // 设置录音为麦克风
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // 录音文件保存这里
            mMediaRecorder.setOutputFile(recordFile.getAbsolutePath());
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            // mMediaRecorder01.getMaxAmplitude();
            // mMediaRecorder01.getAudioSourceMax();
            mMediaRecorder.setOnInfoListener(new OnInfoListener() {
                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {
                    int a = mr.getMaxAmplitude();
                     Toast.makeText(VoiceRecordActivity.this, a, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s_hour, s_minute, s_second;
            if (hour == 0) {
                s_hour = "00";
            } else if (hour < 10) {
                s_hour = "0" + hour;
            } else {
                s_hour = String.valueOf(hour);
            }
            if (minute == 0) {
                s_minute = "00";
            } else if (minute < 10) {
                s_minute = "0" + minute;
            } else {
                s_minute = String.valueOf(minute);
            }

            if (second == 0) {
                s_second = "00";
            } else if (second < 10) {
                s_second = "0" + second;
            } else {
                s_second = String.valueOf(second);
            }
            tv_time.setText(s_hour + ":" + s_minute + ":" + s_second);
        }

    };
    
    private String getSimpleDate() {
        Calendar c = Calendar.getInstance();
        int m_year = c.get(Calendar.YEAR); // 获得年份
        int m_month = c.get(Calendar.MONTH); // 获得月份
        int m_day = c.get(Calendar.DAY_OF_MONTH);// 获得天
        String data = m_year + "-" + (m_month + 1) + "-" + m_day;
        return data;
    }
    
    private void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
            }
        }
    }
    
    private void delFolder(String folderPath) {
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        java.io.File myFilePath = new java.io.File(filePath);
        myFilePath.delete();
    }
}

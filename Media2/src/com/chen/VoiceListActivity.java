package com.chen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class VoiceListActivity extends Activity {

    private boolean existSDCard; // sdcard是否存在

    private File voiceDir; // 录制文件路径

    private ArrayList<String> voiceArrayList = new ArrayList<String>();

    private VoiceAdapter adapter;

    private ListView mListView;

    private Button btn_record;

    String pathStr = Environment.getExternalStorageDirectory().getPath()
            + "/VoiceMagnage/voice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_voice_list);

        mListView = (ListView) findViewById(R.id.listview);
        btn_record = (Button) findViewById(R.id.btn_record);

        existSDCard = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (existSDCard) {

            voiceDir = new File(pathStr);
            if (!voiceDir.exists()) {
                voiceDir.mkdirs();
            }
        }
        getVoiceFiles();

        btn_record.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(VoiceListActivity.this,
                        VoiceRecordActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                String file_path = voiceArrayList.get(arg2);
                if (new File(file_path).exists()) {
                    FileUtil.openFile(VoiceListActivity.this, file_path);
                }
            }
        });
    }

    /**
     * 获取录制文件
     */
    private void getVoiceFiles() {
        voiceArrayList.clear();
        if (existSDCard) {
            File files[] = voiceDir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().indexOf(".") >= 0) {
                        String fileS = files[i].getName().substring(
                                files[i].getName().indexOf("."));
                        if (fileS.toLowerCase().equals(".mp3")
                                || fileS.toLowerCase().equals(".amr")
                                || fileS.toLowerCase().equals(".mp4"))
                            voiceArrayList.add(files[i].getName());
                    }
                }
            }
        }

        if (adapter == null) {
            adapter = new VoiceAdapter(this, voiceArrayList);
            mListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100 && requestCode == 100) {
            String file_path = data.getStringExtra("voice");
            File file = new File(file_path);
            System.out.println(file.getName());
            copy(file_path, pathStr + "/" + file.getName());
            getVoiceFiles();
        }
    }

    private boolean copy(String fileFrom, String fileTo) {
        try {
            FileInputStream in = new java.io.FileInputStream(fileFrom);
            FileOutputStream out = new FileOutputStream(fileTo);
            byte[] bt = new byte[1024];
            int count;
            while ((count = in.read(bt)) > 0) {
                out.write(bt, 0, count);
            }
            in.close();
            out.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}

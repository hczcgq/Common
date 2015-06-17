package com.chen;
/**
 * @author liwan
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.chen.R;


public class VoiceManageActivity extends Activity {
	private ImageButton myButton1;
	private ImageButton myButton2;
	private ImageButton myButton3;
	private ImageButton myButton4;
	private Button myButton;
	private ListView myListView1;
	private String strTempFile = "YYT_";
	private File myRecAudioFile;
	/**¼������·��**/
	private File myRecAudioDir;
	private File myPlayFile;
	private MediaRecorder mMediaRecorder01;
	private int mMinute;
	private boolean xx=true;
	/**�����Ƶ�ļ��б�**/
	private ArrayList<String> recordFiles;
	private ArrayAdapter<String> adapter;
	private TextView myTextView1;
	/**�ļ�����**/
	private boolean sdcardExit;
	/**�Ƿ�ֹͣ¼��**/
	private boolean isStopRecord;
	/**��ť����ͼƬ�ı�־λ**/
	private boolean sigle = false;
	private String length1 = null;
	
	private  final String SUFFIX=".amr";
	
	
	/**��ͣ��ť**/
	Button buttonpause;
	
	
	/**��¼��Ҫ�ϳɵļ���amr�����ļ�**/
	private ArrayList<String> list;
	
	
	int second=0;
	
	int minute=0;
	
	/**��ʱ��**/
	Timer timer;
	
	
	/**�Ƿ���ͣ��־λ**/
	private boolean isPause;
	
	/**����ͣ״̬��**/
	private boolean inThePause;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//��ͣ��־λ Ϊfalse
		isPause=false;
		//��ͣ״̬��־λ
		inThePause=false;
		
		//��ʼ��list
		list=new ArrayList<String>();
		
		//�ĸ���ť
		myButton1 = (ImageButton) findViewById(R.id.ImageButton01);
		myButton2 = (ImageButton) findViewById(R.id.ImageButton02);
		myButton3 = (ImageButton) findViewById(R.id.ImageButton03);
		myButton4 = (ImageButton) findViewById(R.id.ImageButton04);
		myButton = (Button) findViewById(R.id.myButton);
		buttonpause=(Button)findViewById(R.id.mypuase);
		myListView1 = (ListView) findViewById(R.id.ListView01);
		myTextView1 = (TextView) findViewById(R.id.TextView01);
		myButton2.setEnabled(false);
		myButton3.setEnabled(false);
		myButton4.setEnabled(false);
		
		myPlayFile=null;

		// �ж�sd Card�Ƿ����
		sdcardExit = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		// ȡ��sd card·����Ϊ¼���ļ���λ��
		if (sdcardExit){
			String pathStr = Environment.getExternalStorageDirectory().getPath()+"/YYT";
			myRecAudioDir= new File(pathStr);
			if(!myRecAudioDir.exists()){
				myRecAudioDir.mkdirs();
				Log.v("¼��", "����¼���ļ���" + myRecAudioDir.exists());
			}
//			Environment.getExternalStorageDirectory().getPath() + "/" + PREFIX + "/";
		}
		// ȡ��sd card Ŀ¼���.arm�ļ�
		getRecordFiles();
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, recordFiles);
		// ��ArrayAdater���ListView������
		myListView1.setAdapter(adapter);
		// ¼��
	
		myButton1.setOnClickListener(new ImageButton.OnClickListener() {
     
			@Override
			public void onClick(View v) {
			second=0;
			minute=0;
				
			list.clear();
//			Calendar c=Calendar.getInstance();
//			int	mMinute1=c.get(Calendar.MINUTE);
			
				sigle = true;
				// TODO Auto-generated method stub

				 start();

				if (sigle = false) {
					myButton1.setBackgroundResource(R.drawable.record_hover1);
				} else {
					myButton1.setBackgroundResource(R.drawable.record_dis1);
					myButton2.setBackgroundResource(R.drawable.stop_hover2);
					myButton3.setBackgroundResource(R.drawable.play_hover1);
					myButton4.setBackgroundResource(R.drawable.delete_hover);
				}
			
			
			}

		});
		// ֹͣ
		myButton2.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				xx=false;
				sigle = true;
				timer.cancel();
				// TODO Auto-generated method stub
				
				
				//����д��ͣ����� �ļ�������list���� �����ϳ�����
				if(isPause){
					
					//����ͣ״̬���½�����,����list�Ϳ�����
					if(inThePause){
						getInputCollection(list, false);
					}
					//������¼��ʱ������list����ĺ�����¼��������
					else{
						list.add(myRecAudioFile.getPath());
						recodeStop();
						getInputCollection(list, true);
					}
					
					//��ԭ��־λ
					isPause=false;
					inThePause=false;
					buttonpause.setText("��ͣ¼��");
					
				
					
					
				//	adapter.add(myRecAudioFile.getName());
					
				}
				
				
				
				//��¼��û�о����κ���ͣ
				else{
					
				
					if (myRecAudioFile != null) {
					// ֹͣ¼��
					mMediaRecorder01.stop();
					mMediaRecorder01.release();
					mMediaRecorder01 = null;
					// ��¼��Ƶ�ļ���Adapter
					adapter.add(myRecAudioFile.getName());
					DecimalFormat df = new DecimalFormat("#.000");
					if (myRecAudioFile.length() <= 1024*1024) {
						//length1 = (myRecAudioFile.length() / 1024.0)+"";
						
					      length1=df.format(myRecAudioFile.length() / 1024.0)+"K";
					} else {
						//length1 = (myRecAudioFile.length() / 1024.0 / 1024)+"";
						//DecimalFormat df = new DecimalFormat("#.000");
					      length1=df.format(myRecAudioFile.length() / 1024.0 / 1024)+"M";
					}
						System.out.println(length1);
				      
				      myTextView1.setText("ͣ  ֹ" + myRecAudioFile.getName()
							+ "�ļ���СΪ��" + length1);
					myButton2.setEnabled(false);
			
				}
				
			}

				if (sigle = false) {
					myButton2.setBackgroundResource(R.drawable.stop_hover2);
				} else {
					myButton1.setBackgroundResource(R.drawable.record_hover1);
					myButton2.setBackgroundResource(R.drawable.stop1);
					myButton3.setBackgroundResource(R.drawable.play_hover1);
					myButton4.setBackgroundResource(R.drawable.delete_hover);
				}
				
				//ֹͣ¼����
				isStopRecord = true;
			}

		});

		// ����
		myButton3.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				sigle = true;
				// TODO Auto-generated method stub
				if (myPlayFile != null && myPlayFile.exists()) {
					// �򿪲��ų���
					openFile(myPlayFile);
				} else {
					Toast.makeText(VoiceManageActivity.this, "��ѡ����һ�����ļ�", Toast.LENGTH_LONG)
							.show();
					Log.d("û��ѡ���ļ�","û��ѡ���ļ�");
				}
				if (sigle = false) {
					myButton3.setBackgroundResource(R.drawable.play_hover1);
				} else {
					myButton1.setBackgroundResource(R.drawable.record_hover1);
					myButton2.setBackgroundResource(R.drawable.stop_hover2);
					myButton3.setBackgroundResource(R.drawable.play1);
					myButton4.setBackgroundResource(R.drawable.delete_hover);
				}
			}

		});

		// ɾ��
		myButton4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sigle = true;
				// TODO Auto-generated method stub

				if (myPlayFile != null) {
					// �Ƚ�Adapterɾ���ļ���
					adapter.remove(myPlayFile.getName());
					// ɾ���ļ�
					if (myPlayFile.exists())
						myPlayFile.delete();
					myTextView1.setText("���ɾ����");

				}
				if (sigle = false) {
					myButton4.setBackgroundResource(R.drawable.delete_hover);
				} else {
					myButton1.setBackgroundResource(R.drawable.record_hover1);
					myButton2.setBackgroundResource(R.drawable.stop_hover2);
					myButton3.setBackgroundResource(R.drawable.play_hover1);
					myButton4.setBackgroundResource(R.drawable.delete_dis);
				}
			}
		});
		
		/**
		 * ��ͣ��ť,��¼֮ǰ����������ļ�
		 */
		buttonpause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			isPause=true;
				
				//�Ѿ���ͣ���ˣ��ٴε����ť ��ʼ¼����¼��״̬��¼����
			if(inThePause){
				buttonpause.setText("��ͣ¼��");
				start();
				inThePause=false;
			}
			//����¼���������ͣ,����¼��״̬Ϊ��ͣ
			else{
				
				//��ǰ����¼�����ļ�����ȫ��
				list.add(myRecAudioFile.getPath());
				inThePause=true;
				recodeStop();
				//start();
				buttonpause.setText("����¼��");
				
				//��ʱֹͣ
				timer.cancel();
			}
			}
		});
		
		
		myListView1
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						// ���е�����ļ���ʱ��ɾ����ť�����Ű�ťEnable
						myButton3.setEnabled(true);
						myButton4.setEnabled(true);
						myPlayFile = new File(myRecAudioDir.getAbsolutePath()
								+ File.separator
								+ ((TextView) arg1).getText().toString());
						
						DecimalFormat df = new DecimalFormat("#.000");
						if (myPlayFile.length() <= 1024*1024) {
							length1=df.format(myPlayFile.length() / 1024.0)+"K";
						} else {
							length1=df.format(myPlayFile.length() / 1024.0/1024)+"M";
						}
						myTextView1.setText("��ѡ����"
								+ ((TextView) arg1).getText().toString()
								+ "�ļ���СΪ��" + length1);
						Toast.makeText(VoiceManageActivity.this,"��ѡ����" + (((TextView) arg1).getText())+ "�ļ���СΪ��" + length1,
										Toast.LENGTH_LONG).show();

					}

				});

		myButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSize show = new showSize();
				String text = show.showsize();
				Toast.makeText(VoiceManageActivity.this, text, Toast.LENGTH_LONG).show();
			}
		});
	}

	
	protected void recodeStop() {
		if (mMediaRecorder01 != null && !isStopRecord) {
			// ֹͣ¼��
			mMediaRecorder01.stop();
			mMediaRecorder01.release();
			mMediaRecorder01 = null;
		}
		
		timer.cancel();
	}
	

	/**
	 * activity���������ڣ�stopʱ�ر�¼����Դ
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		if (mMediaRecorder01 != null && !isStopRecord) {
			// ֹͣ¼��
			mMediaRecorder01.stop();
			mMediaRecorder01.release();
			mMediaRecorder01 = null;
		}
		super.onStop();
	}


	/**
	 * ��ȡĿ¼�µ�������Ƶ�ļ�
	 */
	private void getRecordFiles() {
		// TODO Auto-generated method stub
		recordFiles = new ArrayList<String>();
		if (sdcardExit) {
			File files[] = myRecAudioDir.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().indexOf(".") >= 0) { // ֻȡ.amr �ļ�
						String fileS = files[i].getName().substring(
								files[i].getName().indexOf("."));
						if (fileS.toLowerCase().equals(".mp3")
								|| fileS.toLowerCase().equals(".amr")
								|| fileS.toLowerCase().equals(".mp4"))
							recordFiles.add(files[i].getName());

					}
				}
			}
		}

	}

	// ��¼�����ų���
	private void openFile(File f) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		String type = getMIMEType(f);
		intent.setDataAndType(Uri.fromFile(f), type);
		startActivity(intent);
//		Uri uri=Uri.fromFile(f);
//		MediaPlayer mediaPlayer=MediaPlayer.create(this, uri);
//		try {
//			mediaPlayer.prepare();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mediaPlayer.start();
	}

	private String getMIMEType(File f) {

		String end = f.getName().substring(f.getName().lastIndexOf(".") + 1,
				f.getName().length()).toLowerCase();
		String type = "";
		if (end.equals("mp3") || end.equals("aac") || end.equals("amr")
				|| end.equals("mpeg") || end.equals("mp4")) {
			type = "audio";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg")) {
			type = "image";
		} else {
			type = "*";
		}
		type += "/";
		return type;
	}
	
	private void start(){
		
		 
		 TimerTask timerTask=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				second++;
				if(second>=60){
					second=0;
					minute++;
				}
				handler.sendEmptyMessage(1);
			}
		};
		 timer=new Timer();
		 timer.schedule(timerTask, 1000,1000);
		
		try {
			if (!sdcardExit) {
				Toast.makeText(VoiceManageActivity.this, "�����SD card",
						Toast.LENGTH_LONG).show();
				return;
			}
			String	mMinute1=getTime();
			Toast.makeText(VoiceManageActivity.this, "��ǰʱ����:"+mMinute1,Toast.LENGTH_LONG).show();
			// ������Ƶ�ļ�
//			myRecAudioFile = File.createTempFile(mMinute1, ".amr",
//					myRecAudioDir);
			
			myRecAudioFile=new File(myRecAudioDir,mMinute1+SUFFIX);
			mMediaRecorder01 = new MediaRecorder();
			// ����¼��Ϊ��˷�
			mMediaRecorder01
					.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder01
					.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
			mMediaRecorder01
					.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			
			//¼���ļ���������
			mMediaRecorder01.setOutputFile(myRecAudioFile
					.getAbsolutePath());
			mMediaRecorder01.prepare();
			mMediaRecorder01.start();
			
//			mMediaRecorder01.getMaxAmplitude();
//			mMediaRecorder01.getAudioSourceMax();
			mMediaRecorder01.setOnInfoListener(new OnInfoListener() {
				
				@Override
				public void onInfo(MediaRecorder mr, int what, int extra) {
					// TODO Auto-generated method stub
					int a=mr.getMaxAmplitude();
					Toast.makeText(VoiceManageActivity.this, a, 500).show();
				}
			});
			
			myTextView1.setText("¼����......");
			myButton2.setEnabled(true);
			myButton3.setEnabled(false);
			myButton4.setEnabled(false);
			isStopRecord = false;
		} catch (IOException e) {
			e.printStackTrace();

		}
	
	}
	private String getTime(){
		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy��MM��dd��HH��mm��ss");      
		Date  curDate=new  Date(System.currentTimeMillis());//��ȡ��ǰʱ��      
		String   time   =   formatter.format(curDate);  
		System.out.println("��ǰʱ��");
		return time;
		}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			myTextView1.setText("¼��ʱ�䣺"+minute+":"+second);
		}
		
	};
	
	/**
	 *  @param isAddLastRecord �Ƿ���Ҫ���list֮�������¼����һ��ϲ�
	 *  @return ���ϲ��������ַ�����
	 */
	public  void getInputCollection(List list,boolean isAddLastRecord){
		
		
		String	mMinute1=getTime();
		Toast.makeText(VoiceManageActivity.this, "��ǰʱ����:"+mMinute1,Toast.LENGTH_LONG).show();
		
		// ������Ƶ�ļ�,�ϲ����ļ�������
		File file1=new File(myRecAudioDir,mMinute1+SUFFIX);
		FileOutputStream fileOutputStream = null;
		 
		if(!file1.exists()){
			try {
				file1.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fileOutputStream=new FileOutputStream(file1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//list����Ϊ��ͣ¼�� �������� ����¼���ļ������֣��м伸���ļ��ļ�ȥǰ���6���ֽ�ͷ�ļ�
		
		
		
	
		for(int i=0;i<list.size();i++){
			File file=new File((String) list.get(i));
	    Log.d("list�ĳ���", list.size()+"");
			try {
				FileInputStream fileInputStream=new FileInputStream(file);
				byte  []myByte=new byte[fileInputStream.available()];
				//�ļ�����
				int length = myByte.length;
		
				//ͷ�ļ�
				if(i==0){
						while(fileInputStream.read(myByte)!=-1){
								fileOutputStream.write(myByte, 0,length);
							}
						}
					
				//֮����ļ���ȥ��ͷ�ļ��Ϳ�����
				else{
					while(fileInputStream.read(myByte)!=-1){
						
						fileOutputStream.write(myByte, 6, length-6);
					}
				}
				
				fileOutputStream.flush();
				fileInputStream.close();
				System.out.println("�ϳ��ļ����ȣ�"+file1.length());
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			}
		//������ر���
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			//���ϵ�ǰ����¼������һ��
//			if(isAddLastRecord){
//				
//				
//				//�ո�¼����
//				try {
//					FileInputStream fileInputStream=new FileInputStream(myRecAudioFile);
//					byte  []myByte=new byte[fileInputStream.available()];
//					System.out.println(fileInputStream.available()+"");
//					while(fileInputStream.read(myByte)!=-1){
//						//outputStream.
//						fileOutputStream.write(myByte, 6, (fileInputStream.available()-6));
//					}
//					
//					fileOutputStream.flush();
//					fileInputStream.close();
//					fileOutputStream.close();
//					System.out.println("�ϳ��ļ����ȣ�"+file1.length());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
			
		
			//�ϳ�һ���ļ���ɾ��֮ǰ��ͣ¼�������������ϳ��ļ�
			deleteListRecord(isAddLastRecord);
			//
			adapter.add(file1.getName());
	
	}
	
	private void deleteListRecord(boolean isAddLastRecord){
		for(int i=0;i<list.size();i++){
			File file=new File((String) list.get(i));
			if(file.exists()){
				file.delete();
			}
		}
		//������ͣ�󣬼���¼������һ����Ƶ�ļ�
		if(isAddLastRecord){
			myRecAudioFile.delete();
		}
	}
}
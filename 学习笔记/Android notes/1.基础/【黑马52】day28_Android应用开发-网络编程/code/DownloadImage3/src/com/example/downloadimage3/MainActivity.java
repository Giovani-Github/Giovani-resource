package com.example.downloadimage3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	static ImageView iv;
	static MainActivity main;
	
	static Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 1:
				iv.setImageBitmap((Bitmap)msg.obj); // ��ImageView����ʾͼƬ
				break;
				
			case 0:
				Toast.makeText(main, "����ʧ��", 0).show();
				break;
			}
		};
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        iv = (ImageView) findViewById(R.id.iv);
        main = this;
    }
    
    public void click(View v) {
    	
    	final String path = "http://192.168.1.102:8080/Android/wo.jpg";//Ҫ����������ַ
    	final File file = new File(getFilesDir(), getFileName(path));//Ҫ���浽���·��
    	
    	if(file.exists()) { //����Ѿ����ڻ���
System.out.println("���棡����");
    		Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());//���ݾ���·���������λͼ����
    		//��Ϊ���������̣߳����Բ���Ҫ��Ϣ����
    		iv.setImageBitmap(bm);//��ʾ��ImageView��
    		
    	} else { //���û�л��棬�������̣߳������������ȡ����
System.out.println("���磡����"); 		
    		Thread t = new Thread() {
    			public void run() {
    	    		FileOutputStream fos = null;
    	    		InputStream is = null;
    	    		try {
    	    			//ʹ��path������RUL���󣬵õ����Ӷ���
    					HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
    					//��ʼ�����Ӷ��󣬲��ҷ�������
    					conn.setRequestMethod("GET");
    					conn.setReadTimeout(5000);
    					conn.setConnectTimeout(5000);
    					conn.connect();
    					
    					if(conn.getResponseCode() == 200) { //����ɹ�
    						fos = new FileOutputStream(file);//�ļ������
    						is = conn.getInputStream();//���������͹���������������
    						byte[] bytes = new byte[1024];
    						int len = 0;
    						while((len = is.read(bytes)) != -1) { //��ȡ�������е����ݣ�û�з���-1.�ͱ�ʾ��������
    							fos.write(bytes, 0, len);//��bytes��0�±굽len��д�뵽fos�С����ǰ�����д�뵽�ļ���
    						}
    						
    						//�Ѵ��ڲ��洢�ж�ȡ�������ļ��������λͼ���󣬲���ʹ����Ϣ���У������߳�����ʾ��ImageView��
    						Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());//���ݾ���·��������λͼ����
    						Message msg = handler.obtainMessage();//
    						msg.obj = bm;
    						msg.what = 1;//�ɹ�����Ϣ
    						handler.sendMessage(msg);//����Ϣ���͸���Ϣ����
    						
    					} else { //����ʧ��
    						Message msg = handler.obtainMessage();
    						msg.what = 0; //ʧ�ܵ���Ϣ
    						handler.sendMessage(msg);
    					}
    					
    				} catch (Exception e) {
    					throw new RuntimeException(e);
    				} finally {
    					try {
    						if(is != null) is.close();
    						if(fos != null) is.close();
    					} catch (Exception e2) {
    						throw new RuntimeException(e2);
    					}
    				}
    			};
    		};
    		t.start();
    	}
    }
    
    private String getFileName(String path) {
    	int index = path.lastIndexOf("/");
    	return path.substring(index + 1);
    }
}

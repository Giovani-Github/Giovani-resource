package com.example.downloadimage2;

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
	
	//5. ������Ϣ����������
	static Handler handler = new Handler() {
		//�����Ϣ����������Ϣ��Looper����ñ�������������Ϣ���󴫵ݹ���
		public void handleMessage(android.os.Message msg) {
			//6. ������Ϣ�����ͣ�����ͬ����
			switch(msg.what) {
			case 1: //����ǳɹ�����Ϣ
				
				//7. ��ʾͼƬ��ImageView��
				iv.setImageBitmap((Bitmap)msg.obj);				
				break;
			
			case 0: //ʧ�ܵ���Ϣ
				
				//8. ����˾
				Toast.makeText(main, "����ʧ��", 0).show();
				break;
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv = (ImageView) findViewById(R.id.iv);
        main = this;
    }
    
    public void click(View v) {
    	//1. ����һ�����̣߳����������������ӣ���ȡ���������ص�����
    	Thread t = new Thread() {
    		public void run() {
    			try {
    				//2.ȷ����ַ����װ��URL����ͨ��URL��ȡ����������ӵĶ���
					HttpURLConnection conn = (HttpURLConnection) new URL("http://192.168.1.102:8080/Android/wo.jpg").openConnection();
					
					//3. �����Ӷ����ʼ��,���ҷ�������
					conn.setRequestMethod("GET");
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					conn.connect();
					
					if(conn.getResponseCode() == 200) { //�������ɹ�
						//4.��ȡ���������͹����������������ҹ����λͼ����
						Bitmap bm = BitmapFactory.decodeStream(conn.getInputStream());
						
						//6. ʹ����Ϣ���У�����ʾͼƬ�����߳���ִ��
						
						Message msg = handler.obtainMessage();//������new������Ϣ�������ķ���������
						msg.obj = bm; //Я������
						msg.what = 1; // �Լ����壬1��ʾ����ɹ�����Ϣ
						handler.sendMessage(msg);//����Ϣ���������̵߳���Ϣ����
					} else { //����ʧ��
						Message msg = handler.obtainMessage();
						msg.what = 0;//��ʾʧ�ܵ���Ϣ
						handler.sendMessage(msg);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	};
    	
    	//�����߳�
    	t.start();
    }
}

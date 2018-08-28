package com.example.downloadtext;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static TextView tv;
	
	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			tv.setText((String)msg.obj);//��������ʾ��TextView
		};
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.tv);
    }
    
    public void click(View v) {
    	Thread t = new Thread() {
    		public void run() {
    			try {
    				HttpURLConnection conn = (HttpURLConnection) new URL("http://192.168.1.102:8080/Android/wo.txt").openConnection();
    				conn.setRequestMethod("GET");
    				conn.setConnectTimeout(5000);
    				conn.setReadTimeout(5000);
    				if(conn.getResponseCode() == 200) { //��ȡ״̬�룬�Դ��������ӵĹ���
    					InputStream in = conn.getInputStream();//�õ����������͹������ı�����
    					ByteArrayOutputStream bos = new ByteArrayOutputStream();//�ֽ��������������ȡ�����ݴ�ŵ�����
    					byte[] b = new byte[1024];
    					int len = 0;
    					while((len = in.read(b)) != -1) {
    						bos.write(b, 0, len);
    					}
    					
    					//���ֽ����������������ݣ�������ַ���
    					String text = new String(bos.toByteArray());
    					
    					//���ı���ͨ�������������ݸ���Ϣ���С������߳��У������ı���ʾ�ڽ���
    					Message msg = handler.obtainMessage();
    					msg.obj = text;
    					handler.sendMessage(msg);    					
    					
    				}
    			} catch (Exception e) {
    				throw new RuntimeException(e);
    			}
    		};
    	};
    	t.start();   	
    }
}

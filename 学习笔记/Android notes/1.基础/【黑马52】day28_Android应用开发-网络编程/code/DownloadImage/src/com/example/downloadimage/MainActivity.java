package com.example.downloadimage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) throws Exception {
    	//1. ȷ��Ҫ�������ַ������дloaclhost������дip��ַ
    	String path = "http://192.168.1.107:8080/Android/wo.jpg";
		//2. ͨ����ַ�������һ��URL����
		URL url = new URL(path);
		//3. ͨ��url���õ��ͻ���������������Ӷ��󣬴�ʱ��û�н�������
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		//4. �����Ӷ������ó�ʼ������
		conn.setRequestMethod("GET");//��������ʽ�������Ǵ�д
		conn.setConnectTimeout(5000);//�������ӳ�ʱʱ�䣬5��
		conn.setReadTimeout(5000);//���ö�ȡ��ʱʱ�䣬5��
		
		//5. �������ӣ�����GET����
		conn.connect();
		
		//6. ���ݷ��ص���Ӧ/״̬�룬����ͬ�Ĳ���
		if(conn.getResponseCode() != 200) { //��������벻��200����������û�гɹ�
			Toast.makeText(this, "����ʧ�ܣ�", 0).show();
		} else { //����ɹ�
			
			//7. �õ����������ص�����������������ݾ��ǿͻ������������
			InputStream is = conn.getInputStream();
			//8. ��ȡ����������ݣ��������λͼ����λͼ��ʵ����ͼƬ
			Bitmap bm = BitmapFactory.decodeStream(is);
			
			
			//9. ��λͼ������ʾ��ImageView�У���ʵ���ǰ�ͼƬ��ʾ��ImageView��
			ImageView iv = (ImageView) findViewById(R.id.iv);
			iv.setImageBitmap(bm);
		}
    }
}

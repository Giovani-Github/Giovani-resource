package com.example.downloadimage4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }    
    
    public void click(View v) {
    	String path = "http://192.168.1.102:8080/Android/wo.jpg";//Ҫ���ʵ���ַ
    	
    	//�õ�ֻ��ͼƬ�鿴������
    	SmartImageView siv = (SmartImageView) findViewById(R.id.siv);
    	
    	//���ز���ʾͼƬ
    	//�������Ѿ��߶ȷ�װ����������Download3��Ŀ���������¡����һ��Կ���������Ҫ����������˷�װ
    	//���Ǻ�ǿ���һ��ͼƬ�鿴��
    	siv.setImageUrl(path);
    	
    }
}

package com.example.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
    }
    
    public void click(View v) {
    	//�����Ի��򹹽����������ƹ���ģʽ
    	//ʹ��android.app.AlertDialog.Builder
    	Builder builder = new Builder(this);
    	
    	//�������Ͻ�ͼƬ
    	builder.setIcon(android.R.drawable.arrow_down_float);
    	builder.setTitle("�Ƿ�ȷ��");//���ñ���
    	builder.setMessage("�Ƿ�ɾ��");//��������
    	
    	//����ȷ����ť��ע�⣺����ļ�������:android.content.DialogInterface.OnClickListener;
    	builder.setPositiveButton("ȷ��", new OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				//��ʾ��˾�Ի���
				Toast.makeText(MainActivity.this, "ȷ��", 0).show();
			}
    	});
    	
    	//����ȡ����ť
    	builder.setNegativeButton("ȡ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//��ʾ��˾�Ի���
				Toast.makeText(MainActivity.this, "ȡ��", 0).show();
			}
		});
    	
    	//ʹ�ù������������Ի������
    	AlertDialog ad = builder.create();
    	//��ʾ�Ի���
    	ad.show();

		//Ҳ����ֱ������
//		duilder.show();
    }
}

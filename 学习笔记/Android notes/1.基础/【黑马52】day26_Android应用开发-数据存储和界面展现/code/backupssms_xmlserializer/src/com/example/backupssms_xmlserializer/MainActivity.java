package com.example.backupssms_xmlserializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;

import com.example.backupssms.domain.Message;

public class MainActivity extends Activity {
	
	private List<Message> messageList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //���������
        createrSms();
    }
    
    //���������
    private void createrSms() {
    	messageList = new ArrayList<Message>();
    	
    	for(int i = 0; i < 10; i++) {
    		Message ms = new Message("����" + i, System.currentTimeMillis() + "", "138" + i + i, "1");
    		messageList.add(ms);
    	}
    }
    
    //���ݰ�ť���ʱ�����ݶ��ŵ�xml�ļ���
    public void click(View v) {
    	
    	//�õ����л���
    	XmlSerializer xs = Xml.newSerializer();
    	   	
    	File file = new File(Environment.getExternalStorageDirectory(), "sms2.xml");
    	FileOutputStream fos = null;
    	try {
			fos = new FileOutputStream(file);
			
			//��ʼ�����л����������ַ������ֽ�������ָ����ʲô��������xml�ļ�
			xs.setOutput(fos, "utf-8");
			
			/*
			 * ��ʼ����xml�ļ�
			 * ����ͷ�ڵ㣬����xml�ļ��ı�ʾ�ڵ㡣
			 * ����ͷ�ڵ�encoding���Ե�ֵ����xml�ļ��Ƿ�������������ǲ���Ҫ����Լ���ļ�Լ��
			 */
			xs.startDocument("utf-8", true);
			
			//���ɿ�ʼ��ǩ�����������ռ�ͱ�ǩ��
			xs.startTag(null, "message");
			
			for (Message ms : messageList) {
				xs.startTag(null, "sms");
				
				xs.startTag(null, "body");
				xs.text(ms.getBody());//�����ı�����
				xs.endTag(null, "body");
				
				xs.startTag(null, "date");
				xs.text(ms.getDate());//�����ı�����
				xs.endTag(null, "date");
				
				xs.startTag(null, "type");
				xs.text(ms.getType());//�����ı�����
				xs.endTag(null, "type");
				
				xs.startTag(null, "address");
				xs.text(ms.getAddress());//�����ı�����
				xs.endTag(null, "address");
				
				xs.endTag(null, "sms");
			}
			
			//���ɽ�����ǩ
			xs.endTag(null, "message");
			
			//�������л������������
			xs.endDocument();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }   
}

package com.example.backupssms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
    	File file = new File(Environment.getExternalStorageDirectory(), "sms.xml");
    	FileOutputStream fos = null;
    	try {
			fos = new FileOutputStream(file);
			
			//�Ѷ���д��xml�ļ�
			fos.write(jointXml().getBytes());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(fos != null) {
					fos.close();
				}
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
    	
    }
    
    //ƾ���ַ���������xml
    private String jointXml() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("<?xml version='1.0' encoding='utf-8'?>");
    	sb.append("<messages>");
    	
    	for (Message ms : messageList) {
        	sb.append("<sms>");
    		
        	sb.append("<body>");
        	sb.append(ms.getBody());
        	sb.append("</body>");
        	
        	sb.append("<date>");
        	sb.append(ms.getDate());
        	sb.append("</date>");
        	
        	sb.append("<type>");
        	sb.append(ms.getType());
        	sb.append("</type>");
        	
        	sb.append("<address>");
        	sb.append(ms.getAddress());
        	sb.append("</address>");
        	
        	sb.append("</sms>");
		}
    	
    	sb.append("</messages>");
    	return sb.toString();
    }
}

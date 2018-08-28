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
        
        //虚拟出短信
        createrSms();
    }
    
    //虚拟出短信
    private void createrSms() {
    	messageList = new ArrayList<Message>();
    	
    	for(int i = 0; i < 10; i++) {
    		Message ms = new Message("内容" + i, System.currentTimeMillis() + "", "138" + i + i, "1");
    		messageList.add(ms);
    	}
    }
    
    //备份按钮点击时，备份短信到xml文件中
    public void click(View v) {
    	
    	//拿到序列化器
    	XmlSerializer xs = Xml.newSerializer();
    	   	
    	File file = new File(Environment.getExternalStorageDirectory(), "sms2.xml");
    	FileOutputStream fos = null;
    	try {
			fos = new FileOutputStream(file);
			
			//初始化序列化器，给出字符流或字节流，和指定用什么编码生成xml文件
			xs.setOutput(fos, "utf-8");
			
			/*
			 * 开始生成xml文件
			 * 生成头节点，就是xml文件的标示节点。
			 * 给出头节点encoding属性的值，和xml文件是否独立：独立就是不需要其他约束文件约束
			 */
			xs.startDocument("utf-8", true);
			
			//生成开始标签，给出命名空间和标签名
			xs.startTag(null, "message");
			
			for (Message ms : messageList) {
				xs.startTag(null, "sms");
				
				xs.startTag(null, "body");
				xs.text(ms.getBody());//生成文本内容
				xs.endTag(null, "body");
				
				xs.startTag(null, "date");
				xs.text(ms.getDate());//生成文本内容
				xs.endTag(null, "date");
				
				xs.startTag(null, "type");
				xs.text(ms.getType());//生成文本内容
				xs.endTag(null, "type");
				
				xs.startTag(null, "address");
				xs.text(ms.getAddress());//生成文本内容
				xs.endTag(null, "address");
				
				xs.endTag(null, "sms");
			}
			
			//生成结束标签
			xs.endTag(null, "message");
			
			//告诉序列化器，生成完毕
			xs.endDocument();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }   
}

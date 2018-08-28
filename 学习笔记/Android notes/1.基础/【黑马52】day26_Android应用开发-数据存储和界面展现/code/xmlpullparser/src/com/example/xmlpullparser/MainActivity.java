package com.example.xmlpullparser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import com.itheima.pullparser.domain.City;

public class MainActivity extends Activity {
	private List<City> cityList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	//���xml�ļ�
    	//InputStream is = this.getClass().getClassLoader().getResourceAsStream("weather.xml");
    	InputStream is = getClassLoader().getResourceAsStream("weather.xml");
    	
    	//��xml���city�ڵ������city����
    	City city = null;
    	
    	//�õ�pull������
    	XmlPullParser xp = Xml.newPullParser();
    	
    	try {
    		//��ʼ��pull�����������ݶ�ȡ�����ͱ���
			xp.setInput(is, "utf-8");
			
			//�õ���ǰ�ڵ���¼�����
			int type = xp.getEventType();
			
			//�����ǰ�¼�����END_DOCUMENT��while����ѭ��
			while(type != XmlPullParser.END_DOCUMENT) {
				
				//���ݽڵ��¼����͵Ĳ�ͬ��Ҫ����ͬ�Ĳ���
				switch (type) {
				
				//����ǿ�ʼ�ڵ㣬���¼�����
				case XmlPullParser.START_TAG:
					
					//�õ��ڵ�����ƣ��ж����ĸ��ڵ�
					if(xp.getName().equals("weather")) { //�����weather�ڵ�
						
						//��ô��һ���ڵ����city�ڵ㣬ΪcityList��ʼ�������¿���װcity����
						cityList = new ArrayList<City>();
						
					} else if(xp.getName().equals("city")) { // �����city�ڵ�
						
						//Ϊcity�����ʼ������city�ڵ���ӽڵ������city������
						city = new City();
						
					} else if(xp.getName().equals("name")) { //�����name�ڵ�
						
						//��ô��һ���ڵ����name�ڵ���ı�����
						//��ȡ��ǰ�ڵ����һ���ڵ���ı�������ָ���ƶ�����ǰ�ڵ�Ľ����ڵ�
						String name = xp.nextText();
						//���浽city��
						city.setName(name);
						
					} else if(xp.getName().equals("temp")) { //�����temp�ڵ�
						
						//��ȡ��ǰ�ڵ����һ���ڵ���ı�
						String temp = xp.nextText();
						//���浽city��
						city.setTemp(temp);
						
					} else if(xp.getName().equals("pm")) { //�����pm�ڵ�
						
						//��ȡ��ǰ�ڵ����һ���ڵ���ı�
						String pm = xp.nextText();
						//���浽city��
						city.setPm(pm);
						
					}
					break;
					
				//����ǽ����ڵ㣬���¼�����
				case XmlPullParser.END_TAG:
					
					if(xp.getName().equals("city")) {	//�����ǰ�Ľ����ڵ���city
						
						//�Ͱ����city�ڵ��н�������city���󱣴浽cityList��
						cityList.add(city);
						
					}
					break;
				}
				
				//һ��ѭ���������Ͱ�ָ���ƶ�����һ���ڵ㣬�����ظýڵ���¼�����
				type = xp.next();
			}
			
			
			//����ӡ����������������city����
			for(City c : cityList) {
				System.out.println(c);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
}

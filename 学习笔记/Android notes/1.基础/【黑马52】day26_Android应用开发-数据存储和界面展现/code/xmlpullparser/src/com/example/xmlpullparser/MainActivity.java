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
    	//获得xml文件
    	//InputStream is = this.getClass().getClassLoader().getResourceAsStream("weather.xml");
    	InputStream is = getClassLoader().getResourceAsStream("weather.xml");
    	
    	//把xml里的city节点解析成city对象
    	City city = null;
    	
    	//拿到pull解析器
    	XmlPullParser xp = Xml.newPullParser();
    	
    	try {
    		//初始化pull解析器，传递读取流，和编码
			xp.setInput(is, "utf-8");
			
			//拿到当前节点的事件类型
			int type = xp.getEventType();
			
			//如果当前事件不是END_DOCUMENT，while继续循环
			while(type != XmlPullParser.END_DOCUMENT) {
				
				//根据节点事件类型的不同，要做不同的操作
				switch (type) {
				
				//如果是开始节点，的事件类型
				case XmlPullParser.START_TAG:
					
					//拿到节点的名称，判断是哪个节点
					if(xp.getName().equals("weather")) { //如果是weather节点
						
						//那么下一个节点就是city节点，为cityList初始化，等下可以装city对象
						cityList = new ArrayList<City>();
						
					} else if(xp.getName().equals("city")) { // 如果是city节点
						
						//为city对象初始化，把city节点的子节点解析到city对象中
						city = new City();
						
					} else if(xp.getName().equals("name")) { //如果是name节点
						
						//那么下一个节点就是name节点的文本内容
						//获取当前节点的下一个节点的文本，并把指针移动至当前节点的结束节点
						String name = xp.nextText();
						//保存到city中
						city.setName(name);
						
					} else if(xp.getName().equals("temp")) { //如果是temp节点
						
						//获取当前节点的下一个节点的文本
						String temp = xp.nextText();
						//保存到city中
						city.setTemp(temp);
						
					} else if(xp.getName().equals("pm")) { //如果是pm节点
						
						//获取当前节点的下一个节点的文本
						String pm = xp.nextText();
						//保存到city中
						city.setPm(pm);
						
					}
					break;
					
				//如果是结束节点，的事件类型
				case XmlPullParser.END_TAG:
					
					if(xp.getName().equals("city")) {	//如果当前的结束节点是city
						
						//就把这个city节点中解析到的city对象保存到cityList中
						cityList.add(city);
						
					}
					break;
				}
				
				//一个循环结束，就把指针移动到下一个节点，并返回该节点的事件类型
				type = xp.next();
			}
			
			
			//最后打印出解析出来的所有city对象
			for(City c : cityList) {
				System.out.println(c);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
}

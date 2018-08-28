package com.example.news;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.domain.News;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {
	
	private static ListView lv;
	private static List<News> newsList; // 存放所有news节点
	private static MainActivity main;
	private static Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1: //成功为集合赋值之后，为listView设置Adapter
				lv.setAdapter(new MyAdapter());
				break;

			case 0: //获取资源失败后弹吐司
				Toast.makeText(main, "获取信息失败！", 0).show();
				break;
			}
		};
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        main = this;
        
        getNewsInfo();
        
        lv = (ListView) findViewById(R.id.lv);

    }
    
    private static class MyAdapter extends BaseAdapter {

    	//获取要显示多少条目，条目有多少，getview就调用多少次
		@Override
		public int getCount() {
			return newsList.size();
		}

		//获取要显示的条目
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			News news = newsList.get(position); // 根据条目的下标，得到一个news对象
			View v = null;
			ViewHolder mHolder = null;
			if(convertView == null) { //如果没有缓存
				v = View.inflate(main, R.layout.item, null); // 填充布局文件为View对象，当做条目返回显示在ListView中
				
				//查找出所需要的组件，封装到Holder中，在存放在填充成的View对象中。
				mHolder = new ViewHolder();
				mHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
				mHolder.tv_detail = (TextView) v.findViewById(R.id.tv_detail);
				mHolder.tv_comment = (TextView) v.findViewById(R.id.tv_comment);
				mHolder.siv = (SmartImageView) v.findViewById(R.id.iv);
				v.setTag(mHolder);				
				
			} else { // 如果有缓存				
				v = convertView; // 布局文件就不用再次填充，直接拿过来用 即可
				mHolder = (ViewHolder) v.getTag(); // 并且把携带的Holder拿出来
			}
			
			//设置每个布局文件条目中的组件的内容
			
			//设置文本内容
			mHolder.tv_title.setText(news.getTitle());
			mHolder.tv_detail.setText(news.getDetail());
			mHolder.tv_comment.setText(news.getComment() + "条评论");
			
			//设置图片内容
			mHolder.siv.setImageUrl(news.getImage());
			
			return v;
		}
		
		public Object getItem(int position) {return null;}
		public long getItemId(int position) {return 0;}
    	
    }
    
    //存放布局文件中的组件
    private static class ViewHolder {
    	TextView tv_title;
    	TextView tv_detail;
    	TextView tv_comment;
    	SmartImageView siv;
    }

	private void getNewsInfo() {
		Thread t = new Thread() {
			public void run() {
				try {
					HttpURLConnection conn = (HttpURLConnection) new URL("http://192.168.1.110:8080/Android/news.xml").openConnection();
					conn.setRequestMethod("GET");
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					
					// 获取状态码并且建立连接
					if(conn.getResponseCode() == 200) { //请求成功
						
						//获取服务器发送过来的xml，并且解析封装成javabean集合
						InputStream is = conn.getInputStream(); // 这就是服务器发送过来的xml文件
						parseXml(is); // 解析xml
						
					} else { // 请求失败
						Message msg = handler.obtainMessage();
						msg.what = 0;
						handler.sendMessage(msg);
					}
				} catch (Exception e) { // 如果获取连接是报错
					Message msg = handler.obtainMessage();
					msg.what = 0;
					handler.sendMessage(msg);
				}
			}
		};
		
		t.start();
	}
	
	//解析xml，封装成javabean集合
	private void parseXml(InputStream is) {	
		try {
			XmlPullParser xp = Xml.newPullParser(); //得到pull解析器	
			xp.setInput(is, "utf-8"); // 设置xml的数据流给pull解析器
			int type = xp.getEventType(); // 得到事件类型
			News news = null; // 存放 解析出来news节点中的数据
			String title = null; // 存放标题
			String detail = null; // 存放正文
			String comment = null; // 存放评论数
			String image = null; //存放新闻配图的地址
			
			//对事件类型进行判断，就可以知道当前节点是什么节点
			while(type != XmlPullParser.END_DOCUMENT) { // 只要不是xml文本的末尾
				switch (type) {
				case XmlPullParser.START_TAG: // 如果是开始标签
					if(xp.getName().equals("newslist")) {
						newsList = new ArrayList<News>(); // 把集合初始化
					} else if(xp.getName().equals("news")) {
						news = new News();
					} else if(xp.getName().equals("title")) {
						title = xp.nextText(); // 获取下个文本节点的内容， 因为title下个节点是文本节点
						news.setTitle(title);
					} else if(xp.getName().equals("detail")) {
						detail = xp.nextText();
						news.setDetail(detail);
					} else if(xp.getName().equals("comment")) {
						comment = xp.nextText();
						news.setComment(comment);
					} else if(xp.getName().equals("image")) {
						image = xp.nextText();
						news.setImage(image);
					}
					
					break;
				case XmlPullParser.END_TAG: // 如果是结束标签
					if(xp.getName().equals("news")) { // 如果是</news>
						newsList.add(news); // 把news放到集合中
					}
					break;

				}
				
				type = xp.next(); // 当前节点解析完，把指针移动到下一个节点。并且返回下个节点的事件类型
			}
			
			//为了保证集合中的元素被赋值完毕，所以赋值完成后使用消息队列，通知主线程直接给ListView设置Adapter
			handler.sendEmptyMessage(1); // 因为不需要携带内容，所以直接发送空消息
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
 
}

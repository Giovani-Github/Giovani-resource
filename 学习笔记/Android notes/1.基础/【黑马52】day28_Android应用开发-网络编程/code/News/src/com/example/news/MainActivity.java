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
	private static List<News> newsList; // �������news�ڵ�
	private static MainActivity main;
	private static Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1: //�ɹ�Ϊ���ϸ�ֵ֮��ΪlistView����Adapter
				lv.setAdapter(new MyAdapter());
				break;

			case 0: //��ȡ��Դʧ�ܺ���˾
				Toast.makeText(main, "��ȡ��Ϣʧ�ܣ�", 0).show();
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

    	//��ȡҪ��ʾ������Ŀ����Ŀ�ж��٣�getview�͵��ö��ٴ�
		@Override
		public int getCount() {
			return newsList.size();
		}

		//��ȡҪ��ʾ����Ŀ
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			News news = newsList.get(position); // ������Ŀ���±꣬�õ�һ��news����
			View v = null;
			ViewHolder mHolder = null;
			if(convertView == null) { //���û�л���
				v = View.inflate(main, R.layout.item, null); // ��䲼���ļ�ΪView���󣬵�����Ŀ������ʾ��ListView��
				
				//���ҳ�����Ҫ���������װ��Holder�У��ڴ�������ɵ�View�����С�
				mHolder = new ViewHolder();
				mHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
				mHolder.tv_detail = (TextView) v.findViewById(R.id.tv_detail);
				mHolder.tv_comment = (TextView) v.findViewById(R.id.tv_comment);
				mHolder.siv = (SmartImageView) v.findViewById(R.id.iv);
				v.setTag(mHolder);				
				
			} else { // ����л���				
				v = convertView; // �����ļ��Ͳ����ٴ���䣬ֱ���ù����� ����
				mHolder = (ViewHolder) v.getTag(); // ���Ұ�Я����Holder�ó���
			}
			
			//����ÿ�������ļ���Ŀ�е����������
			
			//�����ı�����
			mHolder.tv_title.setText(news.getTitle());
			mHolder.tv_detail.setText(news.getDetail());
			mHolder.tv_comment.setText(news.getComment() + "������");
			
			//����ͼƬ����
			mHolder.siv.setImageUrl(news.getImage());
			
			return v;
		}
		
		public Object getItem(int position) {return null;}
		public long getItemId(int position) {return 0;}
    	
    }
    
    //��Ų����ļ��е����
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
					
					// ��ȡ״̬�벢�ҽ�������
					if(conn.getResponseCode() == 200) { //����ɹ�
						
						//��ȡ���������͹�����xml�����ҽ�����װ��javabean����
						InputStream is = conn.getInputStream(); // ����Ƿ��������͹�����xml�ļ�
						parseXml(is); // ����xml
						
					} else { // ����ʧ��
						Message msg = handler.obtainMessage();
						msg.what = 0;
						handler.sendMessage(msg);
					}
				} catch (Exception e) { // �����ȡ�����Ǳ���
					Message msg = handler.obtainMessage();
					msg.what = 0;
					handler.sendMessage(msg);
				}
			}
		};
		
		t.start();
	}
	
	//����xml����װ��javabean����
	private void parseXml(InputStream is) {	
		try {
			XmlPullParser xp = Xml.newPullParser(); //�õ�pull������	
			xp.setInput(is, "utf-8"); // ����xml����������pull������
			int type = xp.getEventType(); // �õ��¼�����
			News news = null; // ��� ��������news�ڵ��е�����
			String title = null; // ��ű���
			String detail = null; // �������
			String comment = null; // ���������
			String image = null; //���������ͼ�ĵ�ַ
			
			//���¼����ͽ����жϣ��Ϳ���֪����ǰ�ڵ���ʲô�ڵ�
			while(type != XmlPullParser.END_DOCUMENT) { // ֻҪ����xml�ı���ĩβ
				switch (type) {
				case XmlPullParser.START_TAG: // ����ǿ�ʼ��ǩ
					if(xp.getName().equals("newslist")) {
						newsList = new ArrayList<News>(); // �Ѽ��ϳ�ʼ��
					} else if(xp.getName().equals("news")) {
						news = new News();
					} else if(xp.getName().equals("title")) {
						title = xp.nextText(); // ��ȡ�¸��ı��ڵ�����ݣ� ��Ϊtitle�¸��ڵ����ı��ڵ�
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
				case XmlPullParser.END_TAG: // ����ǽ�����ǩ
					if(xp.getName().equals("news")) { // �����</news>
						newsList.add(news); // ��news�ŵ�������
					}
					break;

				}
				
				type = xp.next(); // ��ǰ�ڵ�����꣬��ָ���ƶ�����һ���ڵ㡣���ҷ����¸��ڵ���¼�����
			}
			
			//Ϊ�˱�֤�����е�Ԫ�ر���ֵ��ϣ����Ը�ֵ��ɺ�ʹ����Ϣ���У�֪ͨ���߳�ֱ�Ӹ�ListView����Adapter
			handler.sendEmptyMessage(1); // ��Ϊ����ҪЯ�����ݣ�����ֱ�ӷ��Ϳ���Ϣ
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
 
}

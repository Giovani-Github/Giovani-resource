package com.example.listviewshowdatabase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewshowdatabase.domain.Person;
import com.example.listviewshowdatabase.oh.MySQLiteOpenHelper;

public class MainActivity extends Activity {
	
	private List<Person> personList;
	private MySQLiteOpenHelper oh;
	private SQLiteDatabase db;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personList = new ArrayList<Person>();
        oh= new MySQLiteOpenHelper(this, "poeple.db", null, 1);    
        db = oh.getWritableDatabase();
        
        select();
        showListView();
    }

	private void showListView() {
		//得到ListView
		ListView listView = (ListView) findViewById(R.id.lv);
		//给他设置Adapter
		listView.setAdapter(new MyAdapter());
	}
	
	
	//操作数据如何显示
	private class MyAdapter extends BaseAdapter {

		/*
		 * 系统调用，用来获知集合中有多少条元素
		 * 要显示多少条数据到屏幕
		 */
		public int getCount() {
			return personList.size();
		}
		
		/*
		 * 由系统调用，获取一个View对象，作为ListView的条目，显示在窗口中
		 * position:本次getView方法调用所返回的View对象，在listView中是处于第几个条目，那么position的值就是多少
		 * 	刚好对应集合中的下标
		 * convertView：条目的缓存
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
/**			
 * 比较单调的，简单的，把数据显示在窗口
 * 			TextView tv = new TextView(MainActivity.this); 
 *			Person person = personList.get(position);
 *			tv.setText(person.toString());
 *			return tv;
 **/
/*********************************************************************************************************************************/
			
			//稍微华丽点的，把设置好的布局文件，当成条目，在listview中显示
			
/*			Person person = personList.get(position);//获得要显示的数据
			
		
			 * 把布局文件填充成一个View对象
			 * 第三个参数viewGroup：给view对象找一个父元素
			 * 	在布局文件中，所有有子节点的元素都是ViewGroup的子类
			 
			View v = View.inflate(MainActivity.this, R.layout.listview, null);
*/
//------------------------------------------------------------------------------------------------------
			
			//对填充布局文件做个优化
			
			Person person = personList.get(position);//获得要显示的数据
			View v;
			
			//判断是否存在条目缓存
			if(convertView == null) {//如果没有条目被缓存，那么就重新把布局文件填充成view对象
				v = View.inflate(MainActivity.this, R.layout.listview, null);
			} else { //如果存在缓存好的条目，就直接使用这个被缓存好的条目
				//因为被传进来的缓存条目是随意的。所以要为缓存条目重新设置一个下内容
				v = convertView;
			}

//*****************************************************************************************************************************		
			
/*			//把布局文件填充成一个View对象还有两个方式
			//获取布局填充器对象
			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			//使用布局天填充器填充布局文件
			View v1 = inflater.inflate(R.layout.listview, null);
			
			二
			LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View v3 = inflater2.inflate(R.layout.listview, null);
*/
			
//*****************************************************************************************************************************			
			
			/*
			 * 为listview布局文件中的三个TextView设置内容。
			 * 注意：调用的是View对象的findViewById
			 * 否则：这个Activity中设置的布局文件是哪个，就回去哪个布局文件中findViewById
			 */
			TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
			tv_name.setText("name:" + person.getName());
			TextView tv_phone = (TextView) v.findViewById(R.id.tv_phone);
			tv_phone.setText("phone:" + person.getPhone());
			TextView tv_sal = (TextView) v.findViewById(R.id.tv_sal);
			tv_sal.setText("sal:" + person.getSal());			
			
			//把布局文件返回，当成条目在listView中显示
			return v;
		}
		
		
		//这两个方法不用管
		public Object getItem(int position) {return null;}
		public long getItemId(int position) {return 0;}
		
	}

	private void select() {
		Cursor sursor = db.query("person", null, null, null, null, null, null, null);
		
		while(sursor.moveToNext()) {
			String _id = sursor.getString(sursor.getColumnIndex("_id"));
			String name = sursor.getString(sursor.getColumnIndex("name"));
			String phone = sursor.getString(sursor.getColumnIndex("phone"));
			String sal = sursor.getString(sursor.getColumnIndex("sal"));
			
			Person person = new Person(_id, name, phone, sal);
			personList.add(person);
		}
	}
	
}

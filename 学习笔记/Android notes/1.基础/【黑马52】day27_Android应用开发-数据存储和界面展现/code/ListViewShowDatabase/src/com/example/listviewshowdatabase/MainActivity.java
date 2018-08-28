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
		//�õ�ListView
		ListView listView = (ListView) findViewById(R.id.lv);
		//��������Adapter
		listView.setAdapter(new MyAdapter());
	}
	
	
	//�������������ʾ
	private class MyAdapter extends BaseAdapter {

		/*
		 * ϵͳ���ã�������֪�������ж�����Ԫ��
		 * Ҫ��ʾ���������ݵ���Ļ
		 */
		public int getCount() {
			return personList.size();
		}
		
		/*
		 * ��ϵͳ���ã���ȡһ��View������ΪListView����Ŀ����ʾ�ڴ�����
		 * position:����getView�������������ص�View������listView���Ǵ��ڵڼ�����Ŀ����ôposition��ֵ���Ƕ���
		 * 	�պö�Ӧ�����е��±�
		 * convertView����Ŀ�Ļ���
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
/**			
 * �Ƚϵ����ģ��򵥵ģ���������ʾ�ڴ���
 * 			TextView tv = new TextView(MainActivity.this); 
 *			Person person = personList.get(position);
 *			tv.setText(person.toString());
 *			return tv;
 **/
/*********************************************************************************************************************************/
			
			//��΢������ģ������úõĲ����ļ���������Ŀ����listview����ʾ
			
/*			Person person = personList.get(position);//���Ҫ��ʾ������
			
		
			 * �Ѳ����ļ�����һ��View����
			 * ����������viewGroup����view������һ����Ԫ��
			 * 	�ڲ����ļ��У��������ӽڵ��Ԫ�ض���ViewGroup������
			 
			View v = View.inflate(MainActivity.this, R.layout.listview, null);
*/
//------------------------------------------------------------------------------------------------------
			
			//����䲼���ļ������Ż�
			
			Person person = personList.get(position);//���Ҫ��ʾ������
			View v;
			
			//�ж��Ƿ������Ŀ����
			if(convertView == null) {//���û����Ŀ�����棬��ô�����°Ѳ����ļ�����view����
				v = View.inflate(MainActivity.this, R.layout.listview, null);
			} else { //������ڻ���õ���Ŀ����ֱ��ʹ�����������õ���Ŀ
				//��Ϊ���������Ļ�����Ŀ������ġ�����ҪΪ������Ŀ��������һ��������
				v = convertView;
			}

//*****************************************************************************************************************************		
			
/*			//�Ѳ����ļ�����һ��View������������ʽ
			//��ȡ�������������
			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			//ʹ�ò������������䲼���ļ�
			View v1 = inflater.inflate(R.layout.listview, null);
			
			��
			LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View v3 = inflater2.inflate(R.layout.listview, null);
*/
			
//*****************************************************************************************************************************			
			
			/*
			 * Ϊlistview�����ļ��е�����TextView�������ݡ�
			 * ע�⣺���õ���View�����findViewById
			 * �������Activity�����õĲ����ļ����ĸ����ͻ�ȥ�ĸ������ļ���findViewById
			 */
			TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
			tv_name.setText("name:" + person.getName());
			TextView tv_phone = (TextView) v.findViewById(R.id.tv_phone);
			tv_phone.setText("phone:" + person.getPhone());
			TextView tv_sal = (TextView) v.findViewById(R.id.tv_sal);
			tv_sal.setText("sal:" + person.getSal());			
			
			//�Ѳ����ļ����أ�������Ŀ��listView����ʾ
			return v;
		}
		
		
		//�������������ù�
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

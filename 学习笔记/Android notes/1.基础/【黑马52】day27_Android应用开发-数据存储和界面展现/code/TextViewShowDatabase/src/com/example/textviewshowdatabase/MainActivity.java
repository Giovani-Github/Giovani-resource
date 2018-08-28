package com.example.textviewshowdatabase;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.textviewshowdatabase.domain.Person;
import com.example.textviewshowdatabase.openhelper.MySQLiteOpenHelper;

public class MainActivity extends Activity {

	private List<Person> personList;
	private SQLiteDatabase db;
	private MySQLiteOpenHelper oh;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        personList = new ArrayList<Person>();
        
		oh = new MySQLiteOpenHelper(this, "poeple.db", null,	1);
		db = oh.getWritableDatabase();
       
		createDatabase();//�����ݿ���в���50������
        select();//�����ݲ��ҳ���������װ��personList��
        show();//��������ʾ�����档��TextView
    
        db.close();
    }
    
    /**
     * �����ݿ����50������
     */
    private void createDatabase() {
		ContentValues values = new ContentValues();
		for(int i = 0; i < 50; i++) {
			values.put("name", "��"+i);
			values.put("sal", 12000+i);
			long id = db.insert("person", null, values);
			values.clear();
		}	
    }
    
    /**
     * �����ݿ��в�ѯ�����ݣ�����װ��personList��
     */
    private void select() {		
		//��ѯ�����ݿ��е��������ݣ��õ������
		Cursor cursor = db.query("person", null, null, null, null, null, null, null);

		//�Խ�������в�������ÿһ�����ݷ�װ��Person���󣬰�����Person��װ��personList��
		while(cursor.moveToNext()) {
			String _id = cursor.getString(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String sal = cursor.getString(cursor.getColumnIndex("sal"));
			
			//��ÿһ�����ݷ�װ��person��
			Person person = new Person(_id, name, phone, sal);

			
			//��person��ӵ�list��
			personList.add(person);
		}
    }
    
    /**
     * �Ѵ����ݿ��ѯ���������ݣ���ʾ����Ļ��
     */
    private void show() {
    	//�õ�LinearLayout
    	LinearLayout lly = (LinearLayout) findViewById(R.id.ll);
    	
    	//�ж��ٸ�Person����new���ٸ�TextView
    	for (Person p : personList) {
    		TextView tv = new TextView(this);
    		tv.setText(p.toString());
    		
    		//��ÿ��TextView���ŵ�LinearLayout��
    		lly.addView(tv);
		}
    }
}

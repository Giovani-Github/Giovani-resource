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
       
		createDatabase();//往数据库表中插入50条数据
        select();//把数据查找出来，并封装到personList中
        show();//把数据显示到界面。用TextView
    
        db.close();
    }
    
    /**
     * 往数据库插入50条数据
     */
    private void createDatabase() {
		ContentValues values = new ContentValues();
		for(int i = 0; i < 50; i++) {
			values.put("name", "赵"+i);
			values.put("sal", 12000+i);
			long id = db.insert("person", null, values);
			values.clear();
		}	
    }
    
    /**
     * 从数据库中查询出数据，并封装到personList中
     */
    private void select() {		
		//查询出数据库中的所有数据，得到结果集
		Cursor cursor = db.query("person", null, null, null, null, null, null, null);

		//对结果集进行操作，把每一行数据封装成Person对象，把所有Person封装到personList中
		while(cursor.moveToNext()) {
			String _id = cursor.getString(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String sal = cursor.getString(cursor.getColumnIndex("sal"));
			
			//把每一行数据封装到person中
			Person person = new Person(_id, name, phone, sal);

			
			//把person添加到list中
			personList.add(person);
		}
    }
    
    /**
     * 把从数据库查询出来的数据，显示到屏幕上
     */
    private void show() {
    	//得到LinearLayout
    	LinearLayout lly = (LinearLayout) findViewById(R.id.ll);
    	
    	//有多少个Person，就new多少个TextView
    	for (Person p : personList) {
    		TextView tv = new TextView(this);
    		tv.setText(p.toString());
    		
    		//把每个TextView，放到LinearLayout中
    		lly.addView(tv);
		}
    }
}

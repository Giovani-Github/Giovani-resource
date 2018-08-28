package com.example.login_sharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_password;
	private EditText et_username;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //获得用户名和密码的输入框
        et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
        
  	    readAccount();       
    }

    //读取文件里的数据，在这，不用判断文件是否存在。因为它有默认值
  	public void readAccount() {  		
  		//得到SharedPreferences
  		SharedPreferences  sp = getSharedPreferences("info", MODE_PRIVATE);
  		/*
  		 * 获取数据，可以获取字符串,int...
  		 * 	> 给出key
  		 * 	> 默认值：通过key找不到value，就返回默认值
  		 */
  		String username = sp.getString("username", "");
  		String password = sp.getString("password", "");
  		
  		et_username.setText(username);
  		et_password.setText(password);
  	}

  	//登录时调用的方法，把用户名和密码存在文件中
	public void login(View v) {
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		
		//获取复选框
		CheckBox cb = (CheckBox) findViewById(R.id.cb);
		//判断复选框是否被选中
		if(cb.isChecked()) {
			/*
			 * 获取sharedpreference
			 * 	> 给出文件名，不用后缀
			 * 	> 给出权限
			 * 
			 * 路径在data/data/com.itheima.sharedpreference/share_prefs
			 */
			SharedPreferences  sp = getSharedPreferences("info", MODE_PRIVATE);
			
			//拿到编辑器
			Editor ed = sp.edit();
			
			//写数据，给出key，和value
			ed.putString("username", username);
			ed.putString("password", password);
			//提交
			ed.commit();
		}
		
		//创建并显示吐司对话框
		Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
	}
}

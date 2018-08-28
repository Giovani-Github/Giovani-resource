package com.example.login_sdcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
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
        
  		//判断sd卡状态
  		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
  			 //调用读取文件数据的方法
  	        readAccount();
  		} else {
  			Toast.makeText(this, "sd卡不存在", 0).show();
  		}
       
    }

    //读取文件里的数据
  	public void readAccount() {  		
  		//也可以直接写sd卡的路径：sdcard | mnt/sdcard | storage/sdcard
		File file = new File(Environment.getExternalStorageDirectory(), "login.txt");
  		//判断文件是否存在
  		if(file.exists()) {
  			//创建文件读取字节流
  			FileInputStream fis = null;
  			//字符缓冲流，可以读取一行数据
  			BufferedReader br = null;
  			
  			try {
  				//与file文件关联
				fis = new FileInputStream(file);
				//把fis流装饰为字符流，然后给他添加字符缓冲流
				br = new BufferedReader(new InputStreamReader(fis));
				
				//读取一行数据
				String data = br.readLine();
				//把读取出来的字符串用"##"分割出用户名和密码
				String[] datas = data.split("##");
				String username = datas[0];
				String password = datas[1];
				
				//把用户名和密码在输入框中显示出来
				et_username.setText(username);
				et_password.setText(password);
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
  		}
  	  		
  	}

  	//登录时调用的方法，把用户名和密码存在文件中
	public void login(View v) {
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		
		//获取复选框
		CheckBox cb = (CheckBox) findViewById(R.id.cb);
		//判断复选框是否被选中
		if(cb.isChecked()) {
			
			//检测sd卡状态
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				//创建一个文件，在内部存储空间中
				File file = new File(Environment.getExternalStorageDirectory(), "login.txt");
				
				//创建一个文件输出字节流
				FileOutputStream fos = null;
				try {
					//与文件关联
					fos = new FileOutputStream(file);
					//要写入文件的数据
					byte[] bytes = (username + "##" + password).getBytes();
					//往流中写入数据
					fos.write(bytes);
				} catch(Exception e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if(fos != null) {
							fos.close();
						}
					} catch (Exception e2) {
						throw new RuntimeException(e2);
					}
				}
				
			} else {
				Toast.makeText(this, "sd卡不存在", 0).show();
			}
		}
		
		//创建并显示吐司对话框
		Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
	}
}

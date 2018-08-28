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
        
        //����û���������������
        et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
        
  		//�ж�sd��״̬
  		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
  			 //���ö�ȡ�ļ����ݵķ���
  	        readAccount();
  		} else {
  			Toast.makeText(this, "sd��������", 0).show();
  		}
       
    }

    //��ȡ�ļ��������
  	public void readAccount() {  		
  		//Ҳ����ֱ��дsd����·����sdcard | mnt/sdcard | storage/sdcard
		File file = new File(Environment.getExternalStorageDirectory(), "login.txt");
  		//�ж��ļ��Ƿ����
  		if(file.exists()) {
  			//�����ļ���ȡ�ֽ���
  			FileInputStream fis = null;
  			//�ַ������������Զ�ȡһ������
  			BufferedReader br = null;
  			
  			try {
  				//��file�ļ�����
				fis = new FileInputStream(file);
				//��fis��װ��Ϊ�ַ�����Ȼ���������ַ�������
				br = new BufferedReader(new InputStreamReader(fis));
				
				//��ȡһ������
				String data = br.readLine();
				//�Ѷ�ȡ�������ַ�����"##"�ָ���û���������
				String[] datas = data.split("##");
				String username = datas[0];
				String password = datas[1];
				
				//���û��������������������ʾ����
				et_username.setText(username);
				et_password.setText(password);
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
  		}
  	  		
  	}

  	//��¼ʱ���õķ��������û�������������ļ���
	public void login(View v) {
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		
		//��ȡ��ѡ��
		CheckBox cb = (CheckBox) findViewById(R.id.cb);
		//�жϸ�ѡ���Ƿ�ѡ��
		if(cb.isChecked()) {
			
			//���sd��״̬
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				//����һ���ļ������ڲ��洢�ռ���
				File file = new File(Environment.getExternalStorageDirectory(), "login.txt");
				
				//����һ���ļ�����ֽ���
				FileOutputStream fos = null;
				try {
					//���ļ�����
					fos = new FileOutputStream(file);
					//Ҫд���ļ�������
					byte[] bytes = (username + "##" + password).getBytes();
					//������д������
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
				Toast.makeText(this, "sd��������", 0).show();
			}
		}
		
		//��������ʾ��˾�Ի���
		Toast.makeText(this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
	}
}

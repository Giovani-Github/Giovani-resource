package com.example.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
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
        
        //����û���������������
        et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
        
        //���ö�ȡ�ļ����ݵķ���
        readAccount();
    }

    //��ȡ�ļ��������
  	public void readAccount() {
  		//Ҫ��ȡ���ļ�
//  		File file = new File("data/data/com.example.login/login.txt");
  		//getFilesDir()��õ���File���������������·����data/data/com.example.login/files
//  		File file = new File(getFilesDir(), "login.txt");
  		//getCacheDir()��õ���File���������������·����data/data/com.example.login/cache
  		File file = new File(getCacheDir(), "login.txt");
  		
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
			//����һ���ļ������ڲ��洢�ռ���
//			File file = new File("data/data/com.example.login/login.txt");
//			File file = new File(getFilesDir(), "login.txt");
			File file = new File(getCacheDir(), "login.txt");
			
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
		}
		
		/*
		 * ������˾�Ի���
		 * 	> ��Ҫһ�������Ķ���Context
		 * 	> �Ի�����ı���Ϣ
		 * 	> ������ʱ�䣺
		 * 		ֻ����Toast.LENGTH_LONG��Toast.LENGTH_SHORT��5���3��
		 * 		����1��0
		 */
		Toast t = Toast.makeText(this, "��¼�ɹ�", Toast.LENGTH_SHORT);
		//��ʾ��˾�Ի���
		t.show();
	}
    
}

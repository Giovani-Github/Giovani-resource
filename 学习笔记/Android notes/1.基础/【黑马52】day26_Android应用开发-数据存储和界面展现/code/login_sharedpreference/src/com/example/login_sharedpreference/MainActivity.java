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
        
        //����û���������������
        et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
        
  	    readAccount();       
    }

    //��ȡ�ļ�������ݣ����⣬�����ж��ļ��Ƿ���ڡ���Ϊ����Ĭ��ֵ
  	public void readAccount() {  		
  		//�õ�SharedPreferences
  		SharedPreferences  sp = getSharedPreferences("info", MODE_PRIVATE);
  		/*
  		 * ��ȡ���ݣ����Ի�ȡ�ַ���,int...
  		 * 	> ����key
  		 * 	> Ĭ��ֵ��ͨ��key�Ҳ���value���ͷ���Ĭ��ֵ
  		 */
  		String username = sp.getString("username", "");
  		String password = sp.getString("password", "");
  		
  		et_username.setText(username);
  		et_password.setText(password);
  	}

  	//��¼ʱ���õķ��������û�������������ļ���
	public void login(View v) {
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		
		//��ȡ��ѡ��
		CheckBox cb = (CheckBox) findViewById(R.id.cb);
		//�жϸ�ѡ���Ƿ�ѡ��
		if(cb.isChecked()) {
			/*
			 * ��ȡsharedpreference
			 * 	> �����ļ��������ú�׺
			 * 	> ����Ȩ��
			 * 
			 * ·����data/data/com.itheima.sharedpreference/share_prefs
			 */
			SharedPreferences  sp = getSharedPreferences("info", MODE_PRIVATE);
			
			//�õ��༭��
			Editor ed = sp.edit();
			
			//д���ݣ�����key����value
			ed.putString("username", username);
			ed.putString("password", password);
			//�ύ
			ed.commit();
		}
		
		//��������ʾ��˾�Ի���
		Toast.makeText(this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
	}
}

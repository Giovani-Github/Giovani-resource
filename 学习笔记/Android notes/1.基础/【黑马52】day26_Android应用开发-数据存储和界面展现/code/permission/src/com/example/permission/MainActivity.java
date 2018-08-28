package com.example.permission;

import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
    	FileOutputStream fos = null;
    	try {
    		//·���Ѿ�Ĭ��Ϊdata/data/com.itheima.permission/files�����ɸ���
    		//MODE_PRIVATE��Ȩ��˽��
			fos = openFileOutput("info1.txt", MODE_PRIVATE);
			fos.write("ddd".getBytes());
		} catch (Exception e) {
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
    
    public void click2(View v) {
    	FileOutputStream fos = null;
    	try {
    		//·���Ѿ�Ĭ��Ϊdata/data/com.itheima.permission/files�����ɸ���
    		//MODE_WORLD_WRITEABLE��ȫ�ֿ�д
    		//MODE_WORLD_READABLE��ȫ�ֿɶ�
			fos = openFileOutput("info2.txt", MODE_WORLD_WRITEABLE | MODE_WORLD_READABLE);
			fos.write("ddd".getBytes());
		} catch (Exception e) {
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
    
    public void click3(View v) {
    	FileOutputStream fos = null;
    	try {
    		//·���Ѿ�Ĭ��Ϊdata/data/com.itheima.permission/files�����ɸ���
    		//MODE_WORLD_READABLE��ȫ�ֿɶ�
			fos = openFileOutput("info3.txt", MODE_WORLD_READABLE);
			fos.write("ddd".getBytes());
		} catch (Exception e) {
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
}

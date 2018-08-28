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
    		//路径已经默认为data/data/com.itheima.permission/files，不可更改
    		//MODE_PRIVATE：权限私有
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
    		//路径已经默认为data/data/com.itheima.permission/files，不可更改
    		//MODE_WORLD_WRITEABLE：全局可写
    		//MODE_WORLD_READABLE：全局可读
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
    		//路径已经默认为data/data/com.itheima.permission/files，不可更改
    		//MODE_WORLD_READABLE：全局可读
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

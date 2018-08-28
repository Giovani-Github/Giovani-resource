package com.example.activityvita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	public void click(View v) {
		Intent intent = new Intent();
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("主create");
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	System.out.println("主start");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	System.out.println("主resume");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	System.out.println("主pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	System.out.println("主stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	System.out.println("主destroy");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	System.out.println("主restart");
    }
}

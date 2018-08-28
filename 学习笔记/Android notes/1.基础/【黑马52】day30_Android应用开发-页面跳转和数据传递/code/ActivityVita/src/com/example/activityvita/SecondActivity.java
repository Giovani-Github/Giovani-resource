package com.example.activityvita;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		System.out.println("二create");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("二start");		
	}
    
    @Override
    protected void onResume() {
    	super.onResume();
    	System.out.println("二resume");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	System.out.println("二pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	System.out.println("二stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	System.out.println("二destroy");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	System.out.println("二restart");
    }
}

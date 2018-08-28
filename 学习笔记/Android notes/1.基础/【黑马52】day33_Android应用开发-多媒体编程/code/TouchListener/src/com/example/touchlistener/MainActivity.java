package com.example.touchlistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView iv;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        iv = (ImageView) findViewById(R.id.iv);
        
        //设置触摸侦听
        iv.setOnTouchListener(new OnTouchListener() {

        	/*
        	 * 触摸屏幕时此方法调用
        	 * v : 谁触发的，就是谁
        	 * event: 动作事件。有：触摸屏幕，滑动屏幕，离开屏幕。还有当前位置的坐标
        	 */
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//得到当前的动作事件
				int action = event.getAction();
				switch(action) {
				//触摸屏幕事件
				case MotionEvent.ACTION_DOWN:
					int x = (int) event.getX();//拿到当前x坐标
					int y = (int) event.getY();//拿到当前y坐标
					System.out.println("触摸屏幕:" + x + ":" + y);
					break;
					
				//滑动屏幕事件
				case MotionEvent.ACTION_MOVE:
					int x1 = (int) event.getX(); //拿到当前x坐标
					int y1 = (int) event.getY(); //拿到当前y坐标
					System.out.println("滑动屏幕：" + x1 + ":" + y1);
					
					break;
					
				//离开屏幕事件
				case MotionEvent.ACTION_UP:
					int x2 = (int) event.getX();//拿到当前x坐标
					int y2 = (int) event.getY();//拿到当前y坐标
					System.out.println("离开屏幕:" + x2 + ":" + y2);
					break;
				}
				
				/*
				 * false：除了接收到的第一个事件，后续的事件都不处理，而是交给父节点处理
				 * true: 所有时间都给我处理
				 * 
				 * 一个父节点，一个子节点。
				 * 如果两个节点返回的都是false。那么就丢失这个时间，不做处理
				 * 如果两个节点都是ture。那么就是子节点处理。
				 * 实际上第一个接收到事件的是父节点，至于为什么会是子节点处理，后续讲解
				 */
				return true;
			}
        	
        });
    }    
}

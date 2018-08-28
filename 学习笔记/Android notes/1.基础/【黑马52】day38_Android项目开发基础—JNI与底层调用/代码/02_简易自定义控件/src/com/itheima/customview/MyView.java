package com.itheima.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	//在组件里绘制内容
	@Override
	protected void onDraw(Canvas canvas) {
		
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(20);
		
		canvas.drawText("今天最后黑一次小志", 10, 20, paint);
		
		canvas.drawRect(50, 100, 70, 200, paint);
		
	}
	
	
}

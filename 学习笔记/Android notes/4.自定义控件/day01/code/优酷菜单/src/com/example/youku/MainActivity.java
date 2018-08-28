package com.example.youku;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private RelativeLayout rl_level1;
	private RelativeLayout rl_level2;
	private RelativeLayout rl_level3;

	private ImageView iv_icon_home, iv_icon_menu;
	
	// 标记三层菜单是显示状态还是隐藏状态
	private boolean islevel1Show = true;
	private boolean islevel2Show = true;
	private boolean islevel3Show = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
		rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
		rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
		iv_icon_home = (ImageView) findViewById(R.id.iv_icon_home);
		iv_icon_menu = (ImageView) findViewById(R.id.iv_icon_menu);
		
		iv_icon_home.setOnClickListener(this);
		iv_icon_menu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_icon_home:
			if (islevel2Show) { 
				Tools.hideView(rl_level2);
				islevel2Show = false;
				
				if (islevel3Show) {
					Tools.hideView(rl_level3, 200);
					islevel3Show = false;
				}
			} else {
				Tools.showView(rl_level2);
				islevel2Show = true;
			}
			break;
		case R.id.iv_icon_menu:
			if (islevel3Show) {
				Tools.hideView(rl_level3);
				islevel3Show = false;
			} else {
				Tools.showView(rl_level3);
				islevel3Show = true;
			}
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_MENU) {
			if (islevel1Show) {
				Tools.hideView(rl_level1);
				islevel1Show = false;
				
				if (islevel2Show) {
					Tools.hideView(rl_level2, 200);
					islevel2Show = false;
					
				}
				
				if (islevel3Show) {
					Tools.hideView(rl_level3, 400);
					islevel3Show = false;
				}
			} else {
				Tools.showView(rl_level1);
				islevel1Show = true;
			}
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}

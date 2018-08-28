package com.example.videoplayer2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.VideoView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //获的VideoView
        VideoView vv = (VideoView) findViewById(R.id.vv);
        //获取本地视屏
        vv.setVideoPath("sdcard/b3.3gp");
        //获取网络视屏，只支持http
        //vv.setVideoURI(uri);
        
        //直接播放。如果是网络视频。可以设置一个准备侦听
        vv.start();
    }    
}

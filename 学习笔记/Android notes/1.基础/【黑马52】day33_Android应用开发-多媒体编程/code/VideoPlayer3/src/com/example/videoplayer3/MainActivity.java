package com.example.videoplayer3;

import com.example.videoplayer3.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //检测是否支持vitamio
        if (!LibsChecker.checkVitamioLibs(this)) {return;}
        
        //获的VideoView
        VideoView vv = (VideoView) findViewById(R.id.vv);
        //获取本地视屏
        vv.setVideoPath("sdcard/b3.3gp");
        //获取网络视屏，支持很多协议
        //vv.setVideoURI(uri);
        
        //直接播放。如果是网络视频。可以设置一个准备侦听
        vv.start();
        
        //增加一个默认的播放控件（有进度条，暂停，播放等...）
        vv.setMediaController(new MediaController(this));
    }    
}

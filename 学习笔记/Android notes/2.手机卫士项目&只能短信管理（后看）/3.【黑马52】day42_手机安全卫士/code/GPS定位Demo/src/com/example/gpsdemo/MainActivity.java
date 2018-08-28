package com.example.gpsdemo;

import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

/**
 * GPS定位的demo 
 * 需要权限：
 * <uses-permissionandroid:name="android.permission.ACCESS_FINE_LOCATION"/> ： 精准位置（Gps需要的权限）
 * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/> ：
 * 模糊位置（不精准，大概。网络定位和其他定位需要的权限） 
 * <uses-permissionandroid:name="android.permission.ACCESS_MOCK_LOCATION"/> ：模拟位置（模拟器可以模拟经纬度坐标）
 * 
 * 
 * 得到经纬度可以在：谷歌地图上查找位置(纬度,经度)
 * 
 * 如果实在国内，经纬度会被经过加偏处理，所以在查询的时候，会和实际位置偏移一段距离。偏移到的位置就是火星坐标
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {

	private TextView tvLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvLocation = (TextView) findViewById(R.id.tv_location);
		
		// 获取系统的定位服务
		LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		// 获取所有位置提供者（所有可用的位置服务，如gps，网络定位..）
		List<String> allProviders = lm.getAllProviders();
		Log.e("debug", allProviders.toString());

		MyLocationListener listener = new MyLocationListener();
		/*
		 * NETWORK_PROVIDER：网络定位 
		 * PASSIVE_PROVIDER： 如果有其他app获取过经纬度。我们就直接拿他获取过的经纬度
		 * GPS_PROVIDER：gps定位
		 * 
		 * minTime：最短更新时间 
		 * minDistance：最短更新距离
		 */
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
	}

	class MyLocationListener implements LocationListener {

		/**
		 * 位置发生变化
		 */
		public void onLocationChanged(Location location) {
			String j = "经度：" + location.getLongitude();
			String w = "纬度：" + location.getLatitude();
			// 精确度几米内
			String accuracy = "精确度: " + location.getAccuracy();
			String altitude = "海拔: " + location.getAltitude();
			
			tvLocation.setText(j + "\n" + w + "\n" + accuracy + "\n" + altitude);
		}

		/**
		 * 状态发生变化。（可以获取位置，无法获取位置）
		 */
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		/**
		 * 用户打开gps
		 */
		public void onProviderEnabled(String provider) {

		}

		/**
		 * 用户关闭gps
		 */
		public void onProviderDisabled(String provider) {

		}

	}
}

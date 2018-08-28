package com.example.multithreaddownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class MainActivity extends Activity {
	
	static int threadCount = 3; //下载线程的数量
	static int finishedThread = 0; // 记录有多少条线程完成下载
	static String name = "QQPlayer.exe";
	static String path = "http://192.168.1.110:8080/Android/" + name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	Thread t = new Thread(){
    		public void run() {
    			/*
    			 *  1. 第一次http请求，是要获得下载文件的信息：文件大小（length）
    			 *  然后计算出需要的信息：
    			 *  	每个线程下载的大小：size = length / threadCount
    			 *  	每个线程下载的开始位置：startIndex = id * size
    			 *  	每个线程下载的结束位置：endIndex = (id + 1) * size - 1
    			 *  	最后一个线程下载的结束位置：length - 1， 得到文件最后一个字节的下标
    			 */
    			try {
    				HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
    				conn.setRequestMethod("GET");
    				conn.setReadTimeout(5000);
    				conn.setConnectTimeout(5000);
    				
    				if(conn.getResponseCode() == 200) {
    					// 2. 得到文件的总字节
    					int length = conn.getContentLength();
    					int size = length / threadCount; // 计算出每个线程的下载数
    					
    					// 3. 在本地为下载的创建一个与下载文件相同大小相同名字的文件，占好空间
    					RandomAccessFile raf = new RandomAccessFile(new File(Environment.getExternalStorageDirectory(), name), "rwd");
    					raf.setLength(length); // 设置本地文件的大小与下载文件一样
    					raf.close();
    					
    					// 4. 有多少个线程就开多少个线程， i当做线程id
    					for(int i = 0; i < threadCount; i++) {
    						int startIndex = i * size; // 每个线程下载的开始位置
    						int endIndex = (i + 1) * size - 1; // 每个线程下载的借宿位置
    						// 如果是最后一个线程，那么剩下的字节全部由它包
    						if(i == threadCount -1) {
    							endIndex = length - 1;
    						}
    						
    						// 5. 开启线程，并分配下载的开始位置和结束位置，和线程id
    						new DownLoadThread(startIndex, endIndex, i).start();
    					}
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	};
    	
    	t.start();
    }
    
 // 下载线程，每个下载线程都有自己负责下载的区间
    class DownLoadThread extends Thread {
    	
    	int startIndex;
    	int endIndex;
    	int threadId;
    		
    	public DownLoadThread(int startIndex, int endIndex, int threadId) {
    		super();
    		this.startIndex = startIndex;
    		this.endIndex = endIndex;
    		this.threadId = threadId;
    	}

    	@Override
    	public void run() {
    		
    		File file = new File(Environment.getExternalStorageDirectory(), threadId + ".txt"); //记录改线程下载位置的临时文件
    		InputStream is = null; // 服务器返回的数据流
    		BufferedReader br = null; // 读取记录下载位置临时文件的流
    			
    		// 6. 第二次http请求，就是要下载文件了
    		try {
    			
    			// 8. 如果记录上次各线程下载进度的临时文件存在，就从中取出上次下载的进度。本次下载开始位置 = 上次下载的结束位置（上次下载的进度）
    			if(file.exists()) { 
    				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    				startIndex = Integer.parseInt(br.readLine());
    				br.close(); // 必须先关闭，否则文件会删不掉
    			}
System.out.println(threadId + "下载的位置：" + startIndex + "-" + endIndex);			
    			
    			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
    			conn.setRequestMethod("GET");
    			conn.setReadTimeout(5000);
    			conn.setConnectTimeout(5000);
    			conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex); // 设置本次http请求所请求的数据的区间
    					
    			//206 表示部分内容
    			if(conn.getResponseCode() == 206) {
    				is = conn.getInputStream(); // 拿到部分数据
    				RandomAccessFile raf = new RandomAccessFile(new File(Environment.getExternalStorageDirectory(), name), "rwd"); // 读取到的文件保存的位置
    				raf.seek(startIndex); // 设置文件开始写入的位置
    				
    				byte[] b = new byte[1024];
    				int len = 0; // 读取到的字节数
    				int total = 0; // 记录下载的总字节数
    				while((len = is.read(b)) != -1) {
    					raf.write(b, 0, len); // 从b中写入数据，0下标开始写，写到len下标
    					total += len; // 本次循环读取到的字节数，加到总字节数中
    					
    					// 7. 同步把当前本线程下载的进度写入临时文件中，当前下载进度 = 当前读取到的总字节数 + 本次下载开始位置，下次下载开始位置=当前下载进度
    					RandomAccessFile indexFile = new RandomAccessFile(file, "rwd"); // 当前下载进度的临时文件
    					indexFile.write((startIndex + total + "").getBytes());
    					indexFile.close();
    					
System.out.println(threadId + "下载了：" + total);
    				}
    				raf.close();
    				finishedThread++; // 如果有一条线程下载完毕，就加1
    				
    				// 9. 删除记录各线程下载进度的临时文件
    				synchronized (this) { //线程同步
    					if(finishedThread == threadCount) { // 如果三条线程都完成了下载
    						for(int i = 0; i < threadCount; i++) {
    							File f = new File(Environment.getExternalStorageDirectory(), i + ".txt");
    							f.delete();
    						}
    						finishedThread = 0;
    					}
    				}
    				
System.out.println("第" + threadId + "个线程下载完了，共下载了" + total);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				if(is != null) is.close();
    				//if(br != null) br.close();
    			} catch (Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    	}
    }

}

package com.example.multithreaddownload4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static int threadCount = 3; //下载线程的数量
	static int finishedThread = 0; // 记录有多少条线程完成下载
	static String name = "EditPlus3.rar";
	static String path = "http://192.168.1.110:8080/Android/" + name;
	static int curentProgress; // 记录当前下载进度，显示在进度条
	
	static ProgressBar pb; // 进度条组件
	static TextView tv = null; // 显示文本进度的控件
	
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch(msg.what) {
			case 1:
				//// 计算出当前下载了百分之几。如果文件过大int类型会显示不正常，所以使用long
				tv.setText((long)(pb.getProgress() * 100 / pb.getMax()) + "%");
				break;
			case 2:
				//下载完之后会出现停在99%的情况，实际是下完了。强制设置为100
				tv.setText(100 + "%");
				break;
			}
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        pb = (ProgressBar) findViewById(R.id.pb);
        tv = (TextView) findViewById(R.id.tv);
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
    					
    					// 设置进度条的总大小
    					pb.setMax(length);
    					
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
    		
    		File file = new File(Environment.getExternalStorageDirectory(), threadId + ".txt"); //记录该线程下载位置的临时文件
    		InputStream is = null; // 服务器返回的数据流
    		BufferedReader br = null; // 读取记录下载位置临时文件的流
    		
    		// 6. 第二次http请求，就是要下载文件了
    		try {
    			
    			// 8. 如果记录上次各线程下载进度的临时文件存在，就从中取出上次下载的进度。本次下载开始位置 = 上次下载的结束位置（上次下载的进度）
    			if(file.exists()) { 
    				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    				startIndex = Integer.parseInt(br.readLine()); //本次下载的开始位置
    				br.close(); // 必须先关闭，否则文件会删不掉    				    				
    			}

				//如果三个线程的总下载进度文件存在
				File cpFile = new File(Environment.getExternalStorageDirectory(), "curentProgress.txt");
				if(cpFile.exists()) {
					BufferedReader br1 = new BufferedReader(new FileReader(cpFile));
					//取出上次三个线程总共下载的总字节
					curentProgress = Integer.parseInt(br1.readLine());
					br1.close();
					
					//往进度条上设置
					pb.setProgress(curentProgress);
					//通知主线程刷新文本进度
					handler.sendEmptyMessage(1);
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
    					RandomAccessFile indexFile = new RandomAccessFile(file, "rwd");
    					indexFile.write((startIndex + total + "").getBytes());
    					indexFile.close();
    					
    					// 设置进度条的当前进度
    					curentProgress += len;
						//这个是三个线程合起来，下载了多少的进度文件。显示在进度条的
						RandomAccessFile r = new RandomAccessFile(cpFile, "rwd");
						r.write((curentProgress+"").getBytes());
						r.close();
    					pb.setProgress(curentProgress);
    					
    					//通知主线程刷新文本进度
    					handler.sendEmptyMessage(1);
    					
System.out.println(threadId + "下载了：" + total);
    				}
    				raf.close();
    				finishedThread++; // 如果有一条线程下载完毕，就加1
    				
    				// 9. 删除记录各线程下载进度的临时文件，和三条线程总进度的临时文件
    				synchronized (this) { //线程同步
    					if(finishedThread == threadCount) { // 如果三条线程都完成了下载
    						for(int i = 0; i < threadCount; i++) {
    							//删除各线程下载进度的临时文件
    							File f = new File(Environment.getExternalStorageDirectory(), i + ".txt");
    							f.delete();
System.out.println(i + "删成功");
    						}
    						//删除三条总下载进度的临时文件
    						cpFile.delete();
System.out.println(cpFile.getName() + "删除成功");
    						
    						//通知主线程刷新文本进度, 避免出现99%的现象，强制显示文本进度为100%
    						handler.sendEmptyMessage(2);
    					
    						finishedThread = 0;
    						curentProgress = 0;
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

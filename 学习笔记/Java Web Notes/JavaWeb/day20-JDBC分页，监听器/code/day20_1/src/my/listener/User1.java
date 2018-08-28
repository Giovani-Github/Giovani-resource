package my.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User1 implements HttpSessionBindingListener{
	/*
	 * javabean被绑定到session中时调用
	 * */
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("我在session里面了");
	}

	/*
	 * javabean从session解绑是调用
	 * */
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("我从session中出来了");
	}
}

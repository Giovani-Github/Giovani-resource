package my.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class User2 implements HttpSessionActivationListener, Serializable {

	/*
	 * javabean与session一起被钝化时调用
	 * */
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("我和session一起钝化了");
	}
	
	/*
	 * javabean与session一起呗活化时调用
	 * */
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("我和session活化了");
	}
}

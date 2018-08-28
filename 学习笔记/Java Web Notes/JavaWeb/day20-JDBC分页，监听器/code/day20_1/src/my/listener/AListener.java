package my.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/*
 * ServletConetext的生命周期监听器
 * 
 * */
public class AListener implements ServletContextListener{
	/*
	 * ServletContext被创建时调用
	 * */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("被创建");	
	}
	
	/*
	 * ServletContext被销毁时调用
	 * */
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("被销毁");
	}
}

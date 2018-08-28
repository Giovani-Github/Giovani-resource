package my.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class AListener
 *
 */
public class AListener implements ServletContextListener {

	/*
	 * 创建ServletContext时会调用
	 * */
    public void contextInitialized(ServletContextEvent event) {
    	/*
    	 * 1.创建一个map，用来保存key：ip，value：次数
    	 * 2.保存map到servletContext中 
    	 * */
    	
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	event.getServletContext().setAttribute("map", map);
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}

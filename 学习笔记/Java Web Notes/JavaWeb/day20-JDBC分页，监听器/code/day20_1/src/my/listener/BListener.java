package my.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Application Lifecycle Listener implementation class BListener
 *
 */
public class BListener implements ServletContextAttributeListener {
	/*
	 * 向ServletContext添加属性时被调用
	 * */
    public void attributeAdded(ServletContextAttributeEvent scae) {
    	String name = scae.getName();
    	Object value = scae.getValue();
    	System.out.println("添加了名为：" + name + "，值为：" + value + "，的属性！");
    }

    /*
     * 修改属性时被调用6·++来看我
     * */
    public void attributeReplaced(ServletContextAttributeEvent scae) {
    	String name = scae.getName();
    	Object preValue = scae.getValue();//获取到的是修改前的属性值
    	Object laterValue = scae.getServletContext().getAttribute(name);//获取修改之后的属性
    	
    	System.out.println("修改的属性名为：" + name + "，老值为：" + preValue + "，新值为：" + laterValue );
    }

    /*
     * 删除属性时贝被调用
     * */
    public void attributeRemoved(ServletContextAttributeEvent scae) {
    	String name = scae.getName();
    	Object value = scae.getValue();
    	System.out.println("删除名为：" + name + "，值为：" + value + "，的属性");

    }
}
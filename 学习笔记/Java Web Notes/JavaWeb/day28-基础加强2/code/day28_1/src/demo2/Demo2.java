package demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

/*
 * 因为ManWaiter类的服务方法太简单，需要对它的服务方法进行增强。前面加您好，后面加再见
 * 
 * 这个例子实现了：目标对象可以随意更改。增强不能改
 * */
public class Demo2 {
	@Test
	public void fun() {
		//要增强的目标对象
		Waiter manWaiter = new ManWaiter();
		
		//三大参数
		ClassLoader loader = this.getClass().getClassLoader();
		Class[] interfaces = {Waiter.class};//代理对象所实现的接口
		InvocationHandler h = new WaiterInvocationHandler(manWaiter);//增强的内容，需要目标对象
	
		//增强后的代理对象
		Waiter waiter = (Waiter)Proxy.newProxyInstance(loader, interfaces, h);
		
		waiter.serve();
	}
}

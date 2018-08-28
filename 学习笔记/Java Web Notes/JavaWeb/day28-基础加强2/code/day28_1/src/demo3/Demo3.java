package demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

/*
 * 因为ManWaiter类的服务方法太简单，需要对它的服务方法进行增强。前面加您好，后面加再见
 * 
 * 这个例子实现了：目标对象可以随意更改。增强可以随意更改
 * */
public class Demo3 {
	@Test
	public void fun() {
		ProxyFactory factory = new ProxyFactory();
		
		//给出目标对象
		Waiter manWaiter = new ManWaiter();
		factory.setObject(manWaiter);
		//给出前置增强
		factory.setBeforeAdvice(new BeforeAdvice() {
			public void before() {
				System.out.println("你好!");
			}
		});
		//给出后置增强
		factory.setAfterAdvice(new AfterAdvice() {
			public void after() {
				System.out.println("再见！");
			}
		});
		
		//得到增强后的代理对象
		Waiter waiter = (Waiter)factory.createProxy();
		
		waiter.serve();
	}
}

package demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class Demo1 {
	@Test
	public void fun1() {
		/*
		 * 三大参数
		 * 1. ClassLoader
		 * 方法需要动态生成一个类，这个类实现了A、B接口，然后创建这个类的对象！
		 * 需要生成一个类，这个类也需要加载到方法区中，谁来加载，当然是ClassLoader！！！
		 * 
		 * 2. Class[] interfaces
		 * 它是要实现的接口们
		 * 
		 * 3. InvocationHandler
		 * 它是调用处理器
		 * 代理对象的实现的所有接口中的方法，内容都是调用InvocationHandler的invoke()方法。
		 */
		ClassLoader loader = this.getClass().getClassLoader();//类加载器
		Class[] cs = {A.class, B.class};//代理对象所实现的接口
		InvocationHandler h = new InvocationHandler() {//调用处理器
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				System.out.println("除了本地方法，代理对象的所有方法都会调用这个方法");
				return "xxx";
			}
		};
		Object o = Proxy.newProxyInstance(loader, cs, h);
		
		A a = (A)o;
		B b = (B)o;
		
		a.a();
		b.b();
		
		Object result = a.aa("hello", 100);
		System.out.println(result);
	}
}

interface A {
	public void a();
	public Object aa(Object o, int i);
}

interface B {
	public void b();
}
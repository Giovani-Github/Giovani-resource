package demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WaiterInvocationHandler implements InvocationHandler {
	private Waiter waiter;
	
	public WaiterInvocationHandler(Waiter waiter) {
		this.waiter = waiter;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("你好！");
		waiter.serve();//调用目标对象的目标方法。即，要目标对象需要进行增强的方法
		System.out.println("再见！");
		return null;
	}

}

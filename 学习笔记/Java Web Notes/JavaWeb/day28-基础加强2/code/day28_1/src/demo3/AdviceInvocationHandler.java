package demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AdviceInvocationHandler implements InvocationHandler {
	private Object object;//目标对象
	private BeforeAdvice beforeAdvice;//前置增强
	private AfterAdvice afterAdvice;//后置增强
	
	public AdviceInvocationHandler(Object object, BeforeAdvice beforeAdvice,
			AfterAdvice afterAdvice) {
		this.object = object;
		this.beforeAdvice = beforeAdvice;
		this.afterAdvice = afterAdvice;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(beforeAdvice != null) {
			beforeAdvice.before();
		}
		
		Object o = method.invoke(object, args);//调用目标对象的目标方法。即：目标对象要进行增强的方法
		
		if(afterAdvice != null) {
			afterAdvice.after();
		}
		
		//目标方法返回什么，就返回什么
		return o;
	}

}

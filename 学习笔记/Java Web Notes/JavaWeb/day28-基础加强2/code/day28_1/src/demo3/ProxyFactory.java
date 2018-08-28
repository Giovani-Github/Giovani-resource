package demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//代理对象工厂类，进行增强和目标对象的拼凑。得到代理对象
public class ProxyFactory {
	private Object object;//目标对象
	private BeforeAdvice beforeAdvice;//前置增强
	private AfterAdvice afterAdvice;//后置增强
	
	//创建代理对象
	public Object createProxy() {
		//三大参数
		ClassLoader loader = this.getClass().getClassLoader();
		Class[] interfaces = object.getClass().getInterfaces();//得到目标对象所实现的所有接口
		//对目标对象的目标方法进行增强，需要给出目标对象，前置增强，后置增强
		InvocationHandler h = new AdviceInvocationHandler(object, beforeAdvice, afterAdvice);	
		
		//增强后的代理对象
		Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public BeforeAdvice getBeforeAdvice() {
		return beforeAdvice;
	}
	public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
		this.beforeAdvice = beforeAdvice;
	}
	public AfterAdvice getAfterAdvice() {
		return afterAdvice;
	}
	public void setAfterAdvice(AfterAdvice afterAdvice) {
		this.afterAdvice = afterAdvice;
	}
	
	
}

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import org.junit.Test;


public class Demo4 {
	@Test
	public void fun1() {
		/*
		 * 1. 得到作用目标
		 */
		Class<A> c = A.class;
		/*
		 * 2. 获取指定类型的注解
		 */
		MyAnno1 myAnno1 = c.getAnnotation(MyAnno1.class);
		System.out.println(myAnno1.name() + ", " 
					+ myAnno1.age() + ", " + myAnno1.sex());
	}
	
	@Test
	public void fun2() throws SecurityException, NoSuchMethodException {
		/*
		 * 1. 得到作用目标
		 */
		Class<A> c = A.class;
		Method method = c.getMethod("fun1");
		
		
		/*
		 * 2. 获取指定类型的注解
		 */
		MyAnno1 myAnno1 = method.getAnnotation(MyAnno1.class);
		System.out.println(myAnno1.name() + ", " 
					+ myAnno1.age() + ", " + myAnno1.sex());
	}
}

@MyAnno1(name="A类", age=1, sex="男")
class A {
	@MyAnno1(name="fun1方法", age=2, sex="女")
	public void fun1() {
		
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno1 {
	String name();
	int age();
	String sex();
}



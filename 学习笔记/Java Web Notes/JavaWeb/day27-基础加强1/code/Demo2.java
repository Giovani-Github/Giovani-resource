package cn.itcast.demo2;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Demo2 {
	@Test
	public void fun1() {
		Object[] objs = new Object[10];
		List list = new ArrayList();
		
		String[] strs = new String[10];
		List<String> strList = new ArrayList<String>();
		
		Object[] objArray = new String[10];
		objArray[0] = new Integer(100);//ArrayStoreException
//		List<Object> objList = new ArrayList<String>();
//		objList.add(new Integer(100));
		/*
		 * 泛型引用和创建两端，给出的泛型变量必须相同！
		 */
	}
	
	public void fun2() {
		List<Integer> integerList = new ArrayList<Integer>();
		print(integerList);
		
		List<String> stringList = new ArrayList<String>();
		print(stringList);
	}
	
	/*
	 * 其中的?就是通配符
	 * 通配符只能出现在左边！即不能在new时使用通配符！！！
	 * List<?> list = new ArrayList<String>();
	 */
	/*
	 * ?它表示一个不确定的类型，它的值会在调用时确定下来
	 */
	public void print(List<?> list) {
		/*
		 * 当使用通配符时，对泛型类中的参数为泛型的方法起到了副作用，不能再使用！
		 */
//		list.add("hello");
		/*
		 * 当使用通配符时，泛型类中返回值为泛型的方法，也作废了！
		 */
		Object s = list.get(0);
		/*
		 * 通配符好处：可以使泛型类型更加通用！尤其是在方法调用时形参使用通配符！
		 */
	}
	
	public void fun3() {
		List<Integer> intList = new ArrayList<Integer>();
		print1(intList);
		
		List<Long> longList = new ArrayList<Long>();
		print1(longList);
	}
	
	/*
	 * 给通配符添加了限定：
	 *   只能传递Number或其子类型
	 *   子类通配符对通用性产生了影响，但使用形参更加灵活
	 */
	public void print1(List<? extends Number> list) {
		/*
		 * 参数为泛型的方法还是不能使用
		 */
//		list.add(new Integer(100));
		/*
		 * 返回值为泛型的方法可用了！
		 */
		Number number = list.get(0);
	}
	
	public void fun4() {
		List<Integer> intList = new ArrayList<Integer>();
		print2(intList);
		
		List<Number> numberList = new ArrayList<Number>();
		print2(numberList);
		
		List<Object> objList = new ArrayList<Object>();
		print2(objList);
	}
	
	/*
	 * 给通配符添加了限定
	 *   只能传递Integer类型，或其父类型
	 */
	public void print2(List<? super Integer> list) {
		/*
		 * 参数为泛型的方法可以使用了
		 */
		list.add(new Integer(100));
		/*
		 * 返回值为泛型的方法，还是不能使用
		 */
		Object obj =  list.get(0);
	}
}

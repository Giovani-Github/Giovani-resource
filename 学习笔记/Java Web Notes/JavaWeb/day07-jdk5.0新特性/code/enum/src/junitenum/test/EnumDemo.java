package junitenum.test;

import org.junit.Test;

public class EnumDemo {
	//知道枚举常量对象，获取下标和名称
	@Test
	public void demo1() {
		Demo d1 = Demo.A;
		String name = d1.name();
		int idx = d1.ordinal();
		System.out.println(name + " " + idx);
	}
	
	//知道枚举下标，获取枚举常量和名称
	@Test
	public void demo2() {
		int idx = 1;
		//获取整个枚举对象(枚举常量就是枚举类的实例)的数组
		Demo[] demos = Demo.values();
		//根据下面获取枚举常量
		Demo d2 = demos[idx];
		System.out.println(d2.name());
	}
	
	//知道枚举名称，获取枚举常量对象和下标
	@Test
	public void demo3() {
		String name = "C";
		//根据名称获取枚举常量
		Demo d3 = Demo.valueOf(name);
		//使用枚举常量，获取下标
		int idx = d3.ordinal();
		System.out.println(idx);
		
	}
	
}

enum Demo {
	A, B, C;
}

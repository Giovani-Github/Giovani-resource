/*设计模式：解决某一类问题最行之有效的方法。
java中23种设计模式：
单例设计模式：解决一个类在内存只存在一个对象。


想要保证对象唯一。
1，为了避免其他程序过多建立该类对象。先禁止其他程序建立该类对象
2，还为了让其他程序可以访问到该类对象，只好在本类中，自定义一个对象。
3，为了方便其他程序对自定义对象的访问，可以对外提供一些访问方式。

这三部怎么用代码体现呢？
1，将构造函数私有化。
2，在类中创建一个本类对象。
3，提供一个方法可以获取到该对象。



对于事物该怎么描述，还怎么描述。
当需要将该事物的对象保证在内存中唯一时，就将以上的三步加上即可。
*/

/*
单例模式：
饿汉式
*/
class Single {
	private static Single s = new Single();

	private Single() {}
	
	public static Single getInstance() {
		return s;
	}
}

/*
懒汉式
*/
class Single2 {
	private static Single2 s = null;
	
	private Single2() {}

	public static Single2 getInstance() {
		if(s == null) {
			s= new Single2();
		}
		return s;
	}
}

/*
懒汉式在不安全性的解决方法一：
*/
class Single3 {
	private static Single3 s = null;

	private Single3() {}

	public static synchronized Single3 getInstance() {
		if(s == null) {
			s = new Single3();
		}
		return s;
	}
}

/*
懒汉式解决方法二：
*/
class Single4 {
	private static Single4 s = null;

	private Single4() {}

	public static Single4 getInstance() {
		if(s == null) {
			synchronized(Single4.class) {

				if(s == null) {
					s = new Single4();
				}
			}
		}
		return s;
	}
}
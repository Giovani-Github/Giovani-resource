/*
使用LinkedList模拟堆栈或队列数据结构

堆栈：先进后出

队列：先进先出
*/

import java.util.*;

public class Test {
	public static void main(String[] arge) {
		MyList ml = new MyList();
		
		ml.Myadd("java01");	
		ml.Myadd("java02");	
		ml.Myadd("java03");	
		ml.Myadd("java04");
		
		while(!ml.IfNull()) {
			sop(ml.MyGet());
		}

	}

	public static void sop(Object obj) {
		System.out.println(obj);
	}
}


class MyList {
	private LinkedList link;

	MyList() {
		link = new LinkedList();
	}

	public void Myadd(Object obj) {
		link.addFirst(obj);
	}

	public Object MyGet() {
		return link.removeLast();//改成removeFirst就是先进后出
	}

	public boolean IfNull() {
		return link.isEmpty();
	}
}
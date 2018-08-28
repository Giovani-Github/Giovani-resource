import java.util.*;

public class Test {
	public static void main(String[] arge) {
		CollectionDemo();
	}

	public static void CollectionDemo() {
		ArrayList al = new ArrayList();
		ArrayList a2 = new ArrayList();

		al.add("QW_01");
		al.add("QW_02");
		al.add("QW_03");
		al.add("QW_04");

		a2.addAll(al);//将a1的元素存入a2中

		sop("al_size:" + al.size() + "	al:" + al);
		sop("a2_size:" + a2.size() + "	a2:" + a2);

		

		for(Iterator ir = al.iterator();/*迭代器，取出容器元素的方法，返回一个取出类，迭代器就是取出*/ ir.hasNext(); ) {
			sop(ir.next());
		}
	}

	public static void sop(Object obj) {
		System.out.println(obj);
	}
}
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

		a2.addAll(al);//��a1��Ԫ�ش���a2��

		sop("al_size:" + al.size() + "	al:" + al);
		sop("a2_size:" + a2.size() + "	a2:" + a2);

		

		for(Iterator ir = al.iterator();/*��������ȡ������Ԫ�صķ���������һ��ȡ���࣬����������ȡ��*/ ir.hasNext(); ) {
			sop(ir.next());
		}
	}

	public static void sop(Object obj) {
		System.out.println(obj);
	}
}
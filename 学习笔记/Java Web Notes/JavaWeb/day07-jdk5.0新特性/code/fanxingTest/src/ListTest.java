import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class ListTest {
	@Test
	public void listTest() {
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		
		//ʹ��forѭ��ȡ��list���������Ԫ��
		for(int i=0; i<list.size(); i++) {
			String list1 = list.get(i);
			System.out.println(list1);
		}
		
		System.out.println("=======================");
		
		//ʹ����ǿforȡ��listi���������Ԫ��
		for (String list2 : list) {
			System.out.println(list2);
		}
		
		System.out.println("=======================");
		
		//ʹ�õ�������ȥlist�����е�Ԫ��
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String list3 = it.next();
			System.out.println(list3);
		}
	}
}

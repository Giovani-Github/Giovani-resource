import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;


public class SetTest {
	@Test
	public void setTest() {
		Set<String> set = new HashSet<String>();
		set.add("aa");
		set.add("bb");
		set.add("cc");
		
		//ʹ����ǿforȡ��set�е�Ԫ��
		for (String s1 : set) {
			System.out.println(s1);
		}
		
		System.out.println("=======================");
		
		//ʹ�õ�����ȡ��set��Ԫ��
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String s2 = it.next();
			System.out.println(s2);
		}
	}
}

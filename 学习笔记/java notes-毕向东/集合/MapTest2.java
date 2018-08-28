/*
���󣺶�ѧ����������������������

��Ϊ�������Լ�ֵ����ʽ���ڵġ�
����Ҫʹ�ÿ��������Map���ϡ�TreeMap��
*/


import java.util.*;
public class MapTest2 {
	public static void main(String[] arge) {
		TreeMap<Student, String> tm = new TreeMap<Student, String>(new StuNameComparator());

		tm.put(new Student("li01", 12), "�㶫");		
		tm.put(new Student("li01", 2), "�㶫");	
		tm.put(new Student("li02", 11), "����");
		tm.put(new Student("li03", 10), "����");
		
		Set<Map.Entry<Student, String>> st = tm.entrySet();

		for(Iterator<Map.Entry<Student, String>> it = st.iterator(); it.hasNext(); ) {
			Map.Entry<Student, String> me = it.next();

			Student key = me.getKey();
			String value = me.getValue();

			System.out.println("key: " + key + "  " + "value:" + value);
		}
	}
}


class StuNameComparator implements Comparator<Student> {
	public int compare(Student st, Student st1) {
		int num = st.getName().compareTo(st1.getName());
		
		if(num == 0)
			return new Integer(st.getAge()).compareTo(new Integer(st1.getAge()));
		return num;
	}
}
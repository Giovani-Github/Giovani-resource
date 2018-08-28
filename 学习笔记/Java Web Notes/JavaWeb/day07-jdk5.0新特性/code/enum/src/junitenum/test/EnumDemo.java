package junitenum.test;

import org.junit.Test;

public class EnumDemo {
	//֪��ö�ٳ������󣬻�ȡ�±������
	@Test
	public void demo1() {
		Demo d1 = Demo.A;
		String name = d1.name();
		int idx = d1.ordinal();
		System.out.println(name + " " + idx);
	}
	
	//֪��ö���±꣬��ȡö�ٳ���������
	@Test
	public void demo2() {
		int idx = 1;
		//��ȡ����ö�ٶ���(ö�ٳ�������ö�����ʵ��)������
		Demo[] demos = Demo.values();
		//���������ȡö�ٳ���
		Demo d2 = demos[idx];
		System.out.println(d2.name());
	}
	
	//֪��ö�����ƣ���ȡö�ٳ���������±�
	@Test
	public void demo3() {
		String name = "C";
		//�������ƻ�ȡö�ٳ���
		Demo d3 = Demo.valueOf(name);
		//ʹ��ö�ٳ�������ȡ�±�
		int idx = d3.ordinal();
		System.out.println(idx);
		
	}
	
}

enum Demo {
	A, B, C;
}

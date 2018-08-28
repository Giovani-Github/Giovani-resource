/*
��һ��Բ�κͳ�����
�����Ի�ȡ������������������ַǷ���ֵ����Ϊ��ȡ����������⡣
����ͨ���쳣����ʾ��
���жԳ�����л������

*/


class Round {
	private double r;
	public static final double PI = 3.14;
	private int r2;

	Round(double r) {
		if(r <= 0)
			throw new VException();

		this.r = r;
		r2 = (int)r;
	}

	Round(int r2) {
		if(r2 <= 0)
			throw new VException();

		this.r2 = r2;
		r = r2;
	}

	public double area() {
		return PI * (r * r);
	}
}


class ARectangle {
	private double a;
	private double h;
	private int a2;
	private int h2;

	ARectangle(double a, double h) {
		if(a <= 0 || h <= 0)
			throw new VException();

		this.a = a;
		a2 = (int)a;
		this.h = h;
		h2 = (int)h;
	}

	ARectangle(int a, int h) {
		if(a <= 0 || h <= 0)
			throw new VException();

		this.a = a;
		a2 = a;
		this.h = h;
		h2 = h;
	}

	public double area() {

		return h * a;
	}
}


class VException extends RuntimeException {
	VException() {
		super("�Ƿ�");
	}
}


public class Mian {
	public static void main(String[] arge) {
		ARectangle ar = new ARectangle(1, 12);
		System.out.println(ar.area());
		

		Round rd = new Round(2);
		System.out.println(rd.area());
	
	}
}
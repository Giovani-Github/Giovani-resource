import java.util.*;
public class Lottery {
	static Print p = new Print();

	static Random r = new Random();
	static Scanner s = new Scanner(System.in);

	static String son = null;
	static int num = 0;
	
	public static void betting(int a) {
		int[] arr = new int[7];
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < arr.length; j++) {
				arr[j] = r.nextInt(35);
			}
	
		p.print2();
		p.print3(arr);
		}
	}

	public static void main(String[] arge) {
		p.print();

		do
		{
			p.print5();
			num = s.nextInt();
			p.print4();

			betting(num);


			System.out.println("�����Ƿ������ѡ���ǣ�y����n��");
			son = s.next();
		}
		while (son.equals("y") || son.equals("Y"));

		p.print6();
	}
}

class Print {
	public static void print() {
		System.out.println("��ӭ����������磡");
		System.out.println("��������Ϊ����ѡ����......");
	}

	public static void print2() {
		System.out.println("��ѡ�������£�");
	}

	public static void print3(int[] arr) {
		System.out.print("����");
		for(int i = 0; i < arr.length-1; i++) {
			if(i != arr.length-2) {
				System.out.print(arr[i] + "  ");
			} else {
				System.out.println(arr[i]);
			}
		}

		System.out.print("����");
		System.out.println(arr[arr.length-1] + "\n" + "ף����ˣ�");
		print4();
	}

	public static void print4() {
		System.out.println("*** *** *** *** *** *** *** ***");
	}

	public static void print5() {
		System.out.println("������ҪͶ����ע��");
	}

	public static void print6() {
		System.out.println("��ӭ�´�������лл...�ټ���");
	}
}
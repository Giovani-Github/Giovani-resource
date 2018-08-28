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


			System.out.println("请问是否继续机选？是（y）否（n）");
			son = s.next();
		}
		while (son.equals("y") || son.equals("Y"));

		p.print6();
	}
}

class Print {
	public static void print() {
		System.out.println("欢迎来到体彩世界！");
		System.out.println("请让我们为您机选号码......");
	}

	public static void print2() {
		System.out.println("机选号码如下：");
	}

	public static void print3(int[] arr) {
		System.out.print("红球：");
		for(int i = 0; i < arr.length-1; i++) {
			if(i != arr.length-2) {
				System.out.print(arr[i] + "  ");
			} else {
				System.out.println(arr[i]);
			}
		}

		System.out.print("篮球：");
		System.out.println(arr[arr.length-1] + "\n" + "祝你好运！");
		print4();
	}

	public static void print4() {
		System.out.println("*** *** *** *** *** *** *** ***");
	}

	public static void print5() {
		System.out.println("请问需要投多少注？");
	}

	public static void print6() {
		System.out.println("欢迎下次再来，谢谢...再见！");
	}
}
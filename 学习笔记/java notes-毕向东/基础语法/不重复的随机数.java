import java.util.Random;
public class Test {
	public static void cai(int n) {
		Random r = new Random();

		int[] arr = new int[n];
		arr[0] = r.nextInt(arr.length);

		int count = 0;

		while(count < arr.length-1) {
			int temp = r.nextInt(arr.length);

			boolean b = true;

			for(int i = 0; i < arr.length; i++) {
				if(temp == arr[i]) {
					b = false;
					break;
				}
			}

			if(b) {
				arr[count] = temp;
				count++;
			}
		}

		print(arr);

	}

	public static void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}

	public static void main(String[] arge) {
		cai(11);
	}
}
import java.util.Random;

public class Ran {
	public static void main(String[] arge) {
		Random r = new Random();
		int[] arr = new int[7];

		for(int i = 0; i < arr.length-1; i++) {
			int temp = r.nextInt(34);
			if(temp == 0) {
				temp = 1;
			}

			arr[i] = temp;
		}

		int temp = r.nextInt(17);
		if(temp == 0) {
			temp = 1;
		}
		arr[arr.length-1] = temp;

		for(int i = 0; i < arr.length; i++) {
			if(i != arr.length-1) {
				System.out.print(arr[i] + "  ");
			} else {
				System.out.println();
				System.out.println("ÌØÂë£º" + arr[i]);
			}
		}
	}
}
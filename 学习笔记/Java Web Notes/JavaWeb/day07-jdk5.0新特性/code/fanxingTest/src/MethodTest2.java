import java.util.Arrays;


public class MethodTest2 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		System.out.println(Arrays.toString(arr));
		//��ת��
		revers(arr);
		System.out.println(Arrays.toString(arr));
	}

	//��ת����
	public static void revers(int[] arr) {
		for(int i=0; i<arr.length/2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length-i-1];
			arr[arr.length-i-1] = temp;
		}
	}
}

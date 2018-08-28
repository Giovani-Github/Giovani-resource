import java.util.Arrays;


public class MethodTest {
	public static void main(String[] args) {
		//互换前
		String[] arr = {"aa", "bb", "cc", "dd"};
		System.out.println(Arrays.toString(arr));
		
		//互换后
		String[] arr2 = arrTest(arr, 1, 3);
		System.out.println(Arrays.toString(arr2));
	}
	
	//数组中指定的两个元素互换
	public static <T>T[] arrTest(T[] arr, int s1, int s2) { //传入的arr是Sring类型，所以T就是String
		T type = arr[s1];
		arr[s1] = arr[s2];
		arr[s2] = type;
		
		return arr;
	}
}

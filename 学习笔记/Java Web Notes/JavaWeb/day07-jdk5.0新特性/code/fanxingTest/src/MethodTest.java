import java.util.Arrays;


public class MethodTest {
	public static void main(String[] args) {
		//����ǰ
		String[] arr = {"aa", "bb", "cc", "dd"};
		System.out.println(Arrays.toString(arr));
		
		//������
		String[] arr2 = arrTest(arr, 1, 3);
		System.out.println(Arrays.toString(arr2));
	}
	
	//������ָ��������Ԫ�ػ���
	public static <T>T[] arrTest(T[] arr, int s1, int s2) { //�����arr��Sring���ͣ�����T����String
		T type = arr[s1];
		arr[s1] = arr[s2];
		arr[s2] = type;
		
		return arr;
	}
}

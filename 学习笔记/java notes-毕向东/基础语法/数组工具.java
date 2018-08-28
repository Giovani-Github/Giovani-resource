/**
��������в����Ĺ����࣬��ֵ�����򣬲��ң���ӡ��
@author ������
@version v1.0
*/
public class ArraysTool {
	
	/**
	��ȡ�����е����ֵ
	@param arr ����һ��int���͵�����
	@return ���������е����ֵ
	*/
	public static int getMax(int[] arr) {
		int temp = 0;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] > arr[temp]) {
				temp = i;
			}
		}

		return arr[temp];
	}

	/**
	��ȡ�����е���Сֵ
	@param arr ����һ��int���͵�����
	@return ���������е���Сֵ
	*/
	public static int getMin(int[] arr) {
		int temp = 0;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] < arr[temp]) {
				temp = i;
			}
		}

		return arr[temp];
	}
	
	/**
	ʹ���۰���ң������������Ƿ���ָ��������
	@param arr ����һ��int���͵�����
	@param a Ҫ���ҵ�����
	@return ���Һ����õĽ���������±�
	*/
	public static int binary(int[] arr, int a) {
		int first = 0;
		int tail = arr.length-1;
		int middle = (first+tail)/2;

		while(first < tail) {
			if(a == arr[middle]) {
				return middle;
			} else if(middle > first){
				first = middle + 1;
			} else {
				tail = middle - 1;
			}

			middle = (first+tail)/2;
		}
		return -1;
	}
	
	/**
	��������ð�����򷨽��д�С���������
	@param arr ����һ��int���͵�����
	*/
	public static void sortMaoXiao(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					sortHuan(arr, i, j);
				}
			}
		}
	}

	/**
	��������ð��������дӴ�С������
	@param arr ����һ��int�����͵�����
	*/
	public static void sortMaoDa(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					sortHuan(arr, i, j);
				}
			}
		}
	}

	/**
	��������ѡ��������д�С���������
	@param arr ����һ��int���͵�����
	*/
	public static void sortXuanXiao(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = 0; j < arr.length-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					sortHuan(arr, j, j+1);
				}
			}
		}
	} 
	
	/**
	��������ѡ��������дӴ�С������
	@param arr ����һ��int���͵�����
	*/
	public static void sortXuanDa(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = 0; j < arr.length-1-i; j++) {
				if(arr[j] < arr[j+1]) {
					sortHuan(arr, j, j+1);
				}
			}
		}
	}

	/**
	����ʱ��������������ݽ��л���
	@param arr ����һ��int���͵�����
	@param a Ҫ������
	@param b Ҫ������
	*/
	private static void sortHuan(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	/**
	��������д�ӡ����ʽ��[1, 2, 3, ...]
	@param arr ����һ��int���͵�����
	*/
	public static void print_1(int[] arr) {
		System.out.print("[");
		for(int i = 0; i< arr.length; i++) {
			if(i != arr.length-1) {
				System.out.print(arr[i] + ", ");
			} else {
				System.out.print(arr[i]);
			}
		}
		System.out.print("]");
		System.out.println();
	}

	/**
	��������д�ӡ����ʽ��1, 2, 3, ...
	@param arr ����һ��int���͵�����
	*/
	public static void print_2(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(i != arr.length-1) {
				System.out.print(arr[i] + ",");
			} else {
				System.out.print(arr[i]);
			}
		}

		System.out.println();
	}

	/**
	��������д�ӡ����ʽ:[1], [2], [3], ...
	@param arr ����һ��int���͵�����
	*/
	public static void print_3(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(i != arr.length-1) {
				System.out.print("[" + arr[i] + "], ");
			} else {
				System.out.print("[" + arr[i] + "]");
			}
		}

		System.out.println();
	}
}
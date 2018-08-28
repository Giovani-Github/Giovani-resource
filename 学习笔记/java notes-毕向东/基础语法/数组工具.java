/**
对数组进行操作的工具类，最值，排序，查找，打印。
@author 李庆旺
@version v1.0
*/
public class ArraysTool {
	
	/**
	获取数组中的最大值
	@param arr 接受一个int类型的数组
	@return 返回数组中的最大值
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
	获取数组中的最小值
	@param arr 接受一个int类型的数组
	@return 返回数组中的最小值
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
	使用折半查找，查找数组内是否有指定的数据
	@param arr 接收一个int类型的数组
	@param a 要查找的数据
	@return 查找后所得的结果的数组下标
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
	对数组用冒泡排序法进行从小到大的排序
	@param arr 接收一个int类型的数组
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
	对数组用冒泡排序进行从大到小的排序
	@param arr 接收一个int的类型的数组
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
	对数组用选择排序进行从小到大的排序
	@param arr 接收一个int类型的数组
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
	对数组用选择排序进行从大到小的排序
	@param arr 接收一个int类型的数组
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
	排序时对数组的两个数据进行互换
	@param arr 接收一个int类型的数组
	@param a 要换的数
	@param b 要换的数
	*/
	private static void sortHuan(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	/**
	对数组进行打印，格式：[1, 2, 3, ...]
	@param arr 接收一个int类型的数组
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
	对数组进行打印，格式：1, 2, 3, ...
	@param arr 接收一个int类型的数组
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
	对数组进行打印，格式:[1], [2], [3], ...
	@param arr 接收一个int类型的数组
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
/**
运算工具，包含四则运算，以及最值，绝对值
@author 李庆旺
@version v1.0
*/
public class Test{
	/**
	一个运算器，包含加减乘除
	@param arr 要运算的数
	@param a 运算符号
	@return 运算后的结果
	*/
	public static double operation(double[] arr, String a) {
		double temp = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(a.equals("/")) {
				temp = temp / arr[i];
			} else if(a.equals("*")) {
				temp = temp * arr[i];
			} else if(a.equals("+")) {
				temp = temp + arr[i];
			} else {
				temp = temp - arr[i];
			}
		}
		return temp;
	}

	/**
	获取最大值
	@param arr 操作的数组
	@return 数组中的最大值
	*/
	public static double Max(double[] arr) {
		double temp = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > temp) {
				temp = arr[i];
			}
		}

		return temp;
	}

	/**
	获取最小值
	@param arr 操作的数组
	@return 数组中的最小值
	*/
	public static double Min(double[] arr) {
		double temp = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < temp) {
				temp = arr[i];
			}
		}

		return temp;
	}
}
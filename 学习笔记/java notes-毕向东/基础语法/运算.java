/**
���㹤�ߣ������������㣬�Լ���ֵ������ֵ
@author ������
@version v1.0
*/
public class Test{
	/**
	һ���������������Ӽ��˳�
	@param arr Ҫ�������
	@param a �������
	@return �����Ľ��
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
	��ȡ���ֵ
	@param arr ����������
	@return �����е����ֵ
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
	��ȡ��Сֵ
	@param arr ����������
	@return �����е���Сֵ
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
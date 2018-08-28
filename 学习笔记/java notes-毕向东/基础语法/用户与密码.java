/*
����
1����֤�û��������Ƿ���ȷ
2���޸��û���������
3���û��������벻�ð��������ַ��Լ����벻��С����λ�ַ�
*/

/**
�û����������С������֤�Լ��޸��������û����Ĺ���
@author ������
@version v1.0
*/
public class UserService {

	//�û��������벻���е��ַ�
	private static char[] c = {':', '!', '#', '$', '%', '^', '&', '*', '(', ')', '~',
								'`', '<', '>', '?', '\\', '|', ':', '\'', ';', '-', 
								'=', '+', '/'};
	
	//��ʼ���û���������
	private static String name = null;
	private static String password = null;

	/**
	��֤������û����������Ƿ���������ַ��ķ���
	@param arr ������û���������
	@return ����boolean����
	*/
	private static boolean chazhao(char[] arr) {
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < c.length; j++)
				if(arr[i] == c[j])
					return true;
				
		return false;
	}
	
	/**
	��ʼ���û���������
	@param name ��ʼ�û���
	@param password ��ʼ����
	*/
	public UserService(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	��֤�û����������Ƿ���ȷ�ķ���
	@param n �û���
	@param p ����
	@return ����boolean����
	*/
	public static boolean validate(String n, String p) {
		if(n.equals(name) && p.equals(password))
			return true;
		else
			return false;
	
	}
	
	/**
	�޸��û����ķ������ڳ�ʼ�û�����������ȷ�������
	@param n �޸ĵ��û���
	@param b ԭ���û���������ķ���ȷ
	@return ����boolean����
	*/
	public static boolean setName(boolean b, String n) {
		if(b == true) {
			//�û����������
			String temp = n;

			//��Ҫ�޸ĵ�����ת�����ַ����飬���ڲ��������Ƿ���������ַ�
			char[] c1 = temp.toCharArray();
			
			//��֤�û��������Ƿ���������ַ�
			if(chazhao(c1) == false) {
				name = temp;
				return true;
			} else {
				System.out.println("���������ַ�!!");
				return false;
			}
		}

		return false;
	}

	/**
	�޸����룬�ڳ�ʼ�û�����������ȷ�������
	@param p �޸ĵ�����
	@param b Ը�û����������Ƿ���ȷ
	@return ����boolean����
	*/
	public static boolean setPassword(boolean b, String p) {
		if(b == true) {
			String temp = p;

			//��Ҫ�޸ĵ�����ת�����ַ����飬���ڲ��������Ƿ���������ַ�
			char[] c1 = temp.toCharArray();
			
			//���볤�Ȳ���С����
			if(temp.length() > 4) {
				//��֤�����Ƿ���������ַ�
				if(chazhao(c1) == false) {
					password = temp;
					return true;
				} else {
					System.out.println("���������ַ�!!");
					return false;
				}

			} else {
				System.out.println("���벻��С����λ!��");
				return false;
			}
		}

		return false;
	}
}
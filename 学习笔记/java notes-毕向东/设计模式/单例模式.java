/*���ģʽ�����ĳһ����������֮��Ч�ķ�����
java��23�����ģʽ��
�������ģʽ�����һ�������ڴ�ֻ����һ������


��Ҫ��֤����Ψһ��
1��Ϊ�˱�������������ཨ����������Ƚ�ֹ�����������������
2����Ϊ��������������Է��ʵ��������ֻ���ڱ����У��Զ���һ������
3��Ϊ�˷�������������Զ������ķ��ʣ����Զ����ṩһЩ���ʷ�ʽ��

��������ô�ô��������أ�
1�������캯��˽�л���
2�������д���һ���������
3���ṩһ���������Ի�ȡ���ö���



�����������ô����������ô������
����Ҫ��������Ķ���֤���ڴ���Ψһʱ���ͽ����ϵ��������ϼ��ɡ�
*/

/*
����ģʽ��
����ʽ
*/
class Single {
	private static Single s = new Single();

	private Single() {}
	
	public static Single getInstance() {
		return s;
	}
}

/*
����ʽ
*/
class Single2 {
	private static Single2 s = null;
	
	private Single2() {}

	public static Single2 getInstance() {
		if(s == null) {
			s= new Single2();
		}
		return s;
	}
}

/*
����ʽ�ڲ���ȫ�ԵĽ������һ��
*/
class Single3 {
	private static Single3 s = null;

	private Single3() {}

	public static synchronized Single3 getInstance() {
		if(s == null) {
			s = new Single3();
		}
		return s;
	}
}

/*
����ʽ�����������
*/
class Single4 {
	private static Single4 s = null;

	private Single4() {}

	public static Single4 getInstance() {
		if(s == null) {
			synchronized(Single4.class) {

				if(s == null) {
					s = new Single4();
				}
			}
		}
		return s;
	}
}
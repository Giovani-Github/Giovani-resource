/*
������ʦʹ�õ��Խ���

���Ի����쳣��
����
ð��
*/

public class SException {
	public static void main(String[] args) {
		Teacher tcr = new Teacher("����");
		
		try {
			tcr.teaching();
		} catch(StopException e) {
			System.out.println(e.toString());
			System.out.println("����ʦ��ż�");
		}
		
	}
}


class Computer {
	public void run() throws BlueException, CollapseException {
		int temp = 3;
		if(temp == 2)
			throw new BlueException();
		if(temp == 3)
			throw new CollapseException();

		System.out.println("��������");
	}

	public void close() {
		System.out.println("���Թر�");
	}

	public void restart() {
		System.out.println("��������");
	}
}


class Teacher {
	private String name;
	Computer cmpt;

	Teacher(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public void teaching() throws StopException{
		cmpt = new Computer();

		try {
			cmpt.run();
		} catch(BlueException e) {
			System.out.println(e.toString());
			cmpt.restart();
		} catch(CollapseException e) {
			System.out.println(e.toString());
			test();
			throw new StopException();
		}
		

		System.out.println(getName() + "����");
	}

	public void test() {
		System.out.println(getName() + "��ʦ����������ϰ");
	}
}


class BlueException extends Exception {
	BlueException() {
		super("��������");
	}
}


class CollapseException extends Exception {
	CollapseException() {
		super("Ӳ����");
	}
}


class StopException extends Exception {
	StopException() {
		super("�ڿ�������ֹͣ");
	}
}
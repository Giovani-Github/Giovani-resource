/*
需求：老师使用电脑讲课

电脑会有异常：
蓝屏
冒烟
*/

public class SException {
	public static void main(String[] args) {
		Teacher tcr = new Teacher("毕向东");
		
		try {
			tcr.teaching();
		} catch(StopException e) {
			System.out.println(e.toString());
			System.out.println("换老师或放假");
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

		System.out.println("电脑运行");
	}

	public void close() {
		System.out.println("电脑关闭");
	}

	public void restart() {
		System.out.println("电脑重启");
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
		

		System.out.println(getName() + "讲课");
	}

	public void test() {
		System.out.println(getName() + "老师让你们做练习");
	}
}


class BlueException extends Exception {
	BlueException() {
		super("电脑蓝屏");
	}
}


class CollapseException extends Exception {
	CollapseException() {
		super("硬件损坏");
	}
}


class StopException extends Exception {
	StopException() {
		super("授课因意外停止");
	}
}
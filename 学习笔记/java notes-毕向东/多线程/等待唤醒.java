class ZiYuan {
	private String name;
	private String max;
	private boolean b = false;

	public synchronized void set(String name, String max) {
		if(b) {
			try	{
				this.wait(); // �ó���this��������̵߳ȴ���������߳���������ϵȴ���
			}catch(Exception e) {
			
			}
		}

		this.name = name;
		this.max = max;

		b = true;
		this.notify(); // ����������ϵȴ����߳�����
	}	


	public synchronized void print() {
		if(!b) {
			try	{
				this.wait();
			}catch(Exception e) {
			
			}
		}

		System.out.println(name + "********" + max);
		b = false;
		this.notify();
	}
}

class Input implements Runnable {
	ZiYuan z;
	Input(ZiYuan z) {
		this.z = z;
	}

	int sum = 0;
	public void run() {
		while(true) {
				if(sum == 0) {
					z.set("mai", "nan");
				} else {
					z.set("����", "ŮŮ");
				}
				sum = (sum+1) % 2;
		}
	}
}

class Output implements Runnable {
	ZiYuan z;
	Output(ZiYuan z) {
		this.z = z;
	}

	public void run() {
		while(true) {
			z.print();
		}
	}
}

public class Test {
	public static void main(String[] arge) {
		ZiYuan z = new ZiYuan();
		
		new Thread(new Input(z)).start();
		new Thread(new Output(z)).start();
	}
}
class zhangsan {
	public void say() {
		System.out.println("������˵����������ſ����Ҿͷſ��ҵ���");
	}

	public void show() {
		System.out.println("�õ�");
	}
}


class lisi {
	public void say() {
		System.out.println("�Ķ���˵����������ſ����Ҿͷſ��ҵ���");
	}

	public void show() {
		System.out.println("�õ�");
	}
}


class My implements Runnable {
	private static zhangsan z = new zhangsan();
	private static lisi l = new lisi();
	private boolean b;

	public void setB(boolean d) {
		b = d;
	}

	public void run() {
		if(b) {
			synchronized(z) {
				z.say();
				try	{
					Thread.sleep(10);
				} catch(Exception e) {
				
				}

				synchronized(l) {
					z.show();
				}
			}
		} else {
			synchronized(l) {
				l.say();
				try	{
					Thread.sleep(10);
				} catch(Exception e) {
				
				}

				synchronized(z) {
					l.show();	
				}
			}
		}
	}
}


public class Test1 {
	public static void main(String[] areg) {
		My m = new My();
		My m1 = new My();
		m.setB(true);
		m1.setB(false);
		
		Thread t = new Thread(m);
		Thread t1 = new Thread(m1);

		t.start();
		t1.start();
	}
}
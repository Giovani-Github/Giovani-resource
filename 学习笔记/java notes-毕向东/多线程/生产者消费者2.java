import java.util.concurrent.locks.*;

public class Test {
	public static void main(String[] arge) {
		Res s = new Res();

		Intput in = new Intput(s);
		Output ou = new Output(s);
		
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(in);
		Thread t3 = new Thread(ou);
		Thread t4 = new Thread(ou);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}


class Res {
	private String name;
	private int count;
	private boolean b = false;

	private Lock lock = new ReentrantLock();//��
	private Condition condition_con = lock.newCondition();//����lock���ĵȴ����ѵȵĶ���
	private Condition condition_pro = lock.newCondition();//����lock���ĵȴ����ѵȵĶ���

	public void set(String name) throws InterruptedException {
		lock.lock();//�õ���
		try	{
			if(b) {
				condition_con.await();//��con�ĵȴ�
			}

			this.name = name + "----" + count++;
			System.out.println(Thread.currentThread().getName() + "....������" + this.name);
			b = true;
			condition_pro.signal();//��pro�Ļ���
		} finally {
			lock.unlock();//�ͷ���
		}
		
		
	}

	public synchronized void out() throws InterruptedException {
		lock.lock();//�õ���
		try	{
			if(!b) {
				condition_pro.await();//��pro�ĵȴ�
			}

			System.out.println(Thread.currentThread().getName() + ".............������" + this.name);
			b = false;
			condition_con.signal();//��con�Ļ���
		} finally {
			lock.unlock();//�ͷ���
		}
	}
}


class Intput implements Runnable {
	private Res r;
	Intput(Res r) {
		this.r = r;
	}

	public void run() {
		while(true) {
			try	{
				r.set("+���+");
			} catch(InterruptedException e) {
			
			}
		}
	}
}

class Output implements Runnable {
	private Res r;
	Output(Res r) {
		this.r = r;
	}

	public void run() {
		while(true) {
			try	{
				r.out();
			} catch(InterruptedException e) {
			
			}
		}
	}
}
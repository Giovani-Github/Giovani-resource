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

	private Lock lock = new ReentrantLock();//锁
	private Condition condition_con = lock.newCondition();//绑定于lock锁的等待唤醒等的对象
	private Condition condition_pro = lock.newCondition();//绑定于lock锁的等待唤醒等的对象

	public void set(String name) throws InterruptedException {
		lock.lock();//拿到锁
		try	{
			if(b) {
				condition_con.await();//用con的等待
			}

			this.name = name + "----" + count++;
			System.out.println(Thread.currentThread().getName() + "....生产者" + this.name);
			b = true;
			condition_pro.signal();//用pro的唤醒
		} finally {
			lock.unlock();//释放锁
		}
		
		
	}

	public synchronized void out() throws InterruptedException {
		lock.lock();//拿到锁
		try	{
			if(!b) {
				condition_pro.await();//用pro的等待
			}

			System.out.println(Thread.currentThread().getName() + ".............消费者" + this.name);
			b = false;
			condition_con.signal();//用con的唤醒
		} finally {
			lock.unlock();//释放锁
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
				r.set("+面包+");
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
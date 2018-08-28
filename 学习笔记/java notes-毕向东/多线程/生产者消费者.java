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

	public synchronized void set(String name) {
		while(b) {
			try	{
				this.wait();
			} catch(Exception e) {
				e.toString();
			}
		}

		this.name = name + "----" + count++;
		System.out.println(Thread.currentThread().getName() + "....生产者" + this.name);
		b = true;
		this.notifyAll();
	}

	public synchronized void out() {
		while(!b) {
			try	{
				this.wait();
			} catch(Exception e) {
				e.toString();
			}
		}

		System.out.println(Thread.currentThread().getName() + ".............消费者" + this.name);
		b = false;
		this.notifyAll();
	}
}


class Intput implements Runnable {
	private Res r;
	Intput(Res r) {
		this.r = r;
	}

	public void run() {
		while(true) {
			r.set("+面包+");
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
			r.out();
		}
	}
}
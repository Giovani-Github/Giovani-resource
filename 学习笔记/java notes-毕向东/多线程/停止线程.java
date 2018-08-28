class StopThread implements Runnable {
	private boolean b = true;

	public synchronized void run() {
		while(b) {//改变标记以停止线程
			try	{
				wait();			
			} catch(Exception e) {
				System.out.println(Thread.currentThread().getName() + "Exception");
				setB();
			}
			System.out.println(Thread.currentThread().getName() + "*****run");
		}
	}

	public void setB() {
		b = false;
	}
}


public class Test {
	public static void main(String[] arge) {
		int count = 0;
		StopThread st = new StopThread();

		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);

		t1.start();
		t2.start();

		while(true) {
			if(count == 60) {
				//st.setB();
				t1.interrupt();
				t2.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName() + "******Mian" + count++);
		}
	}
}
class RunnableTest implements Runnable {
	private int counnt = 100;
	boolean b = true;
	public void run() {
		if(b) {
			b = false;

			while(true) {
				synchronized(this) {
					if(counnt > 0){
						System.out.println("run" + counnt--);
					}
				}
			}
		} else {
			while(true) {
				show();
			}
		}
	}

	public synchronized void show() {
		if(counnt > 0) {
			System.out.println("show" + counnt--);
		}
	}
}


public class Test1 {
	public static void main(String[] arge) {
		RunnableTest r = new RunnableTest();
		Thread t = new Thread(r);
		Thread t1 = new Thread(r);

		t.start();
		try	{
			Thread.sleep(10);
		}
		catch (Exception e)	{
		}
//		r.b = false;

		t1.start();
	}
}
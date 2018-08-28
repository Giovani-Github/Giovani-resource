class RunnableTest implements Runnable {
	private int counnt = 100;
	boolean b = true;
	public void run() {
		if(b) {
			b = false;

			while(true) {
				synchronized(this) {
					if(counnt > 0){
						System.out.println(Thread.currentThread().getName() + "ÂòÆ±**" + counnt--);
					}
				}
			}
		} else {
			while(true) {
				show();
			}
		}
	}

	public void show() {
		if(counnt > 0){
			System.out.println(Thread.currentThread().getName() + "ÂòÆ±**" + counnt--);
		}
	}
}


public class Test {
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

		t2.start();
	}
}
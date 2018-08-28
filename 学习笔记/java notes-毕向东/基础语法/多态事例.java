interface PCI
{
	public abstract void open();
	public abstract void close();
}


class Zhu
{
	public void run()
	{
		System.out.println("主板运行");
	}

	public void pci(PCI p)
	{
		p.open();
		p.close();
	}

	public void close()
	{
		System.out.println("主板关闭");
	}
}


class Xian implements PCI
{
	public void open()
	{
		System.out.println("显卡运行");
	}

	public void close()
	{
		System.out.println("显卡关闭");
	}
}


class Sheng implements PCI
{
	public void open()
	{
		System.out.println("声卡运行");
	}

	public void close()
	{
		System.out.println("声卡关闭");
	}
}


public class Test
{
	public static void main(String[] arge)
	{
		Zhu z = new Zhu();
		z.run();

		z.pci(new Xian());
		z.pci(new Sheng());

		z.close();
	}
}
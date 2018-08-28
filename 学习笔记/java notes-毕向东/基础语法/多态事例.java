interface PCI
{
	public abstract void open();
	public abstract void close();
}


class Zhu
{
	public void run()
	{
		System.out.println("��������");
	}

	public void pci(PCI p)
	{
		p.open();
		p.close();
	}

	public void close()
	{
		System.out.println("����ر�");
	}
}


class Xian implements PCI
{
	public void open()
	{
		System.out.println("�Կ�����");
	}

	public void close()
	{
		System.out.println("�Կ��ر�");
	}
}


class Sheng implements PCI
{
	public void open()
	{
		System.out.println("��������");
	}

	public void close()
	{
		System.out.println("�����ر�");
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
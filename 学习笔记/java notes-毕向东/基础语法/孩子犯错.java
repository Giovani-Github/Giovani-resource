/*
ÿ�����Ӷ��᷸����ĸ���ݺ��ӷ�������Ͳ�һ�����Դ�����д����������ӡ�д����ĸ�࣬������ʹ����ࡣ
(���翼�Բ�����----̸�ģ����һ�仰��   ͵����----����);
*/

interface Wrong
{
	public abstract String bjg();
	public abstract String tdx();
}


class Persion
{
	String name = null;

	public Persion(String name)
	{
		this.name = name;
	}

	public void setName(String n)
	{
		name = n;
	}

	public String getName()
	{
		return name;
	}
}


class Parents extends Persion
{
	public Parents(String name)
	{
		super(name);
	}

	public static void education(String edt)
	{
		if(edt == "͵����")
		{
			System.out.println("̸��");		
		} else {
			System.out.println("����");
		}
	}
}


class Child  extends Persion implements Wrong
{
	String name = null;

	public Child(String name)
	{
		super(name);
	}

	public String bjg()
	{
		return "������";
	}

	public String tdx()
	{
		return "͵����";
	}
	
}



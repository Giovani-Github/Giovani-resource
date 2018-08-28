/*
每个孩子都会犯错，父母根据孩子犯错的类型不一样，对错误进行处理，教育孩子。写出父母类，孩子类和错误类。
(比如考试不及格----谈心（输出一句话）   偷东西----挨打);
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
		if(edt == "偷东西")
		{
			System.out.println("谈心");		
		} else {
			System.out.println("挨打");
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
		return "不及格";
	}

	public String tdx()
	{
		return "偷东西";
	}
	
}



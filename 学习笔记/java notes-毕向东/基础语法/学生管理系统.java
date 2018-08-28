import java.util.*;
public class XueSheng
{	
	public static void main(String[] arge)

	{
		LuRu lu = new LuRu();

		lu.luRu()；
		lu.print();	
	}
}


class ShuException extends RuntimeException
{
	private int num;
	
	ShuException()
	{
		super();
	}

	ShuException(String name, int num)
	{
		super(name);
		this.num = num;
	}

	public int getNum()
	{
		return num;
	}
	
}


class LuRu
{
	String[] name = null;//学生姓名
	String[] kemu = null;//科目
	double[][] number = null;//成绩
	double[] sum = null;//总分
	String[] zhong = null;//整合

	//录入数据
	public void luRu()
	{
		Scanner in = new Scanner(System.in);

		System.out.println("请输入学生人数：");
		int renshu = in.nextInt();

		if(renshu < 0 || renshu == 0)
		{
			throw new FuShuException("输入的数字不符合规则", renshu);
		}

		System.out.println("请输入科目数量");
		int kemushu = in.nextInt();

		if(kemushu < 0 || renshu == 0)
		{
			throw new FuShuException("输入的数字不符合规则", kemushu);
		}

		//数组初始化
		name = new String[renshu];//学生姓名
		kemu = new String[kemushu];//科目名
		number = new double[renshu][kemushu];//第renshu人的kemushu成绩
		sum = new double[renshu];//总分
		zhong = new String[renshu];//整合，就是把姓名成绩等连接起来

		//录入科目名称
		for(int i = 0; i < kemu.length; i++)
		{
			System.out.println("请输入第" + (i+1) + "个科目名称：");
			kemu[i] = in.next();
		}	

		//录入学生姓名
		for(int i = 0; i < renshu; i++)
		{
			System.out.println("请输入第" + (i+1) + "个学生姓名：");
			name[i] = in.next();

			zhong[i] = name[i] + "\t";//进行姓名连接
			
			//录入科目成绩
			for(int z = 0; z < kemu.length; z++)
			{
				System.out.println("请输入" + name[i] + "的" + kemu[z] + "成绩");
				number[i][z] = in.nextDouble();//录入各科成绩

				sum[i] += number[i][z];//累计总分

				zhong[i] += number[i][z] + "\t";//进行成绩连接
			}
			int ping = (int)(sum[i] / kemu.length);
			zhong[i] += sum[i] + "\t" + ping + "\t";//进行总分及平均分连接
		}
	}

	//效果输出
	public void print() throws NullPointerException
	{
		System.out.print("学生" + "\t");

		for(int i = 0; i < kemu.length; i++)
		{
			System.out.print(kemu[i] + "\t");
		}

		System.out.print("总分" + "\t" + "平均分" + "\t" + "排行榜" + "\n");
		
		huan(zhong);
			
		//排序后输出
		for(int i = 0; i < zhong.length; i++)
		{
			System.out.println(zhong[i] + (i+1));
		}
	}

	private void huan(String[] arr)
	{
		//根据总分进行排序
		for(int i = 0; i < sum.length-1; i++)
		{
			for(int j = 0; j < sum.length-1-i; j++)
			{
				if(sum[j] < sum[j+1])
				{
					String s = zhong[j];
					zhong[j] = zhong[j+1];
					zhong[j+1] = s;
				}
			}
		}
	}
}
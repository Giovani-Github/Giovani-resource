/*有五个学生，每个学生有3门课的成绩，
从键盘输入以上数据（包括姓名，三门课成绩），
输入的格式：如：zhagnsan，30，40，60计算出总成绩，
并把学生的信息和计算出的总分数高低顺序存放在磁盘文件"stud.txt"中。

1，描述学生对象。
2，定义一个可操作学生对象的工具类。

思想：
1，通过获取键盘录入一行数据，并将该行中的信息取出封装成学生对象。
2，因为学生有很多，那么就需要存储，使用到集合。因为要对学生的总分排序。
	所以可以使用TreeSet。
3，将集合的信息写入到一个文件中。
*/

import java.util.*;
import java.io.*;

public class Test
{
	public static void main(String[] args)
	{
		Set<Studen> st = StudenTool.getStuden();
		StudenTool.writeFile(st, "F:\\JAVA\\代码\\Io测试文件\\Studen.txt");
	}
}


class Studen implements Comparable<Studen>
{
	String name;
	int cn, en, in;
	int sum;

	Studen(String name, int cn, int en, int in)
	{
		this.name = name;
		this.cn = cn;
		this.en = en;
		this.in = in;

		sum = cn+in+en;
	}

	public int compareTo(Studen stu)
	{
		int a = new Integer(this.sum).compareTo(new Integer(stu.sum));
		if(a == 0)
			return this.name.compareTo(stu.name);
		return a;
	}

	public boolean equals(Object obj)
	{
		if(!(obj instanceof Studen))
			throw new ClassCastException("类型不匹配");
		Studen st = (Studen)obj;
		return this.name.equals(st.getName()) && this.getSum() == st.getSum();
	}

	public int hashCode()
	{
		return this.name.hashCode()+getSum()*28;
	}

	public String getName()
	{
		return name;
	}

	public int getSum()
	{
		return sum;
	}

	public String toString()
	{
		return "Studen["+name+","+cn+","+en+","+in+"]";
	}
}


class StudenTool
{
	public static Set<Studen> getStuden()
	{
			return getStuden(null);
	}

	public static Set<Studen> getStuden(Comparator<Studen> cmp)
	{
		BufferedReader br = null;

		Set<Studen> st = null;
		if(cmp == null)
			st = new TreeSet<Studen>();
		else
			st = new TreeSet<Studen>(cmp);

		try
		{
			
			br = new BufferedReader(new InputStreamReader(System.in));

			String line = null;
			while((line=br.readLine()) != null)
			{
				if(line.equals("over"))
					break;

				String[] s1 = line.split(",");
				Studen sd = new Studen(s1[0], Integer.parseInt(s1[1]),
												Integer.parseInt(s1[2]),
												Integer.parseInt(s1[3]));

				st.add(sd);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(br != null)
					br.close();
			}
			catch(IOException e)
			{
				System.out.println(e.toString());
			}
		}

		return st;
	}

	public static void writeFile(Set<Studen> stu, String file)
	{
		BufferedWriter bw = null;

		try
		{
			bw = new BufferedWriter(new FileWriter(file));
			
			for(Studen st : stu)
			{
				bw.write(st.toString() + "\t");
				bw.write(st.getSum()+"");
				bw.newLine();
				bw.flush();
			}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(bw != null)
					bw.close();
			}
			catch(IOException e)
			{
				System.out.println(e.toString());
			}
		}
	}
}
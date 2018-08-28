/*�����ѧ����ÿ��ѧ����3�ſεĳɼ���
�Ӽ��������������ݣ��������������ſγɼ�����
����ĸ�ʽ���磺zhagnsan��30��40��60������ܳɼ���
����ѧ������Ϣ�ͼ�������ܷ����ߵ�˳�����ڴ����ļ�"stud.txt"�С�

1������ѧ������
2������һ���ɲ���ѧ������Ĺ����ࡣ

˼�룺
1��ͨ����ȡ����¼��һ�����ݣ����������е���Ϣȡ����װ��ѧ������
2����Ϊѧ���кܶ࣬��ô����Ҫ�洢��ʹ�õ����ϡ���ΪҪ��ѧ�����ܷ�����
	���Կ���ʹ��TreeSet��
3�������ϵ���Ϣд�뵽һ���ļ��С�
*/

import java.util.*;
import java.io.*;

public class Test
{
	public static void main(String[] args)
	{
		Set<Studen> st = StudenTool.getStuden();
		StudenTool.writeFile(st, "F:\\JAVA\\����\\Io�����ļ�\\Studen.txt");
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
			throw new ClassCastException("���Ͳ�ƥ��");
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
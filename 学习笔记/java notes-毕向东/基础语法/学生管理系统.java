import java.util.*;
public class XueSheng
{	
	public static void main(String[] arge)

	{
		LuRu lu = new LuRu();

		lu.luRu()��
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
	String[] name = null;//ѧ������
	String[] kemu = null;//��Ŀ
	double[][] number = null;//�ɼ�
	double[] sum = null;//�ܷ�
	String[] zhong = null;//����

	//¼������
	public void luRu()
	{
		Scanner in = new Scanner(System.in);

		System.out.println("������ѧ��������");
		int renshu = in.nextInt();

		if(renshu < 0 || renshu == 0)
		{
			throw new FuShuException("��������ֲ����Ϲ���", renshu);
		}

		System.out.println("�������Ŀ����");
		int kemushu = in.nextInt();

		if(kemushu < 0 || renshu == 0)
		{
			throw new FuShuException("��������ֲ����Ϲ���", kemushu);
		}

		//�����ʼ��
		name = new String[renshu];//ѧ������
		kemu = new String[kemushu];//��Ŀ��
		number = new double[renshu][kemushu];//��renshu�˵�kemushu�ɼ�
		sum = new double[renshu];//�ܷ�
		zhong = new String[renshu];//���ϣ����ǰ������ɼ�����������

		//¼���Ŀ����
		for(int i = 0; i < kemu.length; i++)
		{
			System.out.println("�������" + (i+1) + "����Ŀ���ƣ�");
			kemu[i] = in.next();
		}	

		//¼��ѧ������
		for(int i = 0; i < renshu; i++)
		{
			System.out.println("�������" + (i+1) + "��ѧ��������");
			name[i] = in.next();

			zhong[i] = name[i] + "\t";//������������
			
			//¼���Ŀ�ɼ�
			for(int z = 0; z < kemu.length; z++)
			{
				System.out.println("������" + name[i] + "��" + kemu[z] + "�ɼ�");
				number[i][z] = in.nextDouble();//¼����Ƴɼ�

				sum[i] += number[i][z];//�ۼ��ܷ�

				zhong[i] += number[i][z] + "\t";//���гɼ�����
			}
			int ping = (int)(sum[i] / kemu.length);
			zhong[i] += sum[i] + "\t" + ping + "\t";//�����ּܷ�ƽ��������
		}
	}

	//Ч�����
	public void print() throws NullPointerException
	{
		System.out.print("ѧ��" + "\t");

		for(int i = 0; i < kemu.length; i++)
		{
			System.out.print(kemu[i] + "\t");
		}

		System.out.print("�ܷ�" + "\t" + "ƽ����" + "\t" + "���а�" + "\n");
		
		huan(zhong);
			
		//��������
		for(int i = 0; i < zhong.length; i++)
		{
			System.out.println(zhong[i] + (i+1));
		}
	}

	private void huan(String[] arr)
	{
		//�����ֽܷ�������
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
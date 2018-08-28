import java.util.*;
import java.io.*;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		Demo_2();
	}

	public static void Demo() throws IOException
	{
		OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream("F:\\JAVA\\代码\\Io测试文件\\TEXT.ini"));
		FileInputStream fs = new FileInputStream("F:\\JAVA\\代码\\Io测试文件\\TEXT.ini");
		Properties pt = new Properties();
	
		fos.write("tim=0");
		fos.flush();
		pt.load(fs);

		pt.setProperty("tim", "20");
		pt.store(fos, "dd");
		pt.list(System.out);

		fos.close();
		fs.close();
	}

	public static void Demo_2() throws IOException
	{
		Properties por = new Properties();

		File f = new File("F:\\JAVA\\代码\\Io测试文件\\TEXT2.ini");
		if(!f.exists())
			f.createNewFile();

		FileReader fr = new FileReader(f);
		por.load(fr);

		int count = 0;
		String value = por.getProperty("time");

		if(value != null)
		{
			count = Integer.parseInt(value);
			if(count >= 5)
			{
				System.out.println("拿钱");
				return;
			}
		}

		count++;
		por.setProperty("time", count+"");

		FileWriter fw = new FileWriter(f);
		por.store(fw, "");

		fw.close();
		fr.close();
	}
}
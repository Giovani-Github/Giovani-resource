import java.text.MessageFormat;
public class MessageFormatTest {
	public static void main(String[] args) {
		/*
			������ռλ�����ַ�������ģ��
			ռλ��:{0}��{1}��{2}...
			�ɱ��������Ҫָ��ģ����ռλ����ֵ���м���ռλ�����ṩ��������
		*/
		String s = MessageFormat.format("{0}��{1}����", "�û���", "����");
		System.out.println(s);
	}
}

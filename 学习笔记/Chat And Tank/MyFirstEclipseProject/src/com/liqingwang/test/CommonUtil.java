package com.liqingwang.test;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;
/**
 * @author admin_tang
 * @since 2010-4-4
 */

/**
 * ������ ȫ���Ǿ�̬��
 */

public class CommonUtil {

	/**
	 * �ж��ַ����Ƿ�Ϊ�� implemented by admin_tang
	 *
	 * @since 2006-04-04
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.equals("");
	}
	/**
	 * �ж�Long�Ƿ�Ϊ�� null����С��1��Ϊ��
	 *
	 * @since 2006-04-04
	 */
	public static boolean isLongEmpty(Long s) {
		return null == s  || s  < 1;
	}
	/**
	 * �ж�Integer�Ƿ�Ϊ�� null����С��1��Ϊ��
	 *
	 * @since 2006-04-04
	 */
	public static boolean isIntegerEmpty(Integer s) {
		return null == s  || s  < 1;
	}
	/**
	 * �ж�Short�Ƿ�Ϊ�� null����С��1��Ϊ��
	 *
	 * @since 2006-04-04
	 */
	public static boolean isShortEmpty(Short s) {
		return null == s  || s  < 1;
	}
	public static int parseInt(String s, int defaultValue) {
		if (s == null || "".equals(s)) {
			return defaultValue;
		}

		int nValue = defaultValue;
		try {
			nValue = Integer.parseInt(s);
		} catch (Exception e) {
		}
		return nValue;
	}

	/**
	 * �򵥽��ܣ��ж��ַ����Ƿ񳬳�<br>
	 * ��ϸ���ܣ�<br>
	 * @param s ��Ҫ�жϳ����Ƿ񳬹�ָ�����ȵ��ַ���
	 * @param l ����
	 * @return TRUE OR FALSE  �������ָ���ĳ���(������l)������true��û�г�������Ϊ�գ�����false;
	 * @version 1.1  2014-4-17
	 * @autor liangyongtong
	 */
	public static boolean isOutLength(String s, int l) {
		if(null == s || 0 == s.length())
			return false;
		if(s.length() > l)
			return true;
		return false;
	}

	public static String normalizeString(String strValue) {
		return ((strValue == null) ? "" : strValue.trim());
	}

	/**
	 * ����ǰ����ת��Ϊָ����ʽ���ַ���
	 *
	 * @param pattern
	 *            ��ʽ
	 * @return String
	 */
	public static String getDateStringByPattern(String pattern) {
		Date date = new Date();
		return getDateStringByPattern(date, pattern);
	}

	/**
	 * ��ָ������ת��Ϊָ����ʽ���ַ���
	 *
	 * @param date
	 *            ( java.core.Date )
	 * @param pattern
	 *            ���ڸ�ʽ���磺"yyyy-MM-dd" ��"yyyy-MM-dd HH:mm:ss"��"HH:mm:ss"
	 * @return String
	 */
	public static String getDateStringByPattern(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		String str = sf.format(date);

		return str;
	}

	public static String getDate() {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append(date.getYear() + 1900);
		sb.append("-");
		int month = date.getMonth() + 1;
		sb.append(month < 10 ? "0" + month : month);
		sb.append("-");
		int d = date.getDate();
		sb.append(d < 10 ? "0" + d : d);
		return sb.toString();
	}

	/**
	 * ȡ��ϵͳ��ǰ��ʱ�䣬��Timestamp ��ʾ
	 *
	 * @ return ����Timestamp����
	 */
	public static Timestamp getDateTime() {
		java.util.Date date = new java.util.Date();
		return (new java.sql.Timestamp(date.getTime()));
	}

	/**
	 * the following const is to define date format.
	 */

	public static java.util.Date parseDate(String strDate, String pattern) {
		if (isEmpty(strDate)) {
			return null;
		}
		java.text.SimpleDateFormat fmtDate = null;
		java.text.ParsePosition pos = new java.text.ParsePosition(0);
		fmtDate = new java.text.SimpleDateFormat(pattern);

		java.util.Date dtRet = null;
		try {
			return dtRet = fmtDate.parse(strDate, pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtRet;

	}

	public static boolean exactEqual(String source, String target) {
		if (source == null || target == null)
			return false;
		if (source.length() != target.length())
			return false;

		char[] sc = source.toCharArray();
		char[] tc = target.toCharArray();
		for (int i = 0; i < sc.length; i++) {
			if (sc[i] == tc[i]) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * ��ȡϵͳ����·��
	 *
	 * @return
	 */
	public final static String getPath() {
		CommonUtil function = new CommonUtil();
		return function.getUrlPath();
	}

	private final String getUrlPath() {

		String path = URLDecoder.decode(this.getClass().getResource("/")
				.toString());
		if (System.getProperties().get("os.name").toString().toLowerCase()
				.contains("windows")) {
			path = path.substring(6, path.indexOf("WEB-INF"));
		} else {
			path = path.substring(5, path.indexOf("WEB-INF"));
		}
		return path;
	}

	/**
	 * תUtf8
	 *
	 * @param str
	 * @return
	 */
	public static final String changeEncoding(String str) {
		try {
			str = new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		return str;
	}

	/**
	 * תgbk
	 *
	 * @param str
	 * @return
	 */
	public static String iso2gbk(String str) {
		try {
			return new String(str.getBytes("ISO8859-1"), "gb2312");
		} catch (UnsupportedEncodingException ex) {
			return str;
		}
	}

	/*
	 * ..* ��ȡ��html��ǩ���ַ����Ĵ��ı�
	 */

	public static String extractText(String htmlStr) {
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ����script��ǩ

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ����style��ǩ

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ����html��ǩ

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}

	/**
	 * �������
	 *
	 * @param
	 * @return
	 */
	/*
	 * public static String filterDirtyWordFromContent(String content) {
	 * BaseCache dwc = new DirtyWordCache(); Collection<DirtyWord> ckwColl =
	 * (Collection<DirtyWord>) dwc.getCacheObject(); for (DirtyWord badWord :
	 * ckwColl) { System.out.println(badWord.getDirtyWord()); String word =
	 * safelyToLowerCase(badWord.getDirtyWord()); if (content.contains(word)) {
	 * content = content.replaceAll(word, "*****"); } } return content; }
	 */

	public static String safelyToLowerCase(String input) {
		if (input != null) {
			return input.toLowerCase();
		}
		return input;
	}

	public static String safelyToUpperCase(String input) {
		if (input != null) {
			return input.toUpperCase();
		}
		return input;
	}

	/**
	 * ���ڸ�ʽ��
	 *
	 * @param l
	 *            ��ת����
	 * @param pattern
	 *            ��ʽ
	 * @return
	 */
	public static String forMatDate(String l, String pattern) {
		try {
			return getDateStringByPattern(new Date(Long.valueOf(l)), pattern);
		} catch (Exception e) {
			return getDateStringByPattern(pattern);
		}
	}

	/***
	 * �鿴�Ƿ�����Ƶ�ļ���ʽ
	 *
	 * @param fileName
	 *            �ļ���
	 * @return boolean
	 */
	public static boolean getFileFormat(String fileName) {
		// ����ת�����ļ���ʽ
		String format_str = "asx|asf|mpg|wmv|3gp|mp4|mov|avi|flv|wmv9|rm|rmvb";
		// �õ��ļ�����չ��
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * �Ƿ���OpenOffice��ת���ĸ�ʽ
	 *
	 * @param fileName
	 *            �ļ���
	 * @return boolean
	 */
	public static boolean isDocumentType(String fileName) {
		// ��ת�����ĵ� ����չ��
		String format_str = "pdf|xhtml|html|odt|sxw|doc|docx|rtf|wpd|txt|wikitext"
				+ "|ods|sxc|xls|xlsx|csv|tsv|odp|sxi|ppt|pptx|odg|svg";
		// �õ��ļ�����չ��
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * �Ƿ�ΪͼƬ��ʽ
	 *
	 * @param fileName
	 *            �ļ���
	 * @return boolean
	 */
	public static boolean isImagesType(String fileName) {
		// ͼƬ��ʽ����չ��
		String format_str = "jpg|gif|bmp";
		// �õ��ļ�����չ��
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * �Ƿ�ΪFlash��ʽ
	 *
	 * @param fileName
	 *            �ļ���
	 * @return boolean
	 */
	public static boolean isFlashType(String fileName) {
		// flash��ʽ����չ��
		String format_str = "swf";
		// �õ��ļ�����չ��
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * �õ��ļ���׺��
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffixName(String fileName) {
		// �õ��ļ�����չ��
		String suffixName = fileName.substring(fileName.lastIndexOf("."),
				fileName.length()).toLowerCase();
		return suffixName;

	}

	/**
	 * �鿴�ļ��Ƿ�����У�� ��ʽ
	 *
	 * @param fileName
	 *            �ļ���
	 * @param formatType
	 *            У���ʽ
	 * @return
	 */
	public static boolean isDocumentType(String fileName, String formatType) {
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();

		return formatType.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * �õ�inputFile�ı���λ��
	 *
	 * @param inputFile
	 *            �ļ�·��
	 * @return String
	 */
	public static String getInputFilePath(String inputFile) {
		inputFile = inputFile.replace("\\", "/");
		inputFile = inputFile.replace("//", "/");
		String path = inputFile.substring(0, inputFile.lastIndexOf("/") + 1);
		return path;
	}

	/**
	 * �����ļ�����չ��
	 *
	 * @param fileName
	 * @param format
	 * @return
	 */
	public static String getModefyFormat(String fileName, String format) {
		return fileName.substring(0, fileName.lastIndexOf(".") + 1) + format;
	}

	/**
	 * �õ��ļ����ļ��� ��d:/a/b.doc �õ� b
	 *
	 * @param file
	 *            �ļ� ·��
	 * @return String
	 */
	public static String getFileName(String fileName) {
		fileName = fileName.replace("\\", "/");
		fileName = fileName.replace("//", "/");
		return fileName.substring(fileName.lastIndexOf("/") + 1,
				fileName.lastIndexOf("."));

	}

	public static boolean isDocu(String fileName) {
		String formatType = "doc,docx,ppt,pptx,rtf,xls,pdf";
		// String type = fileName.substring(fileName.lastIndexOf(".") + 1,
		// fileName.length()).toLowerCase();
		// int index=docutype.indexOf(type);
		// System.out.println("===>"+type);
		// return index>0?false:true;
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();

		return formatType.indexOf(type) >= 0 ? true : false;

	}

	/**
	 * �鿴�ļ����������ļ�
	 *
	 * @param fileName
	 *            �ļ�����
	 * @return List<String>
	 */
	public static int getFileAll(String fileName) {
		// List<String> fileList=new ArrayList<String>();

		File file = new File(fileName);
		File[] fileArray = file.listFiles();
		int fileSize = 0;
		try {
			fileSize = fileArray.length;

		} catch (Exception ex) {

		}
		return fileSize;

	}

	public static int getFileAll(String fileName, String page) {
		// List<String> fileList=new ArrayList<String>();
		fileName = CommonUtil.getPath() + fileName;
		// System.out.println(fileName);
		File file = new File(fileName);
		File[] fileArray = file.listFiles();
		int fileSize = 0;
		try {
			fileSize = fileArray.length;

		} catch (Exception ex) {

		}
		return fileSize > 0 ? fileSize - 1 : 0;

	}

	public static void main(String[] ages) {
		String importFileFileName = "aaa.java.xls";
		System.out.println(importFileFileName.substring(importFileFileName.lastIndexOf(".") + 1));
	}

	/**
	 * ���������ļ�������
	 *
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			// ����Ŀ���ļ���
			(new File(newPath)).mkdirs();
			// ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼
			File[] file = (new File(oldPath)).listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// �����ļ�
					copyFile(file[i],
							new File(newPath + "/" + file[i].getName()));
				}
				if (file[i].isDirectory()) {
					// ����Ŀ¼
					String sourceDir = oldPath + File.separator
							+ file[i].getName();
					String targetDir = newPath + File.separator
							+ file[i].getName();
					copyDirectiory(sourceDir, targetDir);
				}
			}

			// (new File(newPath)).mkdirs(); // ����ļ��в����� �������ļ���
			// File a = new File(oldPath);
			// String[] file = a.list();
			// File temp = null;
			// for (int i = 0; i < file.length; i++) {
			// if (oldPath.endsWith(File.separator)) {
			// temp = new File(oldPath + file[i]);
			// } else {
			// temp = new File(oldPath + File.separator + file[i]);
			// }
			//
			// if (temp.isFile()) {
			// FileInputStream input = new FileInputStream(temp);
			// FileOutputStream output = new FileOutputStream(newPath
			// + "/" + (temp.getName()).toString());
			// byte[] b = new byte[1024 * 5];
			// int len;
			// while ((len = input.read(b)) != -1) {
			// output.write(b, 0, len);
			// }
			// output.flush();
			// output.close();
			// input.close();
			// }
			// if (temp.isDirectory()) {// ��������ļ���
			// copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			// }
			// }
		} catch (Exception e) {
			System.out.println("���������ļ������ݲ�������");
			e.printStackTrace();

		}
	}

	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		// �½��ļ����������������л���
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// �½��ļ���������������л���
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// ��������
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// ˢ�´˻���������
		outBuff.flush();

		// �ر���
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	// �����ļ���
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// �½�Ŀ��Ŀ¼
		(new File(targetDir)).mkdirs();
		// ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// Դ�ļ�
				File sourceFile = file[i];
				// Ŀ���ļ�
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// ׼�����Ƶ�Դ�ļ���
				String dir1 = sourceDir + "/" + file[i].getName();
				// ׼�����Ƶ�Ŀ���ļ���
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * ��һ�����е�ĳ�ؼ��ֱ��
	 *
	 * @param content
	 * @param sensitive
	 * @return
	 */
	public static String getHtmlContent(String content, String sensitive) {
		String text = content.replaceAll(sensitive, "<font color='red'>"
				+ sensitive + "</font>");
		return text;
	}

	/**
	 * ���ַ������޸�Ϊutf-8
	 *
	 * @param str
	 * @return
	 */
	public static String setStr2utf8(String str) {
		String s = "";
		try {
			s = new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

	/**
	 * �����ַ��ı����ʽ
	 *
	 * @param str
	 * @return
	 */
	public static String setStr2Encoding(String str, String encoding) {
		String s = "";
		try {
			s = new String(str.getBytes("ISO-8859-1"), encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

	/**
	 * ��ȡ�����
	 *
	 * @param count
	 *            ����λ
	 * @return
	 */
	public static String getRandomByCount(int count) {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < count; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	/**
	 * ��ȡ��yyyy-MM-dd HH:mm:ss��ʽ��  ʱ��
	 *
	 * @return
	 */
	public static String getDateStrNow() {
		return CommonUtil.getDateStringByPattern("yyyy-MM-dd HH:mm:ss");
	}


	/**
	 * �����û���������LIst��װ�� sql���
	 *
	 * @return
	 */
	public static String getInByList(List<String> list) {
		if(CollectionUtils.isEmpty(list)){
			return " in ('')";
		}
		StringBuilder str = new StringBuilder(" (");
		for (String temp : list) {
			str.append("'"+temp+"',");
		}
		str.append("'')");
		return str.toString();
	}


	/**
	 * �򵥽��ܣ������ļ���ȡ�ļ�ͷ��������<br>
	 * ��ϸ���ܣ�<br>
	 * @param file ��Ҫ�жϵ��ļ�
	 * @return �����ļ�ͷ�����룺��Excel2003:D0CF11E0;Excel2007:504B0304�ȵ�
	 * @throws Exception
	 * @version 1.1  2014-4-28
	 * @autor liangyongtong
	 */
	public static String getFileHeadTypeString(File file) throws Exception {
		FileInputStream is = new FileInputStream(file);
		byte[] src = new byte[4];
		is.read(src, 0, src.length);
		StringBuilder sb = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		is.close();
		return sb.toString().toUpperCase();
	}


	/**
	 * �򵥽��ܣ����ƶ���<br>
	 * ��ϸ���ܣ�<br>
	 * @version 1.1  2014-5-9
	 * @autor wuxiao
	 */

	public  final static Object objectCopy(Object oldObj) {
		Object newObj = null;

		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(oldObj);//Դ����
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi= new ObjectInputStream(bi);
			newObj = oi.readObject();//Ŀ�����
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return newObj;
	}

	/**
	 * �򵥽��ܣ�����һ�������б�<br>
	 * ��ϸ���ܣ����ݲ��������ص�����ǰ��length���ڵ�һ�������б���������<br>
	 * @param length ��Ҫ������
	 * @return �����б� ���lengthС��0������null
	 * @version 1.1  2014-5-9
	 * @autor liangyongtong
	 */
	public static List<String> getDateList(int length) {
		if(length < 0) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for(int i = length - 1; i >= 0; i--){
			String time = df.format(new Date().getTime()-i*24*60*60*1000);
			list.add(time);
		}
		return list;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 */
	public static boolean isNumber(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * ����ԭ��SQL��ҳ��ѯ��SQL���
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param count
	 * @return
	 */
	public static String createPageSql(final String sql, int pageNo,
			final int pageSize, final int count) {
		StringBuffer rSql = new StringBuffer();
		rSql.append("select * from (select row_.*, rownum rownum_ from ( ");
        rSql.append(sql);
        rSql.append(" ) row_ where rownum <=" + (pageSize * (pageNo - 1) + pageSize) + ") " +
        		"where rownum_ >" + (pageSize * (pageNo - 1)));

		return rSql.toString();
	}

	
	
	/**
	 * ��StringתΪbytes�ֽ�
	 * @param str
	 * @param encoding
	 * @return
	 */
	public static String string2Bytes(String str, String encoding) {
		String s = "";
		try {
			if (CommonUtil.isEmpty(str)) {
				s = new String(str.getBytes());
			} else {
				s = new String(str.getBytes(encoding));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	

	public static List<Integer> idsToArray(String ids) {
		List<Integer> list = new ArrayList<>();
		for (String id : ids.split(",")) {
			list.add(Integer.parseInt(id));
		}
		return list;
	}
}

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
 * 工具类 全部是静态类
 */

public class CommonUtil {

	/**
	 * 判断字符串是否为空 implemented by admin_tang
	 *
	 * @since 2006-04-04
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.equals("");
	}
	/**
	 * 判断Long是否为空 null或者小于1都为空
	 *
	 * @since 2006-04-04
	 */
	public static boolean isLongEmpty(Long s) {
		return null == s  || s  < 1;
	}
	/**
	 * 判断Integer是否为空 null或者小于1都为空
	 *
	 * @since 2006-04-04
	 */
	public static boolean isIntegerEmpty(Integer s) {
		return null == s  || s  < 1;
	}
	/**
	 * 判断Short是否为空 null或者小于1都为空
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
	 * 简单介绍：判断字符串是否超长<br>
	 * 详细介绍：<br>
	 * @param s 需要判断长度是否超过指定长度的字符串
	 * @param l 长度
	 * @return TRUE OR FALSE  如果超过指定的长度(不包含l)，返回true；没有超过或者为空，返回false;
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
	 * 将当前日期转换为指定格式的字符串
	 *
	 * @param pattern
	 *            格式
	 * @return String
	 */
	public static String getDateStringByPattern(String pattern) {
		Date date = new Date();
		return getDateStringByPattern(date, pattern);
	}

	/**
	 * 将指定日期转换为指定格式的字符串
	 *
	 * @param date
	 *            ( java.core.Date )
	 * @param pattern
	 *            日期格式，如："yyyy-MM-dd" ，"yyyy-MM-dd HH:mm:ss"，"HH:mm:ss"
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
	 * 取得系统当前的时间，以Timestamp 表示
	 *
	 * @ return 返回Timestamp对象
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
	 * 获取系统绝对路径
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
	 * 转Utf8
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
	 * 转gbk
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
	 * ..* 提取含html标签的字符串的纯文本
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
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}

	/**
	 * 过滤脏词
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
	 * 日期格式化
	 *
	 * @param l
	 *            待转日期
	 * @param pattern
	 *            格式
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
	 * 查看是否是视频文件格式
	 *
	 * @param fileName
	 *            文件名
	 * @return boolean
	 */
	public static boolean getFileFormat(String fileName) {
		// 可以转换的文件格式
		String format_str = "asx|asf|mpg|wmv|3gp|mp4|mov|avi|flv|wmv9|rm|rmvb";
		// 得到文件的扩展名
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * 是否是OpenOffice能转换的格式
	 *
	 * @param fileName
	 *            文件名
	 * @return boolean
	 */
	public static boolean isDocumentType(String fileName) {
		// 能转换的文档 的扩展名
		String format_str = "pdf|xhtml|html|odt|sxw|doc|docx|rtf|wpd|txt|wikitext"
				+ "|ods|sxc|xls|xlsx|csv|tsv|odp|sxi|ppt|pptx|odg|svg";
		// 得到文件的扩展名
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * 是否为图片格式
	 *
	 * @param fileName
	 *            文件名
	 * @return boolean
	 */
	public static boolean isImagesType(String fileName) {
		// 图片格式的扩展名
		String format_str = "jpg|gif|bmp";
		// 得到文件的扩展名
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * 是否为Flash格式
	 *
	 * @param fileName
	 *            文件名
	 * @return boolean
	 */
	public static boolean isFlashType(String fileName) {
		// flash格式的扩展名
		String format_str = "swf";
		// 得到文件的扩展名
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();
		return format_str.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * 得到文件后缀名
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffixName(String fileName) {
		// 得到文件的扩展名
		String suffixName = fileName.substring(fileName.lastIndexOf("."),
				fileName.length()).toLowerCase();
		return suffixName;

	}

	/**
	 * 查看文件是否属于校验 格式
	 *
	 * @param fileName
	 *            文件名
	 * @param formatType
	 *            校验格式
	 * @return
	 */
	public static boolean isDocumentType(String fileName, String formatType) {
		String type = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length()).toLowerCase();

		return formatType.indexOf(type) >= 0 ? true : false;
	}

	/**
	 * 得到inputFile的保存位置
	 *
	 * @param inputFile
	 *            文件路径
	 * @return String
	 */
	public static String getInputFilePath(String inputFile) {
		inputFile = inputFile.replace("\\", "/");
		inputFile = inputFile.replace("//", "/");
		String path = inputFile.substring(0, inputFile.lastIndexOf("/") + 1);
		return path;
	}

	/**
	 * 更新文件的扩展名
	 *
	 * @param fileName
	 * @param format
	 * @return
	 */
	public static String getModefyFormat(String fileName, String format) {
		return fileName.substring(0, fileName.lastIndexOf(".") + 1) + format;
	}

	/**
	 * 得到文件的文件名 如d:/a/b.doc 得到 b
	 *
	 * @param file
	 *            文件 路径
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
	 * 查看文件夹下所有文件
	 *
	 * @param fileName
	 *            文件夹名
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
	 * 复制整个文件夹内容
	 *
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			// 创建目标文件夹
			(new File(newPath)).mkdirs();
			// 获取源文件夹当前下的文件或目录
			File[] file = (new File(oldPath)).listFiles();
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					// 复制文件
					copyFile(file[i],
							new File(newPath + "/" + file[i].getName()));
				}
				if (file[i].isDirectory()) {
					// 复制目录
					String sourceDir = oldPath + File.separator
							+ file[i].getName();
					String targetDir = newPath + File.separator
							+ file[i].getName();
					copyDirectiory(sourceDir, targetDir);
				}
			}

			// (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
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
			// if (temp.isDirectory()) {// 如果是子文件夹
			// copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			// }
			// }
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}
	}

	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 将一段文中的某关键字标红
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
	 * 将字符编码修改为utf-8
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
	 * 设置字符的编码格式
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
	 * 获取随机数
	 *
	 * @param count
	 *            多少位
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
	 * 获取以yyyy-MM-dd HH:mm:ss格式的  时间
	 *
	 * @return
	 */
	public static String getDateStrNow() {
		return CommonUtil.getDateStringByPattern("yyyy-MM-dd HH:mm:ss");
	}


	/**
	 * 根据用户传进来的LIst封装成 sql语句
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
	 * 简单介绍：根据文件获取文件头的类型码<br>
	 * 详细介绍：<br>
	 * @param file 需要判断的文件
	 * @return 返回文件头类型码：如Excel2003:D0CF11E0;Excel2007:504B0304等等
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
	 * 简单介绍：复制对象<br>
	 * 详细介绍：<br>
	 * @version 1.1  2014-5-9
	 * @autor wuxiao
	 */

	public  final static Object objectCopy(Object oldObj) {
		Object newObj = null;

		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(oldObj);//源对象
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi= new ObjectInputStream(bi);
			newObj = oi.readObject();//目标对象
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return newObj;
	}

	/**
	 * 简单介绍：返回一个日期列表<br>
	 * 详细介绍：根据参数，返回当天以前的length天内的一个日期列表，包含当天<br>
	 * @param length 需要的天数
	 * @return 日期列表 如果length小于0，返回null
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
	 * 判断字符串是否为数字
	 */
	public static boolean isNumber(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 生成原生SQL分页查询的SQL语句
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
	 * 将String转为bytes字节
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

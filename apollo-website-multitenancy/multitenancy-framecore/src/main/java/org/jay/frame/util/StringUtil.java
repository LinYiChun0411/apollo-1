package org.jay.frame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jay.frame.jdbc.Constants;



public class StringUtil {

	private static Log log = LogFactory.getLog(StringUtil.class);

	private StringUtil() {
	}

	public static boolean StringConvertBoolean(String key) throws Exception {
		if (key == null) {
			throw new Exception("String[" + key + "] is null.");
		}
		Object o = key;
		boolean result = false;
		if (o.equals(Boolean.FALSE)
				|| (o instanceof String && ((String) o)
						.equalsIgnoreCase("false"))) {
			result = false;
		} else if (o.equals(Boolean.TRUE)
				|| (o instanceof String && ((String) o)
						.equalsIgnoreCase("true"))) {
			result = true;
		}
		if (o.equals(Boolean.FALSE)
				|| (o instanceof String && ((String) o).equalsIgnoreCase("0"))) {
			result = false;
		} else if (o.equals(Boolean.TRUE)
				|| (o instanceof String && ((String) o).equalsIgnoreCase("1"))) {
			result = true;
		}
		return result;
	}

	/**
	 * 将字符串数据转换成长整型
	 * 
	 * @param args
	 * @return
	 */
	public static Long[] StringConvertLong(String[] args) {

		if (args == null)
			return null;
		if (args.length < 1)
			return new Long[0];
		Long[] result = new Long[args.length];
		for (int i = 0; i < args.length; i++) {
			result[i] = new Long(args[i]);
		}
		return result;

	}

	public static boolean ignoreCaseHas(String str, String subStr) {
		if (null == str || null == subStr) {
			return false;
		}
		return str.toUpperCase().indexOf(subStr.toUpperCase()) >= 0;
	}

	/**
	 * 将Unicode码字符串转为为GBK码
	 * 
	 * @param strIn
	 * @return
	 */
	public static String GBToUnicode(String strIn) {
		String strOut = null;

		if (isEmpty(strIn)) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("GBK");
			strOut = new String(b, "ISO8859_1");
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 将GBK码转换为Unicode码
	 * 
	 * @param strIn
	 * @return
	 */
	public static String unicodeToGB(String strIn) {
		String strOut = null;

		if (isEmpty(strIn)) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "GBK");
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 字符串编码类型转换
	 * 
	 * @param str
	 *            待转换的字符串
	 * @param oldCharset
	 *            待转换的字符串的编码类型
	 * @param newCharset新的编码类型
	 * @return 转换成新编码类型的字符串
	 */

	public static String encode(String str, String oldCharset, String newCharset) {
		if (isEmpty(str)) {
			return str;
		}
		String newStr = null;
		try {
			newStr = new String(str.getBytes(oldCharset), newCharset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newStr;

	}

	/**
	 * 把数组转化为字符串
	 * 
	 * @param array
	 *            字符串数组
	 * @param split
	 *            分割字符串
	 * @return string whose format is like "1,2,3"
	 */
	public static String arrayToStr(String[] array, String split) {
		if (array == null || array.length < 1) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(split);
			}
			sb.append(strnull(array[i]));
		}
		return sb.toString();

	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static Double toDouble(String source) {
		try {
			if (null == source || "".equals(source)) {
				return new Double(0);
			} else {

				return new Double(source);
			}
		} catch (NumberFormatException notint) {
			return new Double(0);
		}
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static double toDoubleMin(Object source) {
		return toDouble(source).doubleValue();
	}

	/**
	 * 转化成Double数据类型
	 * 
	 * @param source
	 * @return
	 */
	public static Double toDouble(Object source) {
		return toDouble(strnull(source, "0"));
	}

	/**
	 * 将以sgn为分隔符的字符串转化为数组
	 * 
	 * @param str
	 * @param sgn
	 * @return 更新日志：<br>
	 */
	public static String[] strConvertoArray(String str, String sgn) {
		StringTokenizer st = new StringTokenizer(str, sgn);
		String retstr[] = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			retstr[i] = st.nextToken();
		}
		return retstr;
	}

	/**
	 * 将以sgn为分隔符的字符串转化为List链表
	 * 
	 * @param str
	 *            String 要处理的字符串
	 * @param sgn
	 *            String 间隔符
	 * @return List
	 */
	public static List strConvertoList(String str, String sgn) {
		StringTokenizer st = new StringTokenizer(str, sgn);
		List result = new LinkedList();

		for (int i = 0; st.hasMoreTokens(); i++) {
			result.add(st.nextToken());
		}
		return result;
	}

	/**
	 * 1 --> 00001将整数转化为指定长度字符串(lpMaxLength为5)
	 * 
	 * @param lpInt
	 * @param lpMaxLength
	 * @return
	 */
	public static String intToStr(int lpInt, int lpMaxLength) {
		int length, i;
		String returnValue = "";

		length = Integer.toString(lpInt).length();
		if (length < lpMaxLength) {
			i = lpMaxLength - length;
			while (i > 0) {
				returnValue = returnValue + "0";
				i--;
			}
			returnValue = returnValue + Integer.toString(lpInt);
		} else {
			returnValue = Integer.toString(lpInt);
		}
		return returnValue;
	}

	/**
	 * 将字符串转化为int类型 Converts a string to integer. If fails is not throwing a
	 * NumberFormatException, instead return 0.
	 * 
	 * @param str
	 *            待转换的字符串
	 * @return int数据
	 */
	public static int toInt(String source) {
		return toInt(source, 0);
	}

	public static int toInt(String source, int defVal) {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException notint) {
			return defVal;
		}
	}

	public static int toInt(Object str) {
		return toInt(StringUtil.strnull(str), 0);
	}

	public static BigDecimal toBigDecimal(Object source) {
		return toBigDecimal(strnull(source, "0"));
	}

	/**
	 * 将字符串转为数字型
	 * 
	 * @param source
	 * @return
	 */
	public static BigDecimal toBigDecimal(String source) {
		try {
			if (null == source || "".equals(source)) {
				return new BigDecimal(0);
			} else {

				return new BigDecimal(source);
			}
		} catch (NumberFormatException notint) {
			return new BigDecimal(0);
		}
	}

	public static Long toLong(Object source) {
		return toLong(strnull(source, "0"));
	}

	/**
	 * 返回默认值
	 * 
	 * @param source
	 * @param defualt
	 * @return
	 */
	public static Long toLong(String source, Long defualt) {
		try {
			if (null == source || "".equals(source)) {
				return defualt;
			} else {

				return new Long(source);
			}
		} catch (NumberFormatException notint) {
			return defualt;
		}
	}

	/**
	 * 把字符串转化为Long
	 * 
	 * @param source
	 * @return
	 */
	public static Long toLong(String source) {
		try {
			if (null == source || "".equals(source)) {
				return new Long(0);
			} else {

				return new Long(source);
			}
		} catch (NumberFormatException notint) {
			return new Long(0);
		}
	}

	/**
	 * 取路径后的文件名，也就是路径字串最后一个斜杠后的字串
	 * 
	 * @param path
	 * @return
	 */
	public static String getPathFile(String path) {
		String substr = "";
		try {
			if (isNotEmpty(path)) {
				int i = path.lastIndexOf("/");
				substr = path.substring(i + 1).trim();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return substr;
	}

	/**
	 * 取小数点后的字串，也就是小数点后的字串
	 * 
	 * @param path
	 * @return
	 */
	public static String getLastTwo(String str) {
		String substr = "";
		try {
			if (isNotEmpty(str)) {
				int i = str.lastIndexOf(".");
				substr = str.substring(i + 1).trim();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return substr;
	}

	/**
	 * 取得文件名的文件类型(如2003001.doc-->doc)
	 * 
	 * @param lpFileName
	 * @return
	 */
	public static String getFileType(String lpFileName) {
		String lpReturnValue = "";

		if (isNotEmpty(lpFileName)) {
			int i = lpFileName.lastIndexOf(".");
			lpReturnValue = lpFileName.substring(i + 1);
		}
		return lpReturnValue;
	}

	/**
	 * 返回位于 String 对象中指定位置的子字符串
	 * 
	 * @param str
	 * @param beginIndex
	 *            指明子字符串的起始位置，该索引从 0 开始起算
	 * @param endIndex
	 *            指明子字符串的结束位置，该索引从 0 开始起算
	 * @return 如果字符串为空则返回""
	 */
	public static String getSubString(String str, int beginIndex, int endIndex) {
		String str1 = "";

		if (str == null) {
			str = "";
		}
		if (str.length() >= beginIndex && str.length() >= endIndex) {
			str1 = str.substring(beginIndex, endIndex);
		} else {
			str1 = str;
		}
		return str1;
	}

	/**
	 * 判断参数是否为空： null, "null", " ", ""
	 * 
	 * @param Str
	 * @return true/false
	 * @author chenzp mod 2006-03-28
	 */
	public static boolean isEmpty(String Str) {
		if (null == Str || "null".equals(Str.trim()) || "".equals(Str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Object str) {
		if (null == str || "null".equals(str.toString().trim())
				|| "".equals(str.toString().trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isNotEmpty(Object str) {
		return !isEmpty(str);
	}

	public static String strnull(Object str, String rpt) {
		if (isEmpty(str)) {
			return rpt;
		} else {
			return str.toString().trim();
		}
	}

	/**
	 * 将字符串逆序: 空时返回自身
	 * 
	 * @param str
	 *            ：闽A-1234
	 * @return String 4321-A闽
	 */
	public static String reverseString(String str) {
		if (isEmpty(str)) {
			return str;
		}
		int length = str.length();
		StringBuffer temp = new StringBuffer(length);
		for (int i = length - 1; i >= 0; i--) {
			temp.append(str.charAt(i));
		}
		return temp.toString();
	}

	public static String trim(String str, String defaultStr) {
		return (null == str) ? defaultStr : str.trim();
	}

	public static String trim2Empty(Object str) {
		return (null == str) ? "" : trim(str.toString(), "");
	}

	public static String trim2Empty(String str) {
		return trim(str, "");
	}

	public static String trim2Null(String str) {
		return trim(str, null);
	}

	public static String trim2Null(Object str) {
		return strnull(str, null);
	}

	/**
	 * 如果入参是null或者"",用另一入参rpt替代入参返回，否则返回入参的trim()
	 * 
	 * @param Str
	 * @return
	 */
	public static String strnull(String Str, String rpt) {
		if (isEmpty(Str)) {
			return rpt;
		} else {
			return Str.trim();
		}
	}

	/**
	 * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(String strn) {
		return strnull(strn, "");
	}

	/**
	 * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(Object str) {
		String result = "";
		if (str == null) {
			result = "";
		} else {
			result = str.toString().trim();
		}
		return result;
	}

	/**
	 * 适用于web层 为检查null值，如果为null，将返回"&nbsp;"，不为空时将替换其非html符号
	 * 
	 * @param strn
	 * @return
	 */

	public static String repnull(String strn) {
		return strnull(strn, "&nbsp;");
	}

	/**
	 * 把Date的转化为字符串，为空时将为"0000-00-00 00:00:00"
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(Date strn) {
		String str = "";

		if (strn == null) {
			str = "0000-00-00 00:00:00";
		} else {
			// strn.toGMTString();
			str = strn.toString();
		}
		return (str);
	}

	/**
	 * 把BigDecimal的转换为字符串，为空将返回0
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(BigDecimal strn) {
		String str = "";

		if (strn == null) {
			str = "0";
		} else {
			str = strn.toString();
		}
		return (str);
	}

	/**
	 * 把int的转换为字符串(不为空，只起转换作用)
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(int strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	/**
	 * 把float的转换为字符串(不为空，只起转换作用)
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(float strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	/**
	 * 把double的转换为字符串(不为空，只起转换作用)
	 * 
	 * @param strn
	 * @return
	 */
	public static String strnull(double strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	/**
	 * 0-15转化为0-F
	 * 
	 * @param bin
	 * @return
	 */
	public static char hex(int bin) {
		char retval;
		if (bin >= 0 && bin <= 9) {
			retval = (char) ('0' + bin);
		} else if (bin >= 10 && bin <= 15) {
			retval = (char) ('A' + bin - 10);
		} else {
			retval = '0';
		}
		return retval;
	}

	public static String replace(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replace(oldSub, newSub);
	}

	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		// The number 5 is arbitrary and is used as extra padding to reduce
		// buffer expansion

		StringBuffer sb = new StringBuffer(s.length() + 5 * newSub.length());

		char[] charArray = s.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (c == oldSub) {
				sb.append(newSub);
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {

			// The number 5 is arbitrary and is used as extra padding to reduce
			// buffer expansion

			StringBuffer sb = new StringBuffer(s.length() + 5 * newSub.length());

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);

				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		} else {
			return s;
		}
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs,
			boolean exactMatch) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		if (!exactMatch) {
			replace(s, oldSubs, newSubs);
		} else {
			for (int i = 0; i < oldSubs.length; i++) {
				s = s.replaceAll("\\b" + oldSubs[i] + "\\b", newSubs[i]);
			}
		}

		return s;
	}

	public static String replaceFirst(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replaceFirst(String.valueOf(oldSub), String.valueOf(newSub));
	}

	public static String replaceFirst(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		return s.replaceFirst(String.valueOf(oldSub), newSub);
	}

	public static String replaceFirst(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		return s.replaceFirst(oldSub, newSub);
	}

	public static String replaceFirst(String s, String[] oldSubs,
			String[] newSubs) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceFirst(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * 将字符串转为HTML格式输出
	 * 
	 * @param strn
	 */
	public static String formatToHTML(String strn) {
		StringBuffer dest = new StringBuffer();
		if (strnull(strn).equals("")) {
			dest.append("&nbsp;");
		} else {
			for (int i = 0; strn != null && i < strn.length(); i++) {
				char c = strn.charAt(i);
				if (c == '\n') {
					dest.append("<br>");
				} else if (c == '\'') {
					dest.append("&#39;");
				} else if (c == '\"') {
					dest.append("&#34;");
				} else if (c == '<') {
					dest.append("&lt;");
				} else if (c == '>') {
					dest.append("&gt;");
				} else if (c == '&') {
					dest.append("&amp;");
				} else if (c == 0x20) {
					dest.append("&nbsp;");
				} else {
					dest.append(c);
				}
			}
		}
		return (dest.toString());
	}

	public static String formatToHTML(String strn, int length) {
		int m = 0;
		StringBuffer dest = new StringBuffer();
		if (strnull(strn).equals("")) {
			dest.append("&nbsp;");
		} else {
			for (int i = 0; strn != null && i < strn.length(); i++) {
				m++;
				if (m == length) {
					dest.append("...");
					break;
				}
				char c = strn.charAt(i);
				if (c == '\n') {
					dest.append("<br>");
				} else if (c == '\'') {
					dest.append("&#39;");
				} else if (c == '\"') {
					dest.append("&#34;");
				} else if (c == '<') {
					dest.append("&lt;");
				} else if (c == '>') {
					dest.append("&gt;");
				} else if (c == '&') {
					dest.append("&amp;");
				} else if (c == 0x20) {
					dest.append("&nbsp;");
				} else {
					dest.append(c);
				}
			}
		}
		return (dest.toString());
	}

	public static String formate(String value, String argument) {
		return formate(value, new Object[] { argument });
	}

	public static String formate(String value, Object[] arguments) {
		if (value == null || value.length() == 0) {
			return "";
		} else {
			if (arguments != null && arguments.length > 0) {
				value = MessageFormat.format(value, arguments);
			}
			return value;
		}
	}

	/**
	 * 将BigDecimal的转为HTML格式输出
	 * 
	 * @param strb
	 */
	public static String formatToHTML(BigDecimal strb) {
		String strn = "";
		if (strb == null) {
			strn = "&nbsp;";
		} else {
			strn = strnull(strb);
		}
		return strn;
	}

	/**
	 * 将多行字符串转为有带有回车、换行的HTML格式
	 * 
	 * @param source
	 * @return
	 */
	public static String nl2Br(String source) {
		if (strnull(source).equals("")) {
			return "&nbsp;";
		}
		StringBuffer dest = new StringBuffer(source.length());
		for (int i = 0; i < source.length(); i++) {
			char c;
			c = source.charAt(i);
			if (c == '\n') {
				dest.append("<br>");
			} else if (c == 0x20) {
				dest.append("&nbsp;");
			} else {
				dest.append(c);
			}
		}
		return dest.toString();
	}

	/**
	 * 查找sourceStr中是否含有fieldStr,如果有返回true,如果没有，返回false
	 * 
	 * @param sourceStr
	 *            源字符串
	 * @param fieldStr
	 * @return boolean
	 */

	public static boolean findString(String sourceStr, String fieldStr) {
		boolean StrExist = false;
		if (sourceStr.length() == 0) {
			return StrExist;
		}
		if (sourceStr.indexOf(fieldStr) >= 0) {
			StrExist = true;
		}
		return StrExist;
	}

	/**
	 * This method takes a exception as an input argument and returns the
	 * stacktrace as a string.
	 * 
	 * @param exception
	 * @return String the string of the exception's stacktrace
	 */
	public static String getStackTrace(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		if (exception.getCause() != null) {
			exception.printStackTrace(pw);
		}
		return sw.toString();
	}

	/**
	 * 功能说明： 给字符串数组排序
	 * 
	 * @param String []
	 *            Arr 要进行排序的字符串数组
	 * @return String[] 排序后的字符串数组
	 * @author Linxf@hsit.com.cn（2004-08-09）
	 */
	public static String[] bubbleSort(String[] Arr) {
		int tag = 1;
		for (int i = 1; i < Arr.length && tag == 1; i++) {
			tag = 0;
			for (int j = 0; j < Arr.length - i; j++) {
				if (Arr[j].compareTo(Arr[j + 1]) > 0) {
					String temp = Arr[j];
					Arr[j] = Arr[j + 1];
					Arr[j + 1] = temp;
					tag = 1;
				}
			}
		}
		return Arr;
	}

	/**
	 * 功能说明：依据ValueArr数组的排序，为ContentArr排序
	 * 
	 * @param String []
	 *            ValueArr 排序依据的字符串数组
	 * @param String []
	 *            ContentArr 要排序的内容数组
	 * @return String[] 排序后的字符串数组
	 * @author Linxf@hsit.com.cn（2004-08-09）
	 */
	public static String[] bubbleSort(String[] ValueArr, String[] ContentArr) {
		int tag = 1;
		for (int i = 1; i < ValueArr.length && tag == 1; i++) {
			tag = 0;
			for (int j = 0; j < ValueArr.length - i; j++) {
				if (ValueArr[j].compareTo(ValueArr[j + 1]) > 0) {
					String temp1 = ValueArr[j];
					String temp2 = ContentArr[j];
					ValueArr[j] = ValueArr[j + 1];
					ContentArr[j] = ContentArr[j + 1];
					ValueArr[j + 1] = temp1;
					ContentArr[j + 1] = temp2;
					tag = 1;
				}
			}
		}
		return ValueArr;
	}

	/**
	 * 功能说明：冒泡排序
	 * 
	 * @param int[]
	 *            Arr 要进行排序的数组
	 * @return int[] Arr 排序后的数组
	 * @author Linxf@hsit.com.cn（2004-08-09）
	 */
	public static int[] bubbleSort(int[] Arr) {
		int tag = 1;
		for (int i = 1; i < Arr.length && tag == 1; i++) {
			tag = 0;
			for (int j = 0; j < Arr.length - i; j++) {
				if (Arr[j] > Arr[j + 1]) {
					int temp = Arr[j];
					Arr[j] = Arr[j + 1];
					Arr[j + 1] = temp;
					tag = 1;
				}
			}
		}
		return Arr;
	}

	/**
	 * 将空字符串转为"0"字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToZero(String str) {
		if (str == null || str.equals("")) {
			str = "0";
		}
		return str;
	}

	/**
	 * 首字母大写
	 * 
	 * @param word
	 *            字符串
	 */
	public static String upperFirstLetter(String word) {
		return (new StringBuffer(word.substring(0, 1).toUpperCase())
				.append(word.substring(1, word.length()))).toString();
	}

	/**
	 * 如果str的长度不足len位,就把str补到len位(前面补0),补后的格式为:0..0str
	 * 
	 * @param len要补足的长度
	 *            ,str要补足的字符串
	 * @return 补完0后的字符串(格式为:0..0str)
	 * @author
	 */
	public static String appendZeroBefore(int len, String str) {
		StringBuffer resultStr = new StringBuffer(str);
		if (str != null && str.length() < len) {
			int zeroLen = len - str.length();
			StringBuffer zeroStr = new StringBuffer();
			for (int i = 0; i < zeroLen; i++) {
				zeroStr.append("0");
			}
			resultStr = zeroStr.append(resultStr);
		}
		return resultStr.toString();
	}

	public static int getLenth(String str) {
		int counter = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c < 255) {
				counter++;
			} else {
				counter = counter + 2;
			}
		}
		return counter;
	}

	/**
	 * 是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		for (int i = 0, len = str.length(); i < len; i++) {
			char ch = str.charAt(i);
			if (ch > '9' || ch < '0') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转变为小写
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		if (StringUtil.isNotEmpty(str)) {
			return str.toLowerCase();
		}
		return "";
	}

	/**
	 * 字符串转变为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {
		if (StringUtil.isNotEmpty(str)) {
			return str.toUpperCase();
		}
		return "";
	}

	/**
	 * 格式化流水号，将数字转换为36进制，
	 * 
	 * @param seq
	 *            流水号
	 * @param length
	 *            位数
	 * @return AA001
	 */
	public static String formatSeq(long seq) {
		return formatSeq(seq, 5);
	}

	/**
	 * 格式化流水号，将数字转换为36进制，
	 * 
	 * @param seq
	 *            流水号
	 * @param length
	 *            位数
	 * @return AA001
	 */
	public static String formatSeq(long seq, int length) {
		if (Validator.isNull(seq)) {
			seq = 1;
		}
		String strNum = StringUtil.toUpperCase(Long.toString(seq, 36));

		return StringUtil.appendZeroBefore(length, strNum);
	}

	public static String capitalize(String str) {
		if (StringUtil.isNotEmpty(str)) {
			return WordUtils.capitalize(StringUtil.toLowerCase(str));
		}
		return "";
	}

	public static String[] split(String s) {
		return split(s, StringPool.COMMA);
	}

	public static String[] split(String s, String delimiter) {
		if ((Validator.isNull(s)) || (delimiter == null)
				|| (delimiter.equals(StringPool.BLANK))) {

			return new String[0];
		}

		s = s.trim();

		if (!s.endsWith(delimiter)) {
			StringBuilder sb = new StringBuilder();

			sb.append(s);
			sb.append(delimiter);

			s = sb.toString();
		}

		if (s.equals(delimiter)) {
			return new String[0];
		}

		List<String> nodeValues = new ArrayList<String>();

		if (delimiter.equals(StringPool.NEW_LINE)
				|| delimiter.equals(StringPool.RETURN)) {

			try {
				BufferedReader br = new BufferedReader(new StringReader(s));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}

				br.close();
			} catch (IOException ioe) {
				log.error(ioe.getMessage());
			}
		} else {
			int offset = 0;
			int pos = s.indexOf(delimiter, offset);

			while (pos != -1) {
				nodeValues.add(new String(s.substring(offset, pos)));

				offset = pos + delimiter.length();
				pos = s.indexOf(delimiter, offset);
			}
		}

		return nodeValues.toArray(new String[nodeValues.size()]);
	}

	public static boolean[] split(String s, boolean x) {
		return split(s, StringPool.COMMA, x);
	}

	public static boolean[] split(String s, String delimiter, boolean x) {
		String[] array = split(s, delimiter);
		boolean[] newArray = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			boolean value = x;

			try {
				value = Boolean.valueOf(array[i]).booleanValue();
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static double[] split(String s, double x) {
		return split(s, StringPool.COMMA, x);
	}

	public static double[] split(String s, String delimiter, double x) {
		String[] array = split(s, delimiter);
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			double value = x;

			try {
				value = Double.parseDouble(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static float[] split(String s, float x) {
		return split(s, StringPool.COMMA, x);
	}

	public static float[] split(String s, String delimiter, float x) {
		String[] array = split(s, delimiter);
		float[] newArray = new float[array.length];

		for (int i = 0; i < array.length; i++) {
			float value = x;

			try {
				value = Float.parseFloat(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static int[] split(String s, int x) {
		return split(s, StringPool.COMMA, x);
	}

	public static int[] split(String s, String delimiter, int x) {
		String[] array = split(s, delimiter);
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			int value = x;

			try {
				value = Integer.parseInt(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static long[] split(String s, long x) {
		return split(s, StringPool.COMMA, x);
	}

	public static long[] split(String s, String delimiter, long x) {
		String[] array = split(s, delimiter);
		long[] newArray = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			long value = x;

			try {
				value = Long.parseLong(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static short[] split(String s, short x) {
		return split(s, StringPool.COMMA, x);
	}

	public static short[] split(String s, String delimiter, short x) {
		String[] array = split(s, delimiter);
		short[] newArray = new short[array.length];

		for (int i = 0; i < array.length; i++) {
			short value = x;

			try {
				value = Short.parseShort(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static List split2List(String tar) {
		return split2List(tar, ",");
	}

	public static List split2List(String tar, String r) {
		List result = new ArrayList();
		if (isEmpty(tar)) {
			return result;
		}
		String strs[] = tar.split(r);
		for (int i = 0; i < strs.length; i++) {
			result.add(strs[i]);
		}
		return result;
	}

	public static String mergeList(List list) {
		return mergeList(list, ",");
	}

	public static String mergeList(List list, String r) {
		if (list == null) {
			return null;
		}
		StringBuffer result = null;
		for (int j = 0, size = list.size(); j < size; j++) {
			String s = trim2Empty(list.get(j));
			if (isNotEmpty(s)) {
				if (result == null) {
					result = new StringBuffer(s);
				} else {
					result.append(r);
					result.append(s);
				}
			}
		}

		if (result != null) {
			return result.toString();
		} else {
			return null;
		}
	}

	public static String mergeListMap(List list, String key) {
		return mergeListMap(list, key, ",");
	}

	public static String mergeListMap(List list, String key, String spltstr) {
		StringBuffer result = null;
		Map tmp = null;
		if (list == null) {
			return null;
		}
		for (int j = 0, size = list.size(); j < size; j++) {
			tmp = (Map) list.get(j);
			String s = StringUtil.trim2Empty(tmp.get(key));
			if (isNotEmpty(s)) {
				if (result == null) {
					result = new StringBuffer(s);
				} else {
					result.append(spltstr);
					result.append(s);
				}
			}
		}

		if (result != null) {
			return result.toString();
		} else {
			return null;
		}
	}

	public static String[] getListMapByKey(List list, String key) {
		String[] result = null;
		if (list != null) {
			int size = list.size();
			result = new String[size];
			Map tmp = null;
			for (int j = 0; j < size; j++) {
				tmp = (Map) list.get(j);
				result[j] = StringUtil.trim2Empty(tmp.get(key));
			}
		}
		return result;
	}

	/**
	 * list 转换List 为 Map<groupKey,groupLs>
	 * 
	 */
	public static Map<String, List> groupList(List sourceLs, String groupKey) {
		Map result = new HashMap();
		if (null != sourceLs && sourceLs.size() > 0) {
			String group = null;
			Map tmp = null;
			List groupLs = null;
			for (int i = 0, len = sourceLs.size(); i < len; i++) {
				tmp = (Map) sourceLs.get(i);
				group = StringUtil.trim2Empty(tmp.get(groupKey));
				groupLs = (List) result.get(group);
				if (groupLs == null) {
					groupLs = new ArrayList();
				}
				groupLs.add(tmp);
				result.put(group, groupLs);
			}
		}
		return result;
	}

	public static Map groupList(List sourceLs, String groupKey, String valueKey) {
		Map result = new HashMap();
		if (null != sourceLs && sourceLs.size() > 0) {
			String group = null, value = null;
			Map tmp = null;
			List groupLs = null;
			for (int i = 0, len = sourceLs.size(); i < len; i++) {
				tmp = (Map) sourceLs.get(i);
				group = StringUtil.trim2Empty(tmp.get(groupKey));
				value = StringUtil.trim2Empty(tmp.get(valueKey));
				groupLs = (List) result.get(group);
				if (groupLs == null) {
					groupLs = new ArrayList();
				}
				groupLs.add(value);
				result.put(group, groupLs);
			}
		}
		return result;
	}

	public static boolean endsWith(String s, char end) {
		return endsWith(s, (new Character(end)).toString());
	}

	public static boolean endsWith(String s, String end) {
		if ((s == null) || (end == null)) {
			return false;
		}

		if (end.length() > s.length()) {
			return false;
		}

		String temp = s.substring(s.length() - end.length(), s.length());

		if (temp.equalsIgnoreCase(end)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean startWith(String s, char end) {
		return startWith(s, (new Character(end)).toString());
	}

	public static boolean startWith(String s, String start) {
		if ((s == null) || (start == null)) {
			return false;
		}

		if (start.length() > s.length()) {
			return false;
		}

		String temp = s.substring(0, start.length());

		if (temp.equalsIgnoreCase(start)) {
			return true;
		} else {
			return false;
		}
	}

	public static Map<String, Map> list2Map(List sourceLs, String key) {
		Map<String, Map> result = new HashMap<String, Map>();
		if (null != sourceLs && sourceLs.size() > 0) {
			String group = null;
			Map tmp = null;
			String val = null;
			for (int i = 0, len = sourceLs.size(); i < len; i++) {
				tmp = (Map) sourceLs.get(i);
				group = StringUtil.trim2Empty(tmp.get(key));

				result.put(group, tmp);
			}
		}
		return result;
	}

	/**
	 * <Map>??Map<key,value>
	 * 
	 * @param sourceLs
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, String> list2Map(List sourceLs, String key,
			String value) {
		Map<String, String> result = new HashMap<String, String>();
		if (null != sourceLs && sourceLs.size() > 0) {
			String group = null;
			Map tmp = null;
			String val = null;
			boolean mix = StringUtils.indexOf(value, ",") != -1;
			for (int i = 0, len = sourceLs.size(); i < len; i++) {
				tmp = (Map) sourceLs.get(i);
				group = StringUtil.trim2Empty(tmp.get(key));
				if (mix) {
					val = getMixComboxValue(tmp, value);
				} else {
					val = StringUtil.trim2Empty(tmp.get(value));
				}
				result.put(group, val);
			}
		}
		return result;
	}

	// 如果valueColumn用,隔开字段则用-连接所有的显示值
	private static String getMixComboxValue(Map map, String valueColumn) {
		String[] vs = valueColumn.split(",");
		String value = "";
		for (String v : vs) {
			String _value = StringUtil.trim2Empty(map.get(v));
			value += "-" + _value;
		}
		value = value.replaceFirst("-", "");
		return value;
	}

	/**
	 * 
	 * @param collection
	 * 
	 * @return
	 */
	public static String buildCollection2Msg(Collection collection) {
		return buildCollection2Msg(collection, 5);
	}

	/**
	 * 
	 * @param collection
	 * @param unitNum
	 * 
	 * @return
	 */
	public static String buildCollection2Msg(Collection collection, int unitNum) {
		if (collection == null || collection.isEmpty()) {
			return null;
		}

		if (unitNum <= 0) {
			unitNum = 5;
		}

		StringBuffer msgSB = new StringBuffer(collection.size() * 16);
		String temp = null;
		Iterator iter = collection.iterator();
		for (int i = 0; iter.hasNext();) {
			temp = (String) iter.next();
			if (StringUtils.isNotEmpty(temp)) {
				if (StringUtils.isNotEmpty(msgSB.toString())) {
					msgSB.append(Constants.SEPATOR_ITEM);
				}

				if (i != 0 && i % unitNum == 0) {
					msgSB.append(Constants.SEPATOR_SINGLE_LINE);
				}

				String value = StringUtil.trim2Empty(temp);

				msgSB.append(value);

				i++;
			}
		}

		return msgSB.toString();
	}

	public static String buildCollection2Msg(Collection collection, Object key) {
		return buildCollection2Msg(collection, key, 5);
	}

	/**
	 * 
	 * @param collection
	 * @param key
	 * @param unitNum
	 * @return
	 */
	public static String buildCollection2Msg(Collection collection, Object key,
			int unitNum) {
		return buildCollection2Msg(collection, new Object[] { key }, unitNum);
	}

	public static String buildContent(String content, int limitlength) {
		if (content.length() > limitlength) {
			return content.substring(0, limitlength) + "...";
		} else {
			return content;
		}
	}

	/**
	 * 替换空格
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}

	/**
	 * 对逗号分隔的字符串排序
	 */
	public static String sortStr(String str) {
		return sortStr(str, ",");
	}

	/**
	 * 对spltstr分隔的字符串排序
	 */
	public static String sortStr(String str, String spltstr) {
		if (str == null || str.indexOf(spltstr) == -1) {
			return str;
		}

		// 去掉空格
		str = replaceBlank(str);

		String[] arr = strConvertoArray(str, spltstr);
		Arrays.sort(arr);
		return arrayToStr(arr, ",");
	}

	public static String safePath(String path) {
		return replace(path, StringPool.DOUBLE_SLASH, StringPool.SLASH);
	}

	public static String shorten(String s) {
		return shorten(s, 20);
	}

	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	public static String shorten(String s, String suffix) {
		return shorten(s, 20, suffix);
	}

	public static String shorten(String s, int length, String suffix) {
		if ((s == null) || (suffix == null)) {
			return null;
		}

		if (s.length() > length) {
			for (int j = length; j >= 0; j--) {
				if (Character.isWhitespace(s.charAt(j))) {
					length = j;

					break;
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append(s.substring(0, length));
			sb.append(suffix);

			s = sb.toString();
		}

		return s;
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.capitalize("sdsadfsdfsd, sdfad"));
		System.out.println(sortStr("1, 2, 3"));
		System.out.println(sortStr("1, 5, 3"));
		System.out.println(sortStr("6, 7, 3"));
		System.out.println(sortStr("8, 4, 3"));
		System.out.println(StringUtil.replace("abc'd sse \r  cedd \n \\",
				new String[] { "'", "\r", "\n", "\\" }, new String[] { "\"",
						"	", "", "\\\\" }));

		System.out.println(StringUtil.startWith("/erp/service/cost/d", "/erp"));
		System.out.println(StringUtils.substringAfter("/erp/service/cost/d",
				"/erp"));
		System.out.println(StringUtil.startWith("/service/cost/d", "/"));
		System.out.println(StringUtils.substringAfter("/erp/service/cost/d",
				"/erp/"));

		// System.out.println(reverseString(null));
		// System.out.println(reverseString(""));
		// System.out.println("闽A-W1245 -- " + reverseString("闽A-W1245"));
		// System.out.println("闽D-31885 -- " + reverseString("闽D-31885"));

	}

	/**
	 * 对大文本框的内容过滤html标签内容
	 */
	public static String getTextFromHtml(String html) {
		if (html == null) {
			return null;
		}
		// 得到body标签中的内容
		StringBuffer buff = new StringBuffer();
		int maxindex = html.length() - 1;
		int begin = 0;
		int end;
		// 截取>和<之间的内容
		while ((begin = html.indexOf('>', begin)) < maxindex) {
			end = html.indexOf('<', begin);
			if (end - begin > 1) {
				buff.append(html.substring(++begin, end));
				begin = end + 1;
			} else {
				break;
			}
		}
		;
		return buff.toString();
	}

	private static int cryptTable[] = { 0x55C636E2, 0x76F8C1B1, 0x3DF6965D,
			0x15F261D3, 0x193AA698, 0x2BE0170, 0xB39459D2, 0x30C1237B,
			0xA84C2D0D, 0x5496F7D5, 0x584B71D4, 0x3F1E26D9, 0xF7F6686A,
			0x50F185A6, 0x4208931B, 0x2984F00E, 0xE1811BE7, 0x9FACA186,
			0x33BA41D5, 0x7A4106EC, 0xB682C809, 0x56ED1C4D, 0x7C400076,
			0x39791013, 0x83E86840, 0x91CF876B, 0xC9D18AF8, 0x85ACEF8A,
			0x4BAFF44E, 0xF49B6F8C, 0x775A9C24, 0xE828060E, 0xF4B6D220,
			0xEEEEAA1C, 0xBA3D9A51, 0x597D5CA5, 0x91CADA2E, 0xDDC3481C,
			0xE0488314, 0x55F54DDD, 0x5A1AFEB2, 0x5CCBF9B7, 0x439EAEC4,
			0x559CCD2B, 0x2DE51372, 0xD3E9CE0D, 0xF1A552D4, 0x717BBE63,
			0xA104F445, 0x9AFB571B, 0x32CDCDF8, 0x3C9D4343, 0x8259FAA7,
			0x636F37C4, 0x3AB35406, 0xB18201CD, 0xE1008785, 0xD682BD68,
			0x264D5E3B, 0xAD64FF1F, 0x3CCE05CE, 0x2ADFEEBF, 0x932A8610,
			0x75C17F35, 0xC77764FE, 0xA55D13BE, 0xF90240A0, 0x38BF0A7F,
			0x75424131, 0x7F864466, 0xBB0AFE71, 0x3D08CCE7, 0x6212E2C7,
			0xBB115739, 0x416D9CD4, 0x9376AB33, 0x426E6FB0, 0x88EE3168,
			0x74FE755A, 0xA2489278, 0x848F645E, 0x573C984F, 0xB3C27047,
			0x7D3A7AA6, 0xE30B86E4, 0x87E45A45, 0x13A843AE, 0x6133CB1E,
			0x2D8BE784, 0xB5231B6, 0x45B86017, 0x406B7439, 0x15295506,
			0x83ED154A, 0xBA67AED6, 0x5E656CA8, 0x636085D9, 0x5AE66246,
			0xFC2673D8, 0xE5AB2467, 0x1B851A95, 0x5000BA9A, 0x1D208DDD,
			0x44DD4A7F, 0x60028B90, 0x2542DBD7, 0xAD4A47AB, 0xA91D3DBA,
			0x79056CC8, 0x1D9E20C6, 0xAB4DF9E4, 0xAF001D8D, 0xC315968D,
			0x82CC8831, 0x2A7C692A, 0x5976AE9B, 0x419907AE, 0x6AA2664B,
			0x9D3C1B7C, 0x6B691CDB, 0x6C317E7D, 0x185C8F96, 0x716D0CCA,
			0xE9453BFA, 0x9E51F817, 0xCDDD2F94, 0xE5E9ED4D, 0x891F4956,
			0x24315694, 0x9B763DEC, 0x3C3C13E5, 0x61764133, 0x80866BFF,
			0x661F3253, 0x3D29323F, 0x335B1371, 0xD3703D97, 0xBD56C847,
			0x75549F5C, 0xCFE12B68, 0x31A592CA, 0xAC98F0C6, 0x9093425A,
			0xBB2B63ED, 0x754B459B, 0x51E4FC4C, 0xDBC3A37C, 0x28DD9E87,
			0x67E00D96, 0xA2238047, 0xF7DB5B2F, 0x85F010C4, 0x84EF3E08,
			0xF48966C7, 0xD9C55514, 0x8ABDBE41, 0x90491E32, 0x690A49D6,
			0xD7BEA56, 0x6BDCFFC1, 0x8BEAA674, 0xF12E18BF, 0x6A7EFF82,
			0xC25F92EF, 0x693E6340, 0x20D6B319, 0xC88C96E1, 0xABCFE400,
			0xA947A79D, 0x82383FE7, 0xDE6C9A9D, 0xD3FBD6D9, 0x3D3BE5CA,
			0xDE4ADF6F, 0x1916EA5F, 0xC5AC84E5, 0xE3C28B08, 0x381B650C,
			0xAC0F0342, 0xEC7BCD59, 0x445A5FEB, 0xD5BF08CC, 0x4B7C8622,
			0xD3EB246B, 0x72DE165A, 0x94958CB0, 0xB1E78859, 0x3E0246F3,
			0xA4AA118E, 0xE79A1617, 0x1E7D3847, 0x2546DDCF, 0xA3561654,
			0x3C3E6A46, 0x8EC86234, 0xF35D29B0, 0xB030B200, 0x9488865C,
			0x457F4441, 0xA8F0D284, 0xCA5CCEDA, 0xAAFD2811, 0x3AEF1BF2,
			0xA50A406F, 0x20C90226, 0xB732C8B5, 0x55B22D21, 0x5E5D68A2,
			0x6C508D9F, 0x7BF98884, 0xFDCC41DD, 0xD38BF567, 0xD32F1DDC,
			0xE9AC18E7, 0x28A58331, 0xEDCEC16, 0x469C7A2B, 0x51972BF0,
			0x1ECDB4BA, 0x3EC3FA6E, 0x9D01FEAE, 0x5AD05792, 0x177A213B,
			0x39AC7E3A, 0x4CE0895B, 0x1165D38E, 0xA1A5981E, 0x469375C2,
			0x7FB304FA, 0xC353B4D0, 0x9EE193C8, 0x7DFB8384, 0x37640BD0,
			0x6F38F8E8, 0x33EF064F, 0xBF33B13C, 0x34D1CA0A, 0xFC3324C8,
			0x4AECEA6D, 0x21E5E210, 0x61BC0DFC, 0x7EB0DBE0, 0x7091A09,
			0x61035E73, 0xC8BB589D, 0xEF3E7BE9, 0xD61CE0F6, 0x2D63D3FB,
			0x81708907, 0xE85DCAB2, 0xF8D4D4C5, 0x398068B7, 0x2153F023,
			0xEBC07205, 0xAC65829F, 0xC79B7694, 0xE6406D1F, 0x48223875,
			0x90FD7614, 0xA7BF92D0, 0x5A255943, 0x95AE6B47, 0x61A55826,
			0xB52D217F, 0x5A6174D, 0xB3DD20A, 0xE4281230, 0x8C136538,
			0x6C4DE195, 0x25A50C2E, 0x9D1AB5A3, 0xB0843061, 0x49F71D98,
			0x1DD49084, 0xE5C78777, 0xCFA8BA57, 0xA70A3A68, 0x84C7D51E,
			0x64EE482C, 0x3D75021F, 0x5E6D7069, 0xE340F625, 0x85551A73,
			0x94C7A521, 0x4BAA9C98, 0xCB89B731, 0x72DCBFFD, 0x13D604C5,
			0x540C09D8, 0x23BDC884, 0x3DC0D15B, 0x8EB8AFCD, 0xD701A626,
			0x75DF8DD5, 0x9653BBD7, 0xD4D7E7E, 0x18B6661F, 0x87B844CA,
			0x414131F7, 0xBADCE7F5, 0x97E37F2B, 0x17EF5A5C, 0x741EB29D,
			0x3698FD76, 0xC283A484, 0xFEFC2BB1, 0xC5B22, 0x2A2C977C,
			0xF784DB4F, 0xC040DF2E, 0xF95B16B5, 0x6BA13836, 0xC797CA03,
			0xF8C97A03, 0x9370A841, 0x27A55B93, 0x6165E383, 0x6C4085D7,
			0x48F39B9, 0x2F316022, 0x45F22729, 0x74481C5B, 0x2DACF79B,
			0x3BF4F0BD, 0x36EED231, 0x4C986630, 0xE56F0711, 0x734FA2EB,
			0x8CB50992, 0xAC2CBC0C, 0x7C666862, 0xA26F5024, 0xCC290557,
			0x9B58D9EE, 0x13C0A49B, 0x5FA40847, 0x5FF22E60, 0xFA1E75E4,
			0xE5AB79CC, 0xCDD12997, 0xA3F16205, 0x31A5E829, 0x6B29A27,
			0x9A5F6052, 0x7FE91B2, 0x791B7764, 0xA1094BF0, 0xBECE2A7A,
			0xBD9591B0, 0xCD7EABCD, 0x386B36D6, 0xC680EC6C, 0x70A4554B,
			0xFAD2232B, 0x2C01271D, 0x6E6C3FEF, 0x8CF327D7, 0xC935942E,
			0x5A632254, 0x18432DF8, 0xC75855DB, 0xEBF1348A, 0xA764BBC1,
			0x286E618, 0x599C6BC7, 0x4ABC7DC7, 0x6A227D2F, 0x1FE391D6,
			0x8AD3C8F7, 0x75E93D5A, 0x4A328F9B, 0x74065184, 0x7807F0C2,
			0xE4060176, 0xB67A6EE2, 0xCEF20C0F, 0x8DF65112, 0x40606ED9,
			0x754C4617, 0x8E738E16, 0x60B88F07, 0x2BBD05EE, 0xE5153086,
			0x5C10490B, 0xFF9073FD, 0xF7BB4B8F, 0xE4D00ED6, 0xE91D7DD2,
			0x6F7D6FFF, 0xAF77026A, 0x830B5192, 0x2980EE1A, 0xED5D3BA9,
			0x2187B42A, 0xF86EA2FC, 0x94F711EC, 0x6AE1DA73, 0xAA14B64A,
			0x5775095B, 0x91509EA3, 0x20250752, 0xE84614DA, 0x83B24DD9,
			0x2F4C663, 0x33A78DC6, 0x399D21A3, 0x6C9906AB, 0xEC1FF5CD,
			0x5A5DCA06, 0x4F79234A, 0xE5C0840D, 0xCF8E02DB, 0xBA33EAD3,
			0xFE4AD4C7, 0x3A7535BC, 0xE76CFFA5, 0xD3723E97, 0xE4EF735C,
			0x53E19F7D, 0x3539FCB1, 0x624FAB29, 0x92F66CAF, 0xBC062438,
			0x59FF46B5, 0x3103EE52, 0x5DF133E6, 0xAC8491C7, 0xD8BFD523,
			0xBCC42BA5, 0x4F6F1E69, 0x83E0B9B8, 0xAEC65696, 0x473D1E04,
			0xFD2F4A97, 0x6BB3EBBC, 0xC5796BFB, 0xB98997CF, 0x2007F8A7,
			0xBED6D905, 0x4CB77555, 0x4A7AB2D0, 0xFA16C762, 0xB02903ED,
			0x95629B6B, 0x8DD1E999, 0xBA59A821, 0x6D73C65F, 0x86EA8ADA,
			0x21A1C0DB, 0x2ADE439D, 0x3A81E4C, 0x205D22A6, 0x95AB69CF,
			0xAA10B45D, 0x11521FAE, 0xCD3ADFDB, 0x4DD3AAA5, 0xFD1F9809,
			0xE6EF6D58, 0xB94D2545, 0x32B26B8C, 0x2DEB6BC0, 0x9CB3D8BB,
			0x2892CF4D, 0x8DDE9ABD, 0x8E35C533, 0x9F37686C, 0x51F45958,
			0x9FED6C10, 0x1909393F, 0x9E6300E9, 0x71A5282B, 0x9CDD4276,
			0x1E386BF7, 0xB792A23D, 0x8CF92AC5, 0x376BB9E0, 0xC245865E,
			0x9BE0C6E8, 0x749C455B, 0x880D18EB, 0x7FFF2A1B, 0x8F0C836B,
			0x2B2F15EF, 0xB5B60F2C, 0x131A53B3, 0xDE67982F, 0x4EE7DC07,
			0x19F5AC7B, 0x380459CE, 0x2ED2DC64, 0x9CBF33CE, 0xF6368D9D,
			0x7AFF0E72, 0xDAD5820, 0xB23257C1, 0x2E6DAB37, 0xEF2C1DC1,
			0x31DA576F, 0xB130845B, 0xA06450C1, 0x6E3424B9, 0xEE56B54B,
			0x30252CB4, 0x291CBD52, 0x1B92CB8E, 0xEE143BC, 0xBD62CE2F,
			0x577960AC, 0xDE9A5BB7, 0x72ED730E, 0x832A60D9, 0xF4916AAD,
			0x166E9E5A, 0x51DEF961, 0x19A685F0, 0xBB6329E1, 0xC81CB594,
			0xA9374A61, 0x515B6408, 0x82836483, 0x13F6BEFD, 0x41729F49,
			0x71369C96, 0xCA6E823E, 0x42D94E8A, 0x5965FB84, 0x24BEF0A4,
			0x7FF826AE, 0x382E6E74, 0xEE9BD6F6, 0xF60B233C, 0xDEF487A9,
			0xE8175326, 0xEEBE3D71, 0x556D0B6A, 0x3D695183, 0x222E05B8,
			0xCABBFD33, 0x4C8F0C6A, 0xBA65589A, 0x433224A1, 0x8D3BF5C6,
			0x191190E, 0xE676DCEA, 0xDE24CCE4, 0xB5D9CAE5, 0x11B55009,
			0x699D3C3E, 0x14E1DC7C, 0x53329F6C, 0x82459BAB, 0xAD09D2B3,
			0x36B40B22, 0x6F7FC634, 0xC754FE8B, 0x9F21B311, 0x19DB9FD1,
			0xB3950513, 0xCF85A943, 0x503D2DC7, 0xAF6C5247, 0xD7427085,
			0x9B889BFA, 0xD39EA96E, 0x10027BA4, 0xB447B13A, 0x33DBFC8B,
			0xA52A5007, 0x136E7C93, 0xD3B60A8B, 0x7B2676C3, 0x526B9378,
			0xAC290FED, 0x7164B304, 0x68E68D83, 0xC38979CD, 0x790E1BC8,
			0x3B4E4A4F, 0xF32F1333, 0xA9128A9, 0x8526AE25, 0xB2998A00,
			0xB753D8D6, 0x35C34034, 0x595FA35F, 0xC550AD5B, 0xA5641703,
			0x3C531F22, 0xDE39D721, 0xB03B5BE, 0x685099A7, 0x676D249,
			0x582F6427, 0x91A87439, 0x150A45C4, 0x65E9C2BD, 0x6B9185CC,
			0xA9CD93A9, 0xC410111F, 0xB1629CCE, 0xE5C6DC36, 0x30E4348F,
			0x546E39AE, 0x29F17AAC, 0xE5F7497B, 0xE10B37A9, 0x82C52F65,
			0x242FAAD2, 0x1316A6FF, 0x8A7098A4, 0x88016878, 0x57C7DC24,
			0xD2E0F747, 0x12F194EE, 0xB8233E69, 0xCE81D4E4, 0x489C1ECD,
			0x9F6325D, 0x420B9499, 0x8EA0F978, 0x24D6FC80, 0x9FCAB02A,
			0x59D48719, 0xF72DB0DC, 0x5B579970, 0x4106152D, 0x56D61117,
			0xAD7EB66E, 0x690B9F93, 0xEAB14318, 0x6D4F5F90, 0xFE869CAC,
			0xD5512878, 0x17D14BB2, 0x4B28B263, 0xC4DC74BE, 0x55FC5140,
			0x56DEBF9D, 0x8F931AB8, 0xB6766CEF, 0xDB48676C, 0x7FBBB382,
			0x5107E5A5, 0x217500BC, 0x6782877, 0x6CB569B7, 0x9E5AFC79,
			0xF1C00AA4, 0x875413F8, 0x155C6DD0, 0xF3BF598F, 0x10047C99,
			0x814CCCA8, 0x98B2E43D, 0xC711333C, 0x42B08D9, 0xFC9F5984,
			0x600D90F0, 0xC51F9571, 0xF819CEDF, 0x2CCB2DE, 0x56587E2D,
			0x9BE97619, 0x54CEBDCA, 0xEB1D68, 0xB1056F65, 0xB98193F0,
			0x915FA5F2, 0x719CC79, 0xD6FFFA6E, 0x47994AF4, 0x98FE5E8E,
			0x2B5628DD, 0xF3C7080D, 0x439E5962, 0xFA141BA4, 0x29B15B6B,
			0xA33D5F5A, 0xE4286771, 0xD765D6DB, 0x9376AB2E, 0x9561F055,
			0x595DF7C1, 0xA3EAB3CD, 0xCB0BCEE9, 0x7A76737, 0xBB0CAA25,
			0x6966215D, 0x4A6B00E0, 0x6D3C5647, 0x75E7E6FC, 0x1E4ECC15,
			0x50EC8337, 0x11CF0759, 0x965466F3, 0x449D80A1, 0x23F5393B,
			0xF1D21372, 0x7E897379, 0xCA983C9, 0x3B7259D, 0x845B458, 0xEE2EEFB,
			0x5B32876C, 0x74ECC1CE, 0xF6DF358A, 0xCEFF67CA, 0xAD9E70B7,
			0x5E8CD4F6, 0xFC0563B6, 0x5A75D5B9, 0xB099900C, 0xAB0D2FE4,
			0xCEDFA64, 0x42B08FEE, 0x47286923, 0xB1564F, 0xCF277B5D,
			0x919AC2C7, 0xC5B38853, 0x3B1A30EF, 0x39EEF3D1, 0x62585A2C,
			0xB214F3B3, 0xFE502CEB, 0xEEBE3D6A, 0xFCC1BF84, 0x835A7844,
			0xE89C38C, 0x7B432FAF, 0x9DB1AA00, 0xAC8893B5, 0x74B1FA6B,
			0xF0C43A39, 0xC309E610, 0x7A90D9, 0x6484BF0E, 0x49BAFFD5,
			0xEAE10522, 0x2C3997D8, 0x24667071, 0x91C02AB3, 0x2EA9C864,
			0x835BCE06, 0x43774654, 0x19C73CF, 0x8C0C0C70, 0x129311A8,
			0x9EEC43C2, 0x15BD9D2C, 0x69039BCD, 0x686FA8C6, 0xBDFA1867,
			0xEA26A9D6, 0xED6A420D, 0x95900744, 0xE171BED6, 0x83CA5997,
			0x69531821, 0xC7FF520C, 0x6518B1EB, 0xDFAE37DF, 0x9D1DB719,
			0x6725B24A, 0xB8A97FD1, 0x6905F202, 0xD5A1A4E7, 0x84BB79E6,
			0xDA81B0E2, 0x5E4D60CC, 0xEE3951B2, 0xE3EB49A1, 0x9E3F99F2,
			0xD5B4AE33, 0xB9738D11, 0xE141FCA9, 0x5E6014E0, 0x313F6101,
			0x80F99FB, 0xDA2181FF, 0x797FA832, 0x205B21AC, 0x1B99245B,
			0x15A83DAF, 0x73AC2597, 0x5A95E55B, 0xFD58B3DA, 0xD15D8FB2,
			0x29DFC720, 0x3A8EEC8D, 0xD6263B15, 0x2E7C07CD, 0xCEF90F81,
			0x91E1900F, 0xAC85E779, 0x5B61F394, 0xEF2CC85A, 0x2945268D,
			0x28163D58, 0xF3F975D6, 0x897ACB1C, 0xD7587B46, 0xDBBCF573,
			0x83D107A2, 0xB9FE7B91, 0x5F83A9, 0xF417847D, 0xB1021886,
			0x4EAC149A, 0xF155D1E, 0x22420F71, 0x8A30CEC1, 0x9EE7EC1D,
			0x9F71DA18, 0x2860B6DD, 0xF495176E, 0x70984F6C, 0x1CF824F7,
			0x61D5C4FA, 0x835977CB, 0x7E138F3D, 0xF0B63388, 0x7EAA2E32,
			0xE3AB2A5F, 0xB0607436, 0x1392E384, 0xC220C98D, 0x69C0A2B5,
			0xC7B0D63F, 0x9CAB7F6B, 0x373BF7AA, 0xEDE62936, 0x7494419C,
			0xB3CC752A, 0x8AB91186, 0x8E512816, 0x92C0A7B3, 0xE253D7D3,
			0x61EBCFB6, 0xC12B51E9, 0xA960B3CA, 0x1EF371E8, 0x48DA3D12,
			0x26FFB52A, 0x20084E8B, 0x474D74C, 0x2005F7AF, 0x45B8B571,
			0xED789E3F, 0x44BA8EAD, 0xFFACD6D7, 0x91A47265, 0xDB4D147A,
			0xAA3BC958, 0xA542B130, 0x2EF5ED9E, 0xB0CF5504, 0xD82D8DDE,
			0x455A8788, 0x82BCD5C4, 0x60992AAA, 0xD500ABA8, 0x265D10A2,
			0xC9C082A9, 0xCC747F4E, 0x7E690E99, 0xCB5C4BD3, 0xB0A6EB9A,
			0xA1BEF0E, 0xF1909D8, 0x23C0749D, 0x9B3BCBC3, 0x7E1C93A6,
			0xC29A5A7E, 0xDA242E1C, 0xD8E29105, 0xCF6644B5, 0x36FE2F46,
			0x150D4735, 0x6F7D1AA0, 0x555D5909, 0xCE9488EF, 0xDCAD6B00,
			0x943809E0, 0xD2626486, 0x15631BFE, 0x3FC96E, 0x5439191,
			0x69215510, 0x88D0781E, 0xA69C5A1C, 0xAA42222F, 0xB0CE5484,
			0xEF0B0DA9, 0xAB695CCD, 0x501017CA, 0x4844F3D0, 0x61D1C309,
			0x3B4E9FB3, 0xFA569145, 0x99438048, 0x4DB89D77, 0x8DA62A03,
			0xD8B5D04C, 0xB4FEB55C, 0x38733AC7, 0x8681AAE, 0x6D0FE2F,
			0xC7A023A8, 0xBE47E896, 0xE682E2C8, 0x662F3A28, 0xBAC6DD3C,
			0xB0D50288, 0xE70A7A88, 0xD4655FD6, 0x761552DB, 0xCA2006F3,
			0x64821375, 0xD56185A2, 0x956E4C04, 0x1DF7A17A, 0x8321B1AF,
			0xC260E8CF, 0xACF4C871, 0x347DF643, 0x93FEED9A, 0x411A6F3,
			0x8496BD2C, 0x9282332, 0x2F4B177B, 0xCC496A4F, 0xE8918EAC,
			0xFF4F5435, 0x1DDEEAA8, 0x93ED3AA4, 0xA217CFCD, 0x21A2C152,
			0xFB5560C, 0x590C7ADB, 0xA77E1DD5, 0x3BA3C930, 0x91C0D54F,
			0x7CD74A52, 0xF4A97667, 0x7AE55702, 0x268F7E77, 0x6AAA14FA,
			0x93589C80, 0xBFD85705, 0xD2A52FD9, 0x797B4A1, 0xDD22A440,
			0x88975C47, 0xEA77CCC, 0xEF8BA18C, 0x8BEBFC51, 0x88CB2075,
			0x83BDA89D, 0xA9F85364, 0xB7D3C1EE, 0x68930C4, 0x7A4EB813,
			0x8BCC4296, 0x83195869, 0x8078BA8D, 0x16C874E2, 0x67AFA071,
			0x1B82C21, 0x8BFB041A, 0xAB5AAADB, 0xC242DA24, 0xD8D98C9C,
			0xFD821DBF, 0xDB842F5C, 0x752BE08F, 0xFB229F76, 0x31F10D47,
			0x26520B47, 0xD6F0F315, 0x68B31C1, 0xA0795B02, 0x6FF1A8A8,
			0x4983E19, 0xA7756EA7, 0x78AAE3C, 0x689FC036, 0x2FAAF0A1,
			0xD3E1CA27, 0xA51B439, 0xAA5A8343, 0x17A73732, 0x48A221BB,
			0x782C580F, 0xA9EDF8A3, 0x123D9268, 0xD21AEC00, 0x3BE6948B,
			0x326FF573, 0xD9084E2F, 0x2CEAEE43, 0xAC00A692, 0xAA79E79B,
			0xC157BCC7, 0x827407F8, 0x8EBDB239, 0x5B217F18, 0xEA7278C,
			0x4F5E6B84, 0xD4AC8284, 0x650251F3, 0xAE421624, 0x7A3857EF,
			0x44EBFBFB, 0x9739D0D, 0x4883648, 0x2BC05CC0, 0x49B7FE55,
			0xDA26D9D8, 0xB3BB6CFC, 0x8C62E12E, 0x48C1DB7A, 0xD51CB931,
			0x6CD9D08E, 0xD539C77D, 0x12B32167, 0x4F4E63B4, 0x41C018D,
			0x1719F1D8, 0x6BBC9AC0, 0xE5112E9A, 0x1667F04E, 0xB90501,
			0x715C0487, 0x35C641AA, 0x10002548, 0x34020F94, 0x45EA7881,
			0x2C2D3C92, 0x934C96B0, 0x3E7A818D, 0x972B2555, 0x8FC1DBCF,
			0x53FAABA9, 0xD17AF317, 0x77E5327, 0x9A07355B, 0xB80B32A9,
			0xBC836146, 0x29C6BAEF, 0xF140CC21, 0x1665970, 0xABACD2E9,
			0x510C92D6, 0xB275CDAC, 0x6CE7D75D, 0x7DB60C6F, 0x677BDC40,
			0xE089F82A, 0xD72662DE, 0x9B99F9A5, 0x3AD7103B, 0xECACE542,
			0x4680171F, 0x9F5C2544, 0x3215741C, 0x5C3D09C0, 0x6D6514EB,
			0x369F00DE, 0xC1A98F75, 0xB6AADBAE, 0xEEA3DADA, 0x31C09FF7,
			0x70EC2331, 0xD98E8F9A, 0x738768DC, 0x88C21C10, 0x5E6C1ABD,
			0xE253D55, 0x47BD5C86, 0x82A3742F, 0x102436D7, 0x1C391D0F,
			0xDAFB9717, 0x70C610A6, 0x76517020, 0x6A3B3400, 0xE9D77F1,
			0xE5DD922D, 0xB5482ED4, 0xDD872AD8, 0xEB523C4C, 0x7119392D,
			0x95915D21, 0x23B9C68C, 0x9D0902B2, 0xFB97D896, 0x6BE9B0BA,
			0xA0202F96, 0x3C1BAE66, 0x7D1A6B04, 0x964CB86B, 0x6194FA77,
			0xA161CC47, 0x69556E7F, 0x49381592, 0xDD878038, 0x45E62148,
			0xEACFA6F1, 0xD902F5E0, 0x63A652A5, 0x529DA4D, 0x42234AF2,
			0xED5E9189, 0x653D195B, 0xC15E626, 0xB1468A5, 0xC3239D66,
			0xDAB87684, 0xDE6541FB, 0xE22F70D6, 0x18739AC8, 0x939CBDBC,
			0xA4B76D4A, 0x7BCC6AC, 0x1E84385, 0xF7F26668, 0x56200D9C,
			0xFA704897, 0xC6EE7788, 0xB29DE134, 0xF64F4471, 0x6B275208,
			0x631F10BA, 0x801534D4, 0x20C5000E, 0x5C14F5C3, 0x1A61F3,
			0xD39DA8F9, 0x2C1F35C0, 0xE961F443, 0x44A081FB, 0xCCC2A546,
			0x5DB4C0E4, 0xD9DE614D, 0x2D31662E, 0x39AC7E37, 0x4B722BE0,
			0x16FDE42A, 0xBDCCAC85, 0x3CE6BC28, 0x8A17C26B, 0xEE25F2B7,
			0x2DFF7580, 0xB4D4A0DA, 0x34F9DD94, 0x868F5E67, 0x6D86CF9E,
			0xB56FEC7E, 0x242D549B, 0xFA45DE53, 0x3931978D, 0xAA6BE0CD,
			0xC3FFB370, 0x9D964796, 0x497588BD, 0x6EDF7817, 0x4DCDA7B6,
			0x8E6F36BC, 0xB9CEB982, 0x9468215B, 0x4951CC67, 0x78D4AA13,
			0x6097D459, 0x59FA99A9, 0x777FA5C, 0x943407F3, 0x36EA7AD9,
			0x514D5D36, 0xD8986CC1, 0x6F7114C0, 0xCC5E748F, 0x3F29D700,
			0xA5A737E2, 0x9E90C1A1, 0xE0E82694, 0x2B7EE729, 0xDEEA2D84,
			0x3977B9B3, 0x1BBD82F, 0xE4371986, 0xCBB320F0, 0x6A6AF5BD,
			0xFD31A0CA, 0xD7F1C5FD, 0x57112DE2, 0x11FEC8E7, 0x18AFB81C,
			0x903368DB, 0xDD847EBA, 0xE0CAC289, 0xFCCFC658, 0xD8E4E73C,
			0xE8370D61, 0x883D305D, 0xF2A3CEE0, 0x3454354, 0x8AA708BA,
			0x98109520, 0x25F13152, 0x6A41E1B9, 0x373AA1EC, 0x658B94D9,
			0xADE23CAC, 0x4A92694D, 0xBFCEA77D, 0x1D58FE9A, 0xA676478C,
			0x99F82E04, 0x77F1E601, 0xF927FD52, 0x64710AE, 0xCFA10C22,
			0x41DE7EA3, 0x8024E6E7, 0x69747D98, 0xA88AA0BA, 0x25593C74,
			0x84A1C295, 0x2A5F53D, 0xBEA76CDB, 0xD183A23E, 0x8D962235,
			0x9191BE0, 0x9C3EF4D9, 0x8DD39557, 0x40D150A3, 0x5F980270,
			0x30930D02, 0xAF403CCC, 0x4DB5ECE, 0xF531B8D1, 0x3DF6EBC0,
			0x1C9FA44A, 0xE2AD03C0, 0x2A0885C8, 0xA7D99F85, 0x8E7D92FA,
			0xC406B6D7, 0x46EDF6EC, 0x3BE4E8EE, 0x11838CD5, 0xC3EE55E1,
			0xEEDCA152, 0x6F9BD3E6, 0x21D785DC, 0xB19E64B3, 0xD5F72447,
			0x6149809C, 0xCC24AD7A, 0x9DE7C0E, 0x3D67A5E9, 0x2B0FA95,
			0xB0099EF4, 0x47AFAB12, 0x3258EA33, 0xB02C5AC6, 0x52B0B520,
			0xC5F653A5, 0x82298DF7, 0x51922982, 0x99B9B9E8, 0x70D2C11F,
			0x4C10790D, 0x708C9EEC, 0xEE8DD024, 0x4C202B7A, 0x3A6FDD6C,
			0x7303286C };

	public static byte HASH_A = 1;
	public static byte HASH_B = 2;

	public static int getHashCode(String key, byte hashType) throws Exception {
		int seed1 = 0x7FED7FED;
		int seed2 = 0xEEEEEEEE;

		byte ch;
		byte b[] = key.getBytes("gbk");
		for (int i = 0; i != b.length; i++) {
			ch = b[i];
			seed1 = cryptTable[(hashType << 8) + ch] ^ (seed1 + seed2);
			seed2 = ch + seed1 + seed2 + (seed2 << 5) + 3;
		}
		return seed1;
	}
	/**
	 *  保留{precision}位小数
	 * @param val
	 * @param precision 
	 * @return
	 */
	public static Double roundDouble(double val, int precision) {
		Double ret = null;
		try {
			double factor = Math.pow(10, precision);
			ret = Math.floor(val * factor + 0.5) / factor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static boolean equals(Long num1,Long num2){
		if(num1 == null || num2 == null){
			return num1 == num2;
		}
		return num1.longValue() == num2.longValue();
	}
	
	public static boolean equals(Integer num1,Integer num2){
		if(num1 == null || num2 == null){
			return num1 == num2;
		}
		return num1.intValue() == num2.intValue();
	}
	/**
	 * 获取属性名对应的
	 * @param property
	 * 				属性名
	 * @return
	 */
	public static String getSetMethodName(String property){
		return "set" + upperFirstLetter(property);
	}
	
	public static String getGetMethodName(String property){
		return getGetMethodName(property,false);
	}
	/**
	 * 获取属性的get方法，boolean类型 isXx 其他类型 getXx
	 * @param property
	 * 				属性名
	 * @param isBool
	 * 				属性类别是不是boolean类型的
	 * @return
	 */
	public static String getGetMethodName(String property,boolean isBool){
		String header = null;
		if(isBool){
			header = "is";
		}else{
			header = "get";
		}
		return header+upperFirstLetter(property);
	}
	
	/**
	 * 将str进行分割，返回Long数据
	 * 
	 * @param str
	 * 			分割的目标字符串
	 * 				1,2,3,4,5
	 * @param splitChar
	 * 			分割符
	 * 				,
	 * @return
	 */
	public static long [] toLongArray(String str,String splitChar){
		String [] strs = str.split(splitChar);
		long [] result = new long[strs.length];
		for (int i = 0; i <  strs.length; i++) {
			result[i] = StringUtil.toLong(strs[i]);
		}
		return result;
	}
}

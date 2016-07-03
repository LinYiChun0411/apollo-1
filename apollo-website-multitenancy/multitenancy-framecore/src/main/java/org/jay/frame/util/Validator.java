package org.jay.frame.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class Validator {
	
	private static Logger _log = Logger.getLogger(Validator.class);
	
	public static boolean equals(Object obj1, Object obj2) {
		if ((obj1 == null) && (obj2 == null)) {
			return true;
		} else if ((obj1 == null) || (obj2 == null)) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}

	public static boolean isAddress(String address) {
		if (isNull(address)) {
			return false;
		}

		String[] tokens = address.split(StringPool.AT);

		if (tokens.length != 2) {
			return false;
		}

		for (String token : tokens) {
			for (char c : token.toCharArray()) {
				if (Character.isWhitespace(c)) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isAscii(char c) {
		int i = c;

		if ((i >= 32) && (i <= 126)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if c is a letter between a-z and A-Z.
	 * 
	 * @param c
	 *            a character
	 * @return true if c is a letter between a-z and A-Z
	 */
	public static boolean isChar(char c) {
		int x = c;

		if ((x >= _CHAR_BEGIN) && (x <= _CHAR_END)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if s is a string of letters that are between a-z and A-Z.
	 * 
	 * @param s
	 *            a string
	 * @return true if s is a string of letters that are between a-z and A-Z
	 */
	public static boolean isChar(String s) {
		if (isNull(s)) {
			return false;
		}

		for (char c : s.toCharArray()) {
			if (!isChar(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isDate(int month, int day, int year) {
		return isGregorianDate(month, day, year);
	}

	/**
	 * Returns true if c is a digit between 0 and 9.
	 * 
	 * @param c
	 *            a character
	 * @return true if c is a digit between 0 and 9
	 */
	public static boolean isDigit(char c) {
		int x = c;

		if ((x >= _DIGIT_BEGIN) && (x <= _DIGIT_END)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if s is a string of letters that are between 0 and 9.
	 * 
	 * @param s
	 *            a string
	 * @return true if s is a string of letters that are between 0 and 9
	 */
	public static boolean isDigit(String s) {
		if (isNull(s)) {
			return false;
		}

		for (char c : s.toCharArray()) {
			if (!isDigit(c)) {
				return false;
			}
		}

		return true;
	}
	


	public static boolean isEmailAddressSpecialChar(char c) {

		// LEP-1445

		for (int i = 0; i < _EMAIL_ADDRESS_SPECIAL_CHAR.length; i++) {
			if (c == _EMAIL_ADDRESS_SPECIAL_CHAR[i]) {
				return true;
			}
		}

		return false;
	}

	public static boolean isGregorianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {

				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	public static boolean isHex(String s) {
		if (isNull(s)) {
			return false;
		}

		return true;
	}

	public static boolean isHTML(String s) {
		if (isNull(s)) {
			return false;
		}

		if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1))
		    && ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

			return true;
		}

		return false;
	}

	public static boolean isIPAddress(String ipAddress) {
		Matcher matcher = _ipAddressPattern.matcher(ipAddress);

		return matcher.matches();
	}

	public static boolean isJulianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if ((year % 4) == 0) {
				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isNotNull(Long l) {
		return !isNull(l);
	}

	public static boolean isNotNull(Integer l) {
		return !isNull(l);
	}

	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	public static boolean isNotNull(Object[] array) {
		return !isNull(array);
	}

	public static boolean isNull(Object obj) {
		if (obj instanceof Long) {
			return isNull((Long) obj);
		} else if (obj instanceof Integer) {
			return isNull((Integer) obj);
		} else if (obj instanceof String) {
			return isNull((String) obj);
		} else if (obj == null) {
			return true;
		} else if (obj instanceof Double) {
			return isNull((Double) obj);
		} else {
			return false;
		}
	}

	public static boolean isNull(Long l) {
		if ((l == null) || l.longValue() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(Double l) {
		if ((l == null) || l.doubleValue() == 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(Integer l) {
		if ((l == null) || l.intValue() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(String s) {
		if (null == s || "null".equals(s.trim()) || "".equals(s.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNumber(String number) {
		if (isNull(number)) {
			return false;
		}

		for (char c : number.toCharArray()) {
			if (!isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isPassword(String password) {
		if (isNull(password)) {
			return false;
		}

		if (password.length() < 4) {
			return false;
		}

		for (char c : password.toCharArray()) {
			if (!isChar(c) && !isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isVariableTerm(String s) {
		if (s.startsWith(_VARIABLE_TERM_BEGIN) && s.endsWith(_VARIABLE_TERM_END)) {

			return true;
		} else {
			return false;
		}
	}

	public static boolean isWhitespace(char c) {
		int i = c;

		if ((i == 0) || Character.isWhitespace(c)) {
			return true;
		} else {
			return false;
		}
	}

	private static final int _CHAR_BEGIN = 65;

	private static final int _CHAR_END = 122;

	private static final int _DIGIT_BEGIN = 48;

	private static final int _DIGIT_END = 57;

	private static final char[] _EMAIL_ADDRESS_SPECIAL_CHAR = new char[] { '.', '!', '#', '$', '%', '&', '\'', '*',
	                                                                      '+', '-', '/', '=', '?', '^', '_', '`', '{',
	                                                                      '|', '}', '~' };

	private static final String _VARIABLE_TERM_BEGIN = "[$";

	private static final String _VARIABLE_TERM_END = "$]";


	private static Pattern _ipAddressPattern = Pattern.compile("\\b"
	    + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
	    + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])" + "\\b");
	
	
	public static void main(String[] args){
		System.out.println(isNull("n"));
		System.out.println(isNull("null"));
		System.out.println(isNull("Null"));
	}

}
package org.jit.sose.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义String工具类
 * 
 * @author wangyue
 * @date 2019-06-06 16:20:12
 */
public class StringUtil {

	/**
	 * 判断是否为空字符串
	 * 
	 * @param str
	 * @return 如果为空，则返回true
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) || str.trim().length() == 0;
	}

	/**
	 * 判断字符串是否非空
	 * 
	 * @param str 如果不为空，则返回true
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 去除String空格
	 * 
	 * @param str
	 * @return 去除空格后的字符串
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 去除字符串中的所有特殊字符
	 * 
	 * @param string 需要替换的字符
	 * @return 替换之后的字符
	 */
	public static String removeSpecialChar(String string) {
		// 可以在中括号内加上任何想要替换的字符，实际上是一个正则表达式
		String regEx = "[\n`~!@#$%^&*()+=|{}':,;'\"\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
		Pattern p = Pattern.compile(regEx);
		// 把想要替换的字符串传进来
		Matcher m = p.matcher(string);
		// 将特殊字符换为aa字符串," "代表直接去掉
		String replace = "";
		String newString = m.replaceAll(replace).trim();
		return newString;
	}

	/**
	 * 去除字符串中的所有特殊字符
	 * 
	 * @param string 需要替换的字符
	 * @param regEx  被替换的字符
	 * @return 替换之后的字符
	 */
	public static String removeSpecialChar(String string, String regEx) {
		Pattern p = Pattern.compile("[" + regEx + "]");
		// 把想要替换的字符串传进来
		Matcher m = p.matcher(string);
		// 将特殊字符换为aa字符串," "代表直接去掉
		String replace = "";
		String newString = m.replaceAll(replace).trim();
		return newString;
	}

}

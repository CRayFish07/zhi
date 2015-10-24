package tools;

import java.io.UnsupportedEncodingException;

public class EncodeTool {
	public static int strToint(String str) { // ��String������ת��Ϊint�����ݵķ���
		if (str == null || str.equals(""))
			str = "0";
		int i = 0;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			i = 0;
			e.printStackTrace();
		}
		return i;
	}

	public static String toChinese(String str) { // ����ת������ķ���
		if (str == null)
			str = "";
		try {
			str = new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			str = "";
			e.printStackTrace();
		}
		return str;
	}

	public static String toChineseGBK(String str) { // ����ת������ķ���
		if (str == null)
			str = "";
		try {
			str = new String(str.getBytes("gbk"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			str = "";
			e.printStackTrace();
		}
		return str;
	}
	public static String toChineseGB(String str) { // ����ת������ķ���
		if (str == null)
			str = "";
		try {
			str = new String(str.getBytes("gb2312"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			str = "";
			e.printStackTrace();
		}
		return str;
	}
}
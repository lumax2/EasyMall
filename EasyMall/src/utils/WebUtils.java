package utils;

public class WebUtils {
	
	/**
	 * @param value
	 * @return true:value Ϊ�� 
	 * 		   false:��Ϊ��
	 */
	public static boolean check(String value){
		return value == null ||"".equals(value.trim());
	}
}

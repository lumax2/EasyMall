package utils;

public class WebUtils {
	
	/**
	 * @param value
	 * @return true:value Îª¿Õ 
	 * 		   false:²»Îª¿Õ
	 */
	public static boolean check(String value){
		return value == null ||"".equals(value.trim());
	}
}

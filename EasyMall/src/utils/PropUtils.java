package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {

	private static Properties prop = new Properties();
	static{
	String path = PropUtils.class.getClassLoader().
	getResource("config.properties").getPath();
	try {
	prop.load(new FileInputStream(new File(path)));
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	public static Properties getProp(){
	return prop;
	}
	public static String getProperty(String key){
	return prop.getProperty(key);
	}
}

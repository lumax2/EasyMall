package cn.tedu.factory;

import utils.PropUtils;

public class BasicFactory {

		private static BasicFactory factory ;
		private BasicFactory(){};
		
		public static BasicFactory getFactory(){
			return factory;
		}
		public static <T>T getInstance(Class<T> intfClz){
			String intfName = intfClz.getSimpleName(); 
			String className = PropUtils.getProperty(intfName);
			try {
				
			Class clz = Class.forName(className);
			return (T)clz.newInstance();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}


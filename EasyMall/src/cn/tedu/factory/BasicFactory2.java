package cn.tedu.factory;

import utils.PropUtils;

public class BasicFactory2 {
	
	private static BasicFactory2 b2= new BasicFactory2();
	private BasicFactory2(){};
	public static BasicFactory2 getFactory(){
		return b2;
	}
	@SuppressWarnings("unchecked")
	public <T>T getInstance(Class<T> intfClass){
		
		String intfName =intfClass.getSimpleName();
		String intfImplName =PropUtils.getProperty(intfName);
		
		
		try {
			Class<?> clz = (Class<T>)Class.forName(intfImplName);
			
			return (T)clz.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}

}

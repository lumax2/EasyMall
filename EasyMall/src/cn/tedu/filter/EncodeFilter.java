package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {

	private String encode;
	public void init(FilterConfig fc) throws ServletException {
		encode = fc.getInitParameter("encode");//utf-8
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//设置响应编码格式
		response.setContentType("text/html;charset="+encode);
		
		//创建装饰后的对象
		MyRequest req = new MyRequest((HttpServletRequest)request);
		chain.doFilter(req, response);
		
		
	}
	class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		private Map<String,String[]> map;
		public MyRequest(HttpServletRequest request){
			super(request);
			this.request= request;
		}
		
		@Override
		public String getParameter(String name) {
			return getParameterValues(name) == null ? null : getParameterValues(name)[0];
		}
		
		@Override
		public String[] getParameterValues(String name) {
			
			return (String[]) getParameterMap().get(name);
		}
		@Override
		public Map<String,String[]> getParameterMap() {
			if(map == null){
				//判断提交方式
				if("POST".equals(request.getMethod())){
					try {
						request.setCharacterEncoding(encode);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					map =request.getParameterMap();
					return map;
				}else if("GET".equals(request.getMethod())){
					// 调用request获取form表单提交的所有输入框的值
					map = request.getParameterMap();
					for(Map.Entry<String, String[]> entry:map.entrySet()){
						String[] vals= entry.getValue();
						if(vals !=null){
							for(int i=0;i<vals.length;i++){
								try {
									vals[i]=new String(vals[i].getBytes("ISO8859-1"),encode);
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}
						}
					}
					return map;
				}else{
					map =request.getParameterMap();
					return map;
				}
			}else{
				return map;
			
			}
		}
	}
	public void destroy() {

	}
	
}

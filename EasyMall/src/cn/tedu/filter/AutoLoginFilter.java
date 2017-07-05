package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.tedu.entity.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		//1���ж��Ƿ��¼��
		Object obj = req.getSession().getAttribute("user");
		//2ֻ��δ��¼,���б�Ҫʵ���Զ���¼
		if(obj==null){//δ��¼
			//4���жϵ�ǰ�ͻ������Ƿ����Զ���¼��cookie��Ϣ
			Cookie autologin=null;
			Cookie[] cks = req.getCookies();
			if(cks!=null){
				for(int i =0;i<cks.length;i++){
					if("autologin".equals(cks[i].getName())){
						autologin=cks[i];
						break;
					}
				}
				//5�������Զ���¼��cookie
				if(autologin!=null){
					//6����ȡ�û���������
					String unPwd = URLDecoder.decode(autologin.getValue(),"utf-8");
					String info[] = unPwd.split(":");
					//7������ҵ������
					UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
					//8������ҵ��㷽��
					User user = userService.login(info[0], info[1]);
					//9���жϵ�¼�Ƿ�ɹ�
					if(user!=null){
						//10����user����session����ɵ�¼
						req.getSession().setAttribute("user", user);
					}
					chain.doFilter(request, response);
				}else{
					//ִ�з���
					chain.doFilter(request, response);
				}
			}
		}else{
			//3����,�˴�ʹ��request��req�����ԣ����ڴ��еĵ�ַһ��
			chain.doFilter(req, response);
		}
	}
	public void destroy() {
	}
}


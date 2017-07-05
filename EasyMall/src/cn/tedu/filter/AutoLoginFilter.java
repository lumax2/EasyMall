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
		//1、判断是否登录，
		Object obj = req.getSession().getAttribute("user");
		//2只有未登录,才有必要实现自动登录
		if(obj==null){//未登录
			//4、判断当前客户端中是否有自动登录的cookie信息
			Cookie autologin=null;
			Cookie[] cks = req.getCookies();
			if(cks!=null){
				for(int i =0;i<cks.length;i++){
					if("autologin".equals(cks[i].getName())){
						autologin=cks[i];
						break;
					}
				}
				//5、存在自动登录的cookie
				if(autologin!=null){
					//6、获取用户名和密码
					String unPwd = URLDecoder.decode(autologin.getValue(),"utf-8");
					String info[] = unPwd.split(":");
					//7、创建业务层对象
					UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
					//8、调用业务层方法
					User user = userService.login(info[0], info[1]);
					//9、判断登录是否成功
					if(user!=null){
						//10、将user保存session：完成登录
						req.getSession().setAttribute("user", user);
					}
					chain.doFilter(request, response);
				}else{
					//执行放行
					chain.doFilter(request, response);
				}
			}
		}else{
			//3放行,此处使用request或req都可以，堆内存中的地址一样
			chain.doFilter(req, response);
		}
	}
	public void destroy() {
	}
}


package cn.tedu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MySCListener implements ServletContextListener {

	//application��������Ȼ��ҳ������${app}�滻${pageContext.request.contextPath }
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("app",sce.getServletContext().getContextPath());
	}

	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("app");
	}

}

package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		VerifyCode vc =new VerifyCode();
		
		vc.drawImage(resp.getOutputStream());
		//�������浽session�У��Ա�ע��ʱservletʱ��֤
		
			//��sessionʵ����֤��
		String code = vc.getCode();
		req.getSession().setAttribute("code", code);
			//�������޸�RegistServlet
		
		System.out.println(code);
		
	
	}
}

package com.restrant.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		//��ȡ��Ŀ¼����Ӧ�ľ���·��
		String currentURL=request.getRequestURI();
		HttpSession session=request.getSession(false);
		//���ֱ�ӷ��ʹ��ﳵshopCartҳ������֤
		if(currentURL.indexOf("shopCart.jsp")>-1){
			//�жϵ�ǰҳ�Ƿ����ض����Ժ�ĵ�¼ҳ�棬����ǾͲ��ж�session
			if(session==null || session.getAttribute("user")==null){
				response.sendRedirect(request.getContextPath()+"/toShowMeal");
				return;
			}
		}
		//����filter����������ִ��
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}

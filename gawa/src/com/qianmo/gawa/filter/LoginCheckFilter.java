package com.qianmo.gawa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckFilter  implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		String   uri  =  req.getRequestURL().toString();
		String uc=(String)req.getSession().getAttribute("userctx");
		
		if(uri.endsWith("gawa") || uri.endsWith("gawa/")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");    
            dispatcher.forward(request, response);
		}else if(uri.endsWith("login") ||
				uri.endsWith("onofflines") || uri.endsWith("offlines")
				|| uri.endsWith("netlogs") ||
				uri.endsWith("apopen") || uri.endsWith("apopen1") || uri.endsWith("heartbeat") 
				|| uri.endsWith("xingbo") 
				|| uc!=null){
			chain.doFilter(request, response);
		}else{
			// 跳转到登陆页面  
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");    
            //dispatcher.forward(request, response);
           
            ((HttpServletResponse) response).sendRedirect("/WEB_AUTH/Login.jsp");
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}

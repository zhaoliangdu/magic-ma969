/**
 * 拦截器
 */
package com.ma969.interceptor;

/**
 * @author ZhaoLiangdu
 *
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	/**
	 * 用户登录拦截器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		/* true false */
		/* true 代表不拦截 false 代表拦截 */
		HttpSession session = request.getSession();
		Object user = session.getAttribute("loginuser");
		if (user == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return false;
		} else {
			return true;
		}
	}
}

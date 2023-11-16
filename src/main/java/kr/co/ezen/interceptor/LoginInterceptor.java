package kr.co.ezen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.ezen.beans.User;

public class LoginInterceptor implements HandlerInterceptor{
	
	private User loginBean;
	
	public LoginInterceptor(User loginBean) {
		this.loginBean=loginBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception{
		
		if(loginBean.isUserLogin()==false) { //로그인 안된 상태라면
			String con=request.getContextPath(); //현재 경로 알아내서
			response.sendRedirect(con+"/user/not_login");
			return false;
		}
		return true;
	}
}

package kr.co.ezen.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.ezen.beans.BoardInfo;
import kr.co.ezen.beans.User;
import kr.co.ezen.service.TopMenuService;

//무슨 주소를 요청하던간에 상단메뉴(1,2,3,4팀)를 통과해야하기 때문에 인터셉터 추가
public class TopInterceptor implements HandlerInterceptor{
	
	private TopMenuService topMenuService;
	
	@Resource(name="loginBean")
	private User loginBean;
	
	
	public TopInterceptor(TopMenuService topMenuService, User loginBean) {
		this.topMenuService=topMenuService;	////1,2,3,4, 1팀,2팀,3팀,4팀
		this.loginBean=loginBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception{
		
		List<BoardInfo> team= topMenuService.getTopMenuList();
		
		request.setAttribute("team", team);
		request.setAttribute("loginBean", loginBean);
		return true;
		
	}
}

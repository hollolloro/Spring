package kr.co.ezen.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ezen.beans.User;
import kr.co.ezen.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Resource(name="loginBean")
	private User loginBean;
	
	public boolean existId(String user_id) {
		
		String user_name=userDao.existId(user_id);
		//아이디를 기준으로 이름 추출
		if(user_name==null) {  //사용할 수 있는 아이디면
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addUser(User joinBean) {
		userDao.addUser(joinBean);
		
	}
	
	public void getLoginUser(User loginProBean) {
		
		User loginProBean2 = userDao.getLoginUser(loginProBean);
		
		if(loginProBean2 !=null) { //user_idx, user_name  => 로그인 성공
			loginBean.setUser_idx(loginProBean2.getUser_idx());
			loginBean.setUser_name(loginProBean2.getUser_name());
			loginBean.setUserLogin(true); //로그인 성공 시 세션영역에 idx, name저장
		}
	}
	public void getModifyUser(User modifyBean) {
		
		User u=userDao.getModifyUser(loginBean.getUser_idx()); //로그인한 사람의 idx
		
		modifyBean.setUser_id(u.getUser_id());
		modifyBean.setUser_name(u.getUser_name());
		modifyBean.setUser_idx(loginBean.getUser_idx());
	
	}
	
	public void modifyUser(User modifyBean) {
		
		modifyBean.setUser_idx(loginBean.getUser_idx());
		userDao.modifyUser(modifyBean);
		
	}
	
}





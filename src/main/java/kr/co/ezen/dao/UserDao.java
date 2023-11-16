package kr.co.ezen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ezen.beans.User;
import kr.co.ezen.mapper.UserMapper;

@Repository
public class UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	public String existId(String user_id) {
		return userMapper.existId(user_id);  

	}
	public void addUser(User joinBean) {
		userMapper.addUser(joinBean);
	}
	
	public User getLoginUser(User loginProBean) {
		return userMapper.getLoginUser(loginProBean);
	}
	
	public User getModifyUser(int user_idx) {
		return userMapper.getModifyUser(user_idx);
	}
	
	public void modifyUser(User modifyBean) {
		userMapper.modifyUser(modifyBean);
	}
	
	
}







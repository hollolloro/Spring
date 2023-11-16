package kr.co.ezen.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.ezen.beans.User;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user=(User)target;
		//비밀번호와 비밀번호확인값이 다를 경우
		
		String beanName=errors.getObjectName();
		
		if(beanName.equals("joinBean") || beanName.equals("modifyBean")) {
			if(user.getUser_pw().equals(user.getUser_pw2())==false) {
				errors.rejectValue("user_pw", "NotEqual");
				errors.rejectValue("user_pw2", "NotEqual");
			}
		}
		if(beanName.equals("joinBean")){
			if(user.isExistId()==false) {
				errors.rejectValue("user_id", "NotCheck");
			}		
			}	
		}	
	}

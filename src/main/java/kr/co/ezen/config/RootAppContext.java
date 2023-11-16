package kr.co.ezen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.ezen.beans.User;

@Configuration
public class RootAppContext {
	
	@Bean("loginBean")
	@SessionScope
	public User loginBean() {
		return new User();
	}
	
	
}

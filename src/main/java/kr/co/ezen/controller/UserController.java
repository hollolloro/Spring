package kr.co.ezen.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.ezen.beans.User;
import kr.co.ezen.service.UserService;
import kr.co.ezen.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name="loginBean")
	private User loginBean;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("loginProBean") User loginProBean,
			@RequestParam(value="fail", defaultValue = "false") boolean fail, 
			Model model) {
		//로그인 실패하면 true가 boolean fail에 저장 -> 이름은 fail로 저장됨
		model.addAttribute("fail",fail);
		
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute
			("loginProBean") User loginProBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/login";
		}
		
		userService.getLoginUser(loginProBean); //로그인 성공여부
		
		if(loginBean.isUserLogin()==true) {
			return "user/login_success";
		}
		else {
			return "user/login_fail";
		}
		
	}
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyBean") User modifyBean) {
		
		userService.getModifyUser(modifyBean);
		
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyBean") User modifyBean,
					BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/modify";
		}
		userService.modifyUser(modifyBean);
		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		loginBean.setUserLogin(false); //로그아웃시 세션연결 끊음
		return "user/logout";
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinBean") User joinBean) {
		return "user/join";
	}
	
	@PostMapping("/join_pro")  //회원가입폼에서 쓴 값들 유효성 검사 확인
	public String join_pro(@Valid @ModelAttribute("joinBean") User joinBean,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUser(joinBean);
		
		return "user/join_success";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		UserValidator v1=new UserValidator();
		binder.addValidators(v1);
	}
}





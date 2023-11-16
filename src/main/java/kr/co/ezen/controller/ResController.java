package kr.co.ezen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ezen.service.UserService;

@RestController
public class ResController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/chexistId/{user_id}")
	public String existId(@PathVariable String user_id) {
		
		boolean chk=userService.existId(user_id);
		
		return chk+"";  //데이터넘겨짐
	
	}
}

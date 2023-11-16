package kr.co.ezen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ezen.beans.BoardInfo;
import kr.co.ezen.dao.TopMenuDao;

@Service
public class TopMenuService {
	
	@Autowired
	private TopMenuDao topMenuDao;
	
	public List<BoardInfo> getTopMenuList(){
		List<BoardInfo> topMenuList = topMenuDao.getTopMenuList();
		return topMenuList;  //1,2,3,4, 1팀,2팀,3팀,4팀
		
	} 
}

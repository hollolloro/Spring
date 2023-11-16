package kr.co.ezen.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ezen.beans.BoardInfo;
import kr.co.ezen.mapper.TopMenuMapper;

@Repository
public class TopMenuDao {
	
	@Autowired
	private TopMenuMapper topMenuMapper;
	
	public List<BoardInfo> getTopMenuList(){
		List<BoardInfo> topMenuList = topMenuMapper.getTopMenuList();
		return topMenuList;  //1,2,3,4, 1팀,2팀,3팀,4팀
		
	}
}




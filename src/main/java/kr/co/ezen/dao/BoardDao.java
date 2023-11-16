package kr.co.ezen.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ezen.beans.Content;
import kr.co.ezen.mapper.BoardMapper;

@Repository
public class BoardDao {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public void addContent(Content writeBean) {
		boardMapper.addContent(writeBean);
		
	}
	
	public String getBoardName(int board_info_idx) {
		return boardMapper.getBoardName(board_info_idx);
	}
	
	public List<Content> getContent(int board_info_idx){
		return boardMapper.getContent(board_info_idx);
	}
	
	public Content getInfo(int content_idx) {
		return boardMapper.getInfo(content_idx);
	}
	
	
}

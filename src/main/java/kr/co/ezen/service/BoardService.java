package kr.co.ezen.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ezen.beans.Content;
import kr.co.ezen.beans.User;
import kr.co.ezen.dao.BoardDao;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name="loginBean")
	private User loginBean;
	
	
	public void addContent(Content writeBean) {
		
		writeBean.setContent_writer_idx(loginBean.getUser_idx());
		boardDao.addContent(writeBean);

	}
	
	public String getBoardName(int board_info_idx) {
		return boardDao.getBoardName(board_info_idx);
	}

	public List<Content> getContent(int board_info_idx){
		return boardDao.getContent(board_info_idx);
	}
	
	public Content getInfo(int content_idx) {
		return boardDao.getInfo(content_idx);
	}
}







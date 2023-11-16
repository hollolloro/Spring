package kr.co.ezen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.ezen.beans.Content;
import kr.co.ezen.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
			Model m) {
		
		m.addAttribute("board_info_idx",board_info_idx);
		
		String boardName=boardService.getBoardName(board_info_idx);
		
		m.addAttribute("boardName",boardName);
		
		List<Content> contentLi = boardService.getContent(board_info_idx);
		m.addAttribute("contentLi",contentLi);
	
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
			@RequestParam("content_idx") int content_idx,
			Model m) {
		
		m.addAttribute("board_info_idx",board_info_idx);
		
		Content readContent=boardService.getInfo(content_idx);
		m.addAttribute("readContent",readContent);
		
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeBean") Content writeBean,
			            @RequestParam("board_info_idx") int board_info_idx) {
		
		writeBean.setContent_board_idx(board_info_idx);
		
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeBean") Content writeBean
			, BindingResult result) {
		
		if(result.hasErrors()) {
			return "board/write";
		}
		boardService.addContent(writeBean); //작성하기 버튼 누르면 디비에 삽입됨
		return "board/write_success";
		
	}

	@GetMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "board/delete";
	}
}

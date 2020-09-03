package com.last.app.controller.board;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.last.app.domain.Board;
import com.last.app.model.board.BoardService;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//has a 관계에 잇는 객체는 느슨할 수록 좋다!!(유지보수성에)
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/board/registForm", method=RequestMethod.GET)
	public String registForm() {
		return "board/registForm";
	}
	
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String selectAll(Model model) {
		logger.info("selectAll 동작");
		List boardList = boardService.selectAll();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@RequestMapping(value="/board/detail", method=RequestMethod.GET)
	public String select(Model model, @RequestParam int board_id) {
		logger.info("select 동작");
		Board board = boardService.select(board_id);
		model.addAttribute("board", board);
		return "board/content";
	}
	
	@RequestMapping(value="/board/regist", method=RequestMethod.POST)
	public String insert(Board board, Model model) {
		boardService.insert(board);
		model.addAttribute("msg", "등록되었습니다");
		model.addAttribute("url", "/board/list");
		return "result/message";
	}
	
}













package com.last.app.controller.test.board;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.last.app.aop.exception.DMLException;
import com.last.app.domain.Board;
import com.last.app.model.board.BoardService;

//@RestController를 선언하면 ,메서드마다 @ResponseBody를 붙이지 않아도 된다
@RestController
public class TestBoardController {
	private static Logger logger=LoggerFactory.getLogger(TestBoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/test/board", method=RequestMethod.GET,produces ="text/json;charset=UTF-8")
	public List<Board> getList(){
		logger.info("RestBoardController 호출함");
		return boardService.selectAll();
	} 
	
	//한건 가져오기 
	@RequestMapping(value="/test/board/{board_id}", method=RequestMethod.GET)
	public Board select(@PathVariable int board_id) {
		logger.info("board_id="+board_id);
		Board board = boardService.select(board_id);
		return board;
	}
	
	//한건 등록하기 
	@RequestMapping(value="/test/board", method=RequestMethod.POST,produces ="text/json;charset=UTF-8" )
	public void regist(Board board) {
		throw new DMLException("에러네용");
		//return "1";
	}
	
	@ExceptionHandler(DMLException.class)
	public String handle(DMLException e) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"resultCode\":0,");
		sb.append("\"msg\":\""+e.getMessage()+"\",");
		sb.append("}");
		return sb.toString();
	}
	
}





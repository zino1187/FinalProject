package com.last.app.controller.rest;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.last.app.aop.exception.DMLException;
import com.last.app.domain.Board;
import com.last.app.model.board.BoardService;

//Rest형식으로 들어오는 요청을 처리하는 컨트롤러
//그냥 @Controller 를 사용하는것과 @RestController를 명시하는 것과의 
//차이점?  @RestController를 명시하면, @ResponseBody를 명시할 필요없다
@RestController
@CrossOrigin("http://localhost:3000")
public class RestBoardController {
	private static Logger logger=LoggerFactory.getLogger(RestBoardController.class);
	
	@Inject
	private BoardService boardService;
	
	
	//게시판 목록 요청
	//요청 URL에는 더이상 동사적 표현은 하지 않는다
	//  rest/board + GET = 게시판 목록 요청
	//하나의 컴퓨터에서 여러대의 서버를 서로 접근하려고 할때, 아래의설정을 해주면
	//테스트가 가능하다
	
	@RequestMapping(value="/rest/board", method=RequestMethod.GET, produces ="application/json;charset=UTF-8")
	@ResponseBody
	public List<Board> getList() {
		logger.info("목록 요청");
		List boardList= boardService.selectAll();
		return boardList;
	}
	
	//REST 글쓰기 요청 처리 는 POST로 처리한다
	//평소같앗으면, /rest/board/regist 라는 동사로 표현햇을 텐데, 
	//REST는 데이터만 명시한다
	//주의: 모든 어플케이션을 REST로 개발하는게 아니라, 외부의 이기종간 데이터
	//교환이 있을때, 지원할 일이 있을때 REST로 개발하는 것이다. 
	//예)  웹사이트에 안드로이드폰, 아이폰도 연동할 경우...
	//      웹사이트와 외부 IOT 장비를 연동할 경우 ...
	//      웹사이트에 또 다른 외부 웹사이트를 연동할 경우.. 
	@RequestMapping(value="/rest/board", method=RequestMethod.POST)
	public String insert(@RequestBody Board board) {
		logger.info("글쓰기 요청 board is "+board);
		
		//마지막 수업을 MySQLBaordDAO사용햇었따..기억하기
		boardService.insert(board);//여기서 Exception이 발생할 수 있으므로
		//처리하자., 처리는 메서드 차원에서 하지말고, 클래스나, 글로벌로 처리한다
		//이 실습에서는 클래스차원에서 처리한다
		//여기서 에러가 발생하면, 같은 클래스에 명시된 아래의 ExceptionHandler
		//가 예외를 처리한다
		//json이나 평문으로 에러 메시지를 보내자
		//현재 메서드에서는 성공한 경우 그냥 평문으로 보내자 
		//@RestController를 명시햇으므로, @ResponseBody는 따로 안해줘도
		//될것이다
		return "1";
	}
	
	//상세보기 요청 처리 
	//REST 방식에서의 상세보기 요청 처리는 역시나 데이터를 명사형식으로 
	//표현하되, http의 GET으로 처리한다 (한건인 경우 /key 값으로 표현)
	//따라서 주소는 변함이 없다
	///rest/board/{board_id} 주소안의 {board_id}를 변수로 생각할까?
	//스프링의 REST Controller에서는, URL 경로 안에 들어있는 문자열을 
	//분해하여 변수로 처리하는 기능이 지원된다..
	@RequestMapping(value="/rest/board/{board_id}", method=RequestMethod.GET)
	public Board select(@PathVariable int board_id) {
		logger.info("한건 요청 board_id는 "+board_id);
		Board board=boardService.select(board_id);
		return board;
	
	}
	
	//수정요청 
	@RequestMapping(value="/rest/board", method=RequestMethod.PUT)
	public String update(@RequestBody Board board) {
		logger.info("수정요청"+board);
		boardService.update(board);
		//수정후, 상세보기 내용을 반환 
		Board vo=boardService.select(board.getBoard_id());
		return "1";
	}
	
	//삭제요청 
	@RequestMapping(value="/rest/board/{board_id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable int board_id) {
		logger.info("삭제요청 board_id="+board_id);
		boardService.delete(board_id);
		return "1";
	}
	
	@ExceptionHandler(DMLException.class)
	public String handle(DMLException e) {
		return "0";//실패의 경우 0을 보내자
	}
	
}	




















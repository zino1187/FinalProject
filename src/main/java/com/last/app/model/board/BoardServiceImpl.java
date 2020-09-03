package com.last.app.model.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.last.app.aop.exception.DMLException;
import com.last.app.domain.Board;

@Service
public class BoardServiceImpl implements BoardService{
	
	//중복된 자료형이 두개이상 발견되면, 개발자가 어떤 객체를 사용할지를 명시해야 한다!!
	//기본적으로 클래스명의 맨 앞자를 소문자로한 아이디를 빈의 기본 아이디로 인식
	@Inject
	@Qualifier("mysqlBoardDAO")
	private BoardDAO boardDAO;
	
	@Override
	public List selectAll() {
		System.out.println("Service의 selectAll() 호출");
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void insert(Board board) throws DMLException{
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board) throws DMLException{
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_id) throws DMLException{
		boardDAO.delete(board_id);
		
	}
	
}

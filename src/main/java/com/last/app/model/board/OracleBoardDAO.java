package com.last.app.model.board;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.last.app.aop.exception.DMLException;
import com.last.app.domain.Board;

@Repository
public class OracleBoardDAO implements BoardDAO{
	
	@Inject
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public List selectAll() {
		System.out.println("Oracle용 DAO의 selectAll() 호출");
		return sessionTemplate.selectList("OracleBoard.selectAll");
	}

	@Override
	public Board select(int board_id) {
		return sessionTemplate.selectOne("OracleBoard.select", board_id);	
	}

	@Override
	public void insert(Board board) throws DMLException{
		int result=sessionTemplate.insert("OracleBoard.insert", board);

		if(result==0) {
			throw new DMLException("등록되지 않았습니다");
		}
	}

	@Override
	public void update(Board board) throws DMLException{
		int result=sessionTemplate.update("OracleBoard.update", board);
		if(result==0) {
			throw new DMLException("수정되지 않았습니다");
		}
	}

	@Override
	public void delete(int board_id)  throws DMLException{
		int result=sessionTemplate.delete("OracleBoard.delete", board_id);
		if(result==0) {
			throw new DMLException("삭제되지 않았습니다");
		}
	}

}

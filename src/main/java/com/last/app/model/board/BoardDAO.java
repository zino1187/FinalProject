package com.last.app.model.board;

import java.util.List;

import com.last.app.domain.Board;

//서비스는 해당 객체를 직접 보유하기보다는, BoardDAO라는 최상위 객체인 
//인터페이스를 보유함으로서, 결합도를 약화시킬 수 있다 (DI 목적)
public interface BoardDAO {
	public List selectAll();
	public Board select(int board_id);
	public void insert(Board board);
	public void update(Board board);
	public void delete(int board_id);
}











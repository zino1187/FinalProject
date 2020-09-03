package com.last.app.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.last.app.aop.exception.DMLException;
import com.last.app.aop.exception.MemberNotFoundException;
import com.last.app.domain.Member;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List selectAll() {
		return sessionTemplate.selectList("MysqlMember.selectAll");
	} 
	public Member select(Member member) {
		Member obj = sessionTemplate.selectOne("MysqlMember.select", member);
		if(obj==null) {
			//throw new MemberNotFoundException("등록된 회원이 없습니다");
		}
		return obj;
	}
	
	public void insert(Member member) {
		int result = sessionTemplate.insert("MysqlMember.insert", member);
		if(result==0) {
			throw new DMLException("회원등록에 실패하였습니다.");
		}
	}
	public void delete(Member member) {
		int result = sessionTemplate.delete("MysqlMember.delete", member);
		if(result==0) {
			throw new DMLException("회원탈퇴에 실패하였습니다.");
		}
	}
	
}

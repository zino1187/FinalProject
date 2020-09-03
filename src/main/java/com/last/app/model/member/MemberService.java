package com.last.app.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.last.app.aop.exception.DMLException;
import com.last.app.aop.exception.MemberNotFoundException;
import com.last.app.domain.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	public List selectAll() {
		return memberDAO.selectAll();
	} 
	public Member select(Member member) throws MemberNotFoundException{
		return memberDAO.select(member);
	}
	
	public void insert(Member member) throws DMLException{
		memberDAO.insert(member);
	}
	public void delete(Member member) throws DMLException {
		memberDAO.delete(member);
	}
	
}

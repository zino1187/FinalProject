package com.last.app.aop;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.last.app.aop.exception.DMLException;
import com.last.app.aop.exception.MemberNotFoundException;

/*모든 컨트롤러에서 발생하는 예외를 감지하여, 여기서 일괄처리*/
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DMLException.class)
	public String handle(DMLException e, Model model) {
		model.addAttribute("msg", e.getMessage());
		return "result/error";
	}
	@ExceptionHandler(MemberNotFoundException.class)
	public String handle(MemberNotFoundException e, Model model) {
		model.addAttribute("url", "/sns/login");
		model.addAttribute("msg", e.getMessage());
		return "result/message";
	}
	
}













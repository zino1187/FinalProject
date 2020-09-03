<%@page import="com.study.model.reboard.MybatisReBoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!MybatisReBoardDAO reboardDAO = new MybatisReBoardDAO(); %>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="reboard" class="com.study.model.reboard.ReBoard"/>
<jsp:setProperty property="*" name="reboard"/>
<jsp:getProperty property="team" name="reboard"/>
<jsp:getProperty property="rank" name="reboard"/>
<jsp:getProperty property="depth" name="reboard"/>
<%
	//답변 들어갈 자리 만들기(내가본 이후의 글들의 rank를 1씩 증가
	//update  reboard set rank = rank+1 
	//where team=내본team and rank>내본rank
	reboardDAO.updateRank(reboard);
	
	//답변 등록 (답변이 들어갈 자리 확보!! + 답변 등록)
	//insert into reboard(~,team,rank,depth)
	//values(~,내본team, 내본rank+1,내본depth+1)
	reboardDAO.reply(reboard);
	
	//추후 응답이 완료된 후에 , 클라이언트의 브라우저로 하여금 다시 재접속 
	//하라는 명령임..
	response.sendRedirect("/reboard/list.jsp");
%>















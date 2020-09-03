<%@page import="com.pub.model.notice.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!BoardDAO reboardDAO = new BoardDAO();%>
<!-- 아래와 같이 태그이되, 서버에서만 실행될 수 있는 태그를 가리켜 
  빈즈 태그라 한다!! bean태그를 이용하면, 코드량을 줄일수 있다-->

<!-- 아래의 태그는 ReBoard reboard = new ReBoard() -->
<jsp:useBean id="board" class="com.the.model.notice.Board"/>
<%request.setCharacterEncoding("utf-8"); %>

<!-- 아래의 태그는 VO 를생성한후 setter로 파라미터를 넣는 작업과 같다 
	아래의 * 가 동작하려면, 반드시 html 파라미터명과 VO의 멤버변수명
	이 같아야 한다!!
 -->
<jsp:setProperty property="*" name="board"/>
<%
	//파라미터를 넘겨받아 오라클에 넣기!
	int result=reboardDAO.insert(board);
	
	response.sendRedirect("/board/list.jsp");
%>













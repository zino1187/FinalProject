<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8">
</head>
<body>

<h1>
	<pre>
		메인입니다  
		<%if(session.getAttribute("member")==null){%>
			<a href="/sns/login">로그인</a>
		<%}else{ %>
			<a href="/sns/logout">로그아웃</a>
		<%} %>
	</pre> 
</h1>
</body>
</html>

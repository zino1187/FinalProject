<%@page import="org.apache.log4j.Logger"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	Logger logger=Logger.getLogger("test.jsp");
%>
<%logger.fatal("치명적 에러"); %>
<%logger.error("일반적 에러"); %>
<%logger.warn("경고 수준"); %>
<%logger.info("정보 출력"); %>
<%logger.debug("확인 출력"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
</body>
</html>




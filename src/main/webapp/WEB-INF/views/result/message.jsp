<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String url=(String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
alert("<%=msg%>");
location.href="<%=url%>";
</script>
</head>
<body>

</body>
</html>
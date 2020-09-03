<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
alert("<%=msg%>");
history.back();
</script>
</head>
<body>

</body>
</html>
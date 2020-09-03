<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String code = request.getParameter("code");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input{
	width:60%;
	height:50px;
}
</style>
<script>
function getToken(){
	//form1.code.value=decodeURIComponent(form1.code.value);
	form1.action="https://accounts.google.com/o/oauth2/token";
	form1.submit();
}
</script>
</head>
<body>
	<!-- 주소 URL에 구글로 부터 보내진 정보를 확인해보자 
		구글이 보내온 코드를 이용해서, 토큰을 요청하자!!
	-->
	<pre>
		<form name="form1" method="post">		
			<input type="text" name="code" 		value="<%=code%>">
			<input type="text" name="client_id" value="">
			<input type="text" name="client_secret" value="">
			<input type="text" name="redirect_uri" value="http://localhost:7777/member/google/callback">
			<input type="text" name="grant_type" value="authorization_code">
		</form>		
		<button onClick="getToken()">토큰 얻기</button>
	</pre>
</body>
</html>






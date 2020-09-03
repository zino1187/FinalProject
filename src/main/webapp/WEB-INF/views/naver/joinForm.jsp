<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String google_url =(String)request.getAttribute("google_url");
	String naver_url =(String)request.getAttribute("naver_url");
	String kakao_url =(String)request.getAttribute("kakao_url");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function sendGoogle(){
	location.href="";
}

function sendNaver(){
	location.href="<%=naver_url%>";
}

function sendKaKao(){
	location.href="";
}

</script>
</head>
<body>
	<button onClick="sendGoogle()">구글 로그인</button>
	<a href="javascript:sendNaver()"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
	<button onClick="send()">구글 로그인</button>
	
</body>
</html>



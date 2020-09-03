<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//개발자가 원래 하드코딩해야할 각종 설정 및 url을 일일이 작성하지 말고
	//컨트롤러에서 받아오자!!
	String google_url = (String)request.getAttribute("google_url");
	String naver_url = (String)request.getAttribute("naver_url");
	String kakao_url = (String)request.getAttribute("kakao_url");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
//스프링 컨트롤러에서 얻어온 url을 이용하여 코드를 요청할거임..
function googleLogin(){
	location.href="<%=google_url%>";
}
function naverLogin(){
	location.href="<%=naver_url%>";
}
function kakaoLogin(){
	location.href="<%=kakao_url%>";
}

</script>
</head>
<body>
	<button onClick="googleLogin()">Google로 로그인</button>
	<button onClick="naverLogin()">Naver로 로그인</button>
	<button onClick="kakaoLogin()">KaKao로 로그인</button>
</body>
</html>



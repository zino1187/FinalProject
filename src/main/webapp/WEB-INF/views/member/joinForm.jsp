<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function send(){
	var client_id="3151950286-3g6gbes60sivr1lk9lofh7uppua58e0d.apps.googleusercontent.com";
	var redirect_uri="http://localhost:7777/member/google/callback";
	
	//구글에 인증요청하기(인증코드 즉 토큰을 받기 위함)
	location.href="https://accounts.google.com/o/oauth2/auth?client_id="
			+client_id+"&redirect_uri="+redirect_uri+"&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email&approval_prompt=force&access_type=offline";
}
</script>
</head>
<body>
	<button onClick="send()">구글 로그인</button>
</body>
</html>



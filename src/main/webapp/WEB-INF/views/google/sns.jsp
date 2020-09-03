<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바스크립트를 이용한 구글 로그인</title>
<script src="https://apis.google.com/js/platform.js?onload=gauthInit" async defer></script>
<meta name="google-signin-client_id" content="3151950286-3g6gbes60sivr1lk9lofh7uppua58e0d.apps.googleusercontent.com">

</head>
<body>
	<span id="msg">사용자의 상태</span>
	
	<!-- <div class="g-signin2" data-onsuccess="onSignIn"></div> -->
	
	<!-- 개발자가 정의한 버튼으로 처리해 본다 -->
	<button></button>
	
</body>
</html>

<script>
var gauth; //라이브러리를 가리킬 변수

function gauthInit(){
	//구글에서 제공하는 로그인 api 객체가 로드되면.. 
	//주의( 로그인 api 사용은 선택이다..하지만 사용하면 편하다)
	gapi.load("auth2", function(){
		//alert("라이브러리 로드 성공");		
		//이 시점부터 api 사용가능!~!
		
		//개발자가 부여받은 클라이언트 아이디로 auth2 라이브러리를 초기화해야함
		gauth=gapi.auth2.init({
			client_id:"3151950286-3g6gbes60sivr1lk9lofh7uppua58e0d.apps.googleusercontent.com"
		});
		
		//초기화 마져 완료되면..
		gauth.then(function(){
			//로그인 상태에 따라 알맞는 처리를 할 수 있다.
			if(gauth.isSignedIn.get()){//로그인 상태라면..
				document.getElementById("msg").innerText="로그인중";
				document.querySelector("button").innerText="Log out";
			}else{//로그아웃 상태라면...
				document.getElementById("msg").innerText="로그아웃중";
				document.querySelector("button").innerText="Log In";
			}
		});
	});
	
	//버튼에 이벤트 부여하기
	//구글에서 제공하는 디폴트 버튼 말고, 개발자가 주도하여 프로그래밍적으로 
	//로그인 로그아웃을 구현 
	document.querySelector("button").addEventListener("click",function(){
		//로그인상태이면 로그아웃을 구글측에 요청하고,
		if(gauth.isSignedIn.get()){//로그인 상태라면..
			
			gauth.signOut().then(function(){
				document.getElementById("msg").innerText="로그아웃중";
				document.querySelector("button").innerText="Log In";
			});
		}else{//로그아웃상태이면 구글측에 로그인을 요청한다
			gauth.signIn().then(function(){
				document.getElementById("msg").innerText="로그인중";
				document.querySelector("button").innerText="Log out";
			});
		}
		
	});
	
	
}



function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}
</script>










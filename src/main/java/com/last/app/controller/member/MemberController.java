package com.last.app.controller.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.last.app.aop.exception.MemberNotFoundException;
import com.last.app.domain.Member;
import com.last.app.model.member.MemberService;
import com.last.app.sns.NaverConnectionFactory;
import com.last.app.sns.NaverOAuth2Operations;
import com.last.app.sns.NaverOAuth2Parameters;


@Controller
public class MemberController {
	private static Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	//구글 관련	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters oAuth2Parameters;

	
	//네이버 관련	
	@Autowired
	private NaverConnectionFactory naverConnectionFactory;
	@Autowired
	private NaverOAuth2Parameters naverOAuth2Parameters;
	
	@Autowired
	private MemberService memberService;
	
	//회원이 보게될 동의 및 로그인 화면
	@GetMapping("/sns/logout")
	public String logout(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		
		model.addAttribute("msg","로그아웃 되었습니다.");
		model.addAttribute("url","/");
		return "result/message";
	}
	
	//회원이 보게될 동의 및 로그인 화면
	@GetMapping("/sns/login")
	public String googleForm(Model model) {
		/*------------------------------------------------------
		 구글 로그인 URL생성
		------------------------------------------------------*/
		OAuth2Operations oAuth2Operations=googleConnectionFactory.getOAuthOperations();
		String google_url = oAuth2Operations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		logger.info("자동 생성된 google_url은 "+google_url);
		model.addAttribute("google_url" , google_url);

		/*------------------------------------------------------
		 네이버 로그인 URL생성
		------------------------------------------------------*/
		NaverOAuth2Operations naverOAuth2Operations = naverConnectionFactory.getNaverOAuth2Operations();
		String naver_url = naverOAuth2Operations.buildAuthenticateUrl(NaverOAuth2Operations.AUTHORIZATION_CODE, naverOAuth2Parameters);
		model.addAttribute("naver_url" , naver_url);
		
		/*------------------------------------------------------
		카카오 로그인 URL생성
		------------------------------------------------------*/
		String kakao_url="";
		model.addAttribute("kakao_url" , kakao_url);
		
		return "sns/joinForm";
	}
	
	//구글의 콜백처리
	@GetMapping("/sns/google/callback")
	public String googleCallback(HttpServletRequest request) {
		String code = request.getParameter("code");
		logger.info("구글에서 콜백받아온 코드는 "+code);
		
		/*----------------------------------------------
		 1단계: 토큰 얻어오기
		----------------------------------------------*/ 
		OAuth2Operations oAuth2Operations=googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant=oAuth2Operations.exchangeForAccess(code,oAuth2Parameters.getRedirectUri() , null);
		String accessToken = accessGrant.getAccessToken();
		logger.info("얻어온 토큰은 "+accessToken);
		//토큰은 만료기간이 있으므로, 만일 기간이 만료되면 자동연장을 처리..
		Long expireTime = accessGrant.getExpireTime();
		if(expireTime !=null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();//갱신!!!
		}
		
		/*----------------------------------------------
		 2단계: 회원정보 얻어오기
		----------------------------------------------*/ 
		HttpURLConnection connection=null;
		URL url = null;
		BufferedReader buffr=null;
		StringBuilder sb = new StringBuilder();
		
		try {
			url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken);
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			//connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			connection.setConnectTimeout(10000);       //컨텍션타임아웃 10초
			connection.setReadTimeout(5000);           //컨텐츠조회 타임아웃 5총
			
			int responseCode = connection.getResponseCode();
			logger.info("Response Code is "+responseCode);
			
			Charset charset = Charset.forName("UTF-8");
			buffr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String data=null;
			while(true) {
				data = buffr.readLine();
				if(data==null)break;
				sb.append(data);
			}
			logger.info("읽어온 데이터는 : "+sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/*----------------------------------------------
		 3단계: 로그인/가입 여부 판단하기
		 -우리 DB 존재 여부에 따라
		   (1)만일 우리 DB에 존재하는 회원이면 로그인처리 
		   (2)만일 우리 DB에 비존재 회원이면 가입 후 로그인 
		----------------------------------------------*/
		String data = sb.toString();
		JSONParser jsonParser=new JSONParser();
		JSONObject jsonObject=null;
		
		Member member = new Member();
		
		try {
			jsonObject = (JSONObject)jsonParser.parse(data);
			member.setSns_id(jsonObject.get("id").toString());
			member.setEmail(jsonObject.get("email").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("vo 의 id값은 "+member.getSns_id());
		logger.info("vo 의 email값은 "+member.getEmail());
		
		HttpSession session=request.getSession();
		
		if(member.getSns_id()==null) { //프로필 얻기 실패라면 구글 인증이 안된 회원임
			logger.info("프로필을 얻지 못했습니다");
			throw new MemberNotFoundException("로그인이 필요한 서비스입니다");
		}else {
			Member vo = memberService.select(member);//우리 서버에 등록된 회원인지 조사
			
			if(vo==null) {//프로필은 얻어왔으나 우리 DB에 없을 경우 가입처리
				logger.info("프로필은 있으나, 우리회원으로 가입한 적이 없습니다");
				memberService.insert(member);
			}else {//우리 DB에 있을 경우엔 로그인
				logger.info("프로필도 있고, 우리회원가입되어 있습니다");
				session.setAttribute("member", member);
			}
		}
		return "redirect:/";
	}

	//네이버의 콜백처리
	@GetMapping("/sns/naver/callback")
	public String naverCallback(HttpServletRequest request) {
		return null;
	}
	
	//카카오의 콜백처리
	@GetMapping("/sns/kakao/callback")
	public String kakaoCallback(HttpServletRequest request) {
		return null;
	}

}











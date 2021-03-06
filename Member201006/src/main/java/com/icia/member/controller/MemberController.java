package com.icia.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.member.api.KakaoJoinApi;
import com.icia.member.api.KakaoLoginApi;
import com.icia.member.api.NaverJoinApi;
import com.icia.member.api.NaverLoginApi;
import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoJoinApi kakaoJoinApi;
	
	@Autowired
	private KakaoLoginApi kakaoLoginApi;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private NaverLoginApi naverLoginApi;
	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	
	//회원가입 페이지 이동
	@RequestMapping(value="/memberjoinform")
	public String join() {
		return "join";
	}
	
	//로그인 페이지 이동
	@RequestMapping(value="/memberloginform")
	public String login() {
		return "login";
	}
	
	//회원가입
	@RequestMapping(value="/memberjoin")
	public ModelAndView join(@ModelAttribute MemberDTO member) {
		System.out.println(member.toString());
		mav = memberService.memberJoin(member);

		return mav;
	}
	
	//로그인
	@RequestMapping(value="/memberlogin")
	public ModelAndView login(@ModelAttribute MemberDTO member) {
		mav = memberService.memberLogin(member);
		return mav;
	}
	
	//목록 조회
	@RequestMapping(value="/memberlist")
	public ModelAndView memberList() {
		mav = memberService.memberList();
		return mav;
	}
	
	//회원 조회
	@RequestMapping(value="/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid) {
		mav = memberService.memberView(mid);
		return mav;
	}
	
	//회원 삭제
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav = memberService.memberDelete(mid);
		return mav;
	}
	
	//회원 수정 정보가져오기
	@RequestMapping(value="/memberupdate")
	public ModelAndView memberUpdate(@RequestParam("mid") String mid) {
		mav = memberService.memberUpdate(mid);
		return mav;
	}
	
	//회원 수정
	@RequestMapping(value="/memberupdateprocess")
	public ModelAndView memberUpdateProcess(@ModelAttribute MemberDTO member) {
		mav = memberService.memberUpdateProcess(member);
		return mav;
	}
	
	//아이디 중복확인(ajax)
	//@ResponseBody : return을 text로 줄수있는 어노테이션 (원래는 jsp(주소값) 로 감)
	@RequestMapping(value="/idoverlap")
	public @ResponseBody String idOverLap(@RequestParam("mid") String mid) {
		String resultMsg = memberService.idOverlap(mid);
		System.out.println(resultMsg);
		return resultMsg;
	}
	
	//ajax 이용한 상세조회
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("전달받은 값 : "+mid);
		MemberDTO memberView = memberService.memberViewAjax(mid);
		return memberView;
	}

	//ajax 삭제
	@RequestMapping(value="/ajaxdelete")
	public @ResponseBody String ajaxDelete(@RequestParam("mid") String mid) {
		
		return null;
	}
	
	//로그아웃
	@RequestMapping(value="/memberlogout")
	public String memberLogout() {
		session.invalidate();
		return "login";
	}
	
	//카카오 서버 로그인
	@RequestMapping(value="/kakaojoin")
	public ModelAndView kakaoJoin(HttpSession session) {
		String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl",kakaoUrl);	
		mav.setViewName("KakaoLogin");
		return mav;
	}
	
	//카카오 서버 인증 통과 후 처리
	@RequestMapping(value="/kakaojoinok")
	public ModelAndView kakaoJoinOk(@RequestParam("code") String code, HttpSession session) {
		JsonNode token = kakaoJoinApi.getAccessToken(code);
		JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		System.out.println("profile : "+profile);
		//profile에 담긴 id값을 가지고 MemberJoin.jsp로 이동
		String kakaoId = profile.get("id").asText();
		mav = new ModelAndView();
		mav.addObject("kakaoId", kakaoId);
		mav.setViewName("join");
		return mav;
	}
	
	//카카오 로그인
	@RequestMapping(value="/kakaologin")
	public ModelAndView kakaoLogin(HttpSession session) {
		String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl",kakaoUrl);	
		mav.setViewName("KakaoLogin");
		return mav;
	}
	
	//카카오 로그인 2
	@RequestMapping(value="/kakaologinok")
	public ModelAndView kakaoLoginOk(@RequestParam("code")String code) {
		JsonNode token = kakaoLoginApi.getAccessToken(code);
		JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
		
		mav = memberService.kakaoLogin(profile);
		return mav;
	}
	
	//네이버 회원가입 1
	@RequestMapping(value="/naverjoin")
	public ModelAndView naverJoin(HttpSession session) {
		String naverUrl = naverJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl",naverUrl);	
		mav.setViewName("NaverLogin");
		return mav;
	}
	
	//네이버 회원가입 2
	@RequestMapping(value="/naverjoinok")
	public ModelAndView naverJoinOk(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws IOException, ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String) userInfo.get("id");
		mav.addObject("naverId",naverId);	
		mav.setViewName("join");
		return mav;
	}
	
	//네이버 로그인 1
	@RequestMapping(value="/naverlogin")
	public ModelAndView naverLogin(HttpSession session) {			String naverUrl = naverLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl",naverUrl);	
		mav.setViewName("NaverLogin");			
		return mav;
	}
	
	//네이버 로그인 2
	@RequestMapping(value="/naverloginok")
	public ModelAndView naverLoginOk(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) throws IOException, ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
		String profile = naverLoginApi.getUserProfile(oauthToken);
		mav = memberService.naverLogin(profile);
		return mav;
		}	
	
}

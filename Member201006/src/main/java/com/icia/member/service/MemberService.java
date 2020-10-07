package com.icia.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private HttpSession session;
	//ModelAndView 클래스 : 데이터와 화면을 모두 저장하는 클래스
	//Autowired 안쓰니 주의할것
	private ModelAndView mav; 
	
	//회원가입
	public ModelAndView memberJoin(MemberDTO member) {
		int joinResult = memberDAO.memberJoin(member);
		mav = new ModelAndView();
		if(joinResult>0) {
			//회원가입 성공시  MemberLogin.jsp 출력 (화면지정시 setViewName사용)
			mav.setViewName("login");
		}else {
			//회원가입 실패시 MemberJoinFail.jsp 출력
			mav.setViewName("MemberJoinFail");
		}
		return mav;
	}

	//로그인
	public ModelAndView memberLogin(MemberDTO member) {
		mav = new ModelAndView();
		
		String loginId = memberDAO.memberLogin(member);
		if(loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("main");
		}else {
			mav.setViewName("LoginFail");
		}
		return mav;
	}

	//목록 조회
	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<MemberDTO> memberList = memberDAO.memberList();
		mav.addObject("memberList", memberList);
		mav.setViewName("list");
		return mav;
	}
	
	//상세조회
	public ModelAndView memberView(String mid) {
		mav = new ModelAndView();	
		MemberDTO memberView = memberDAO.memberView(mid);
		mav.addObject("memberView", memberView);
		mav.setViewName("memberview");
		return mav;
	}

	//회원삭제
	public ModelAndView memberDelete(String mid) {
		mav = new ModelAndView();
		int deleteResult = memberDAO.memberDelete(mid);
		if(deleteResult>0) {
			mav.setViewName("redirect:/memberlist");
		}else {
			mav.setViewName("MemberDeleteFail");
		}
		return mav;
	}

	//정보수정 불러오기
	public ModelAndView memberUpdate(String mid) {
		mav = new ModelAndView();
		//String mid = (String)session.getAttribute("loginId"); 세션값 불러오기
		MemberDTO memberUpdate = memberDAO.memberUpdate(mid);
		mav.addObject("memberUpdate", memberUpdate);
		mav.setViewName("update");
		return mav;
	}

	//정보수정
	public ModelAndView memberUpdateProcess(MemberDTO member) {
		int updateResult = memberDAO.memberUpdateProcess(member);
		mav = new ModelAndView();
		if(updateResult>0) {
			mav.setViewName("main");
		}else {
			mav.setViewName("MemberUpdateFail");
		}
		return mav;
	}
	
	//아이디 중복확인(ajax)
	public String idOverlap(String mid) {
		String checkResult = memberDAO.idOverlap(mid);
		String resultMsg = null;
		if(checkResult == null)
			resultMsg = "OK";
		else
			resultMsg = "NO";
		return resultMsg;
	}

	//ajax 이용한 상세조회
	public MemberDTO memberViewAjax(String mid) {
		MemberDTO memberView = memberDAO.memberView(mid);
		return memberView;
	}
	
	//카카오 로그인
	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		String loginId = memberDAO.kakaoLogin(kakaoId);
		
		session.setAttribute("loginId", loginId);
		mav.setViewName("main");
		return mav;
	}
	

}

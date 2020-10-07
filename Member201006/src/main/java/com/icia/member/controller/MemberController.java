package com.icia.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	private ModelAndView mav;
	
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
}

package com.icia.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.member.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	//회원가입
	public int memberJoin(MemberDTO member) {
		return sql.insert("Member.memberJoin", member);
	}
	
	//로그인
	public String memberLogin(MemberDTO member) {
		return sql.selectOne("Member.memberLogin", member);
	}
	
	//목록 조회
	public List<MemberDTO> memberList() {
		return sql.selectList("Member.memberList");
	}
	
	//상세 조회
	public MemberDTO memberView(String mid) {
		return sql.selectOne("Member.memberView", mid);
		
	}

	//회원 삭제
	public int memberDelete(String mid) {
		return sql.delete("Member.memberDelete", mid);
	}
	
	//정보수정 불러오기
	public MemberDTO memberUpdate(String mid) {
		return sql.selectOne("Member.memberUpdate", mid);
	}

	//정보수정
	public int memberUpdateProcess(MemberDTO member) {
		return sql.update("Member.memberUpdateProcess", member);
	}

}
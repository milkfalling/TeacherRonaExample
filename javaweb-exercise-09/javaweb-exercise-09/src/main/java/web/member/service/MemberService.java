package web.member.service;

import java.util.List;

import web.member.bean.Member;

public interface MemberService {
	
	boolean register(Member member); //需帶有商業邏輯的命名

	Member edit(Member member);
	
	Member login(Member member);
	
	List<Member> findAll();
	
	
	
}

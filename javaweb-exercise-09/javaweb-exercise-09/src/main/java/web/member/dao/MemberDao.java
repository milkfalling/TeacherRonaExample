package web.member.dao;

import java.util.List;

import web.member.bean.Member;

public interface MemberDao {
	
	int insert(Member member);
	
	int updateByUsername(Member member);
	
	Member selectByUsername(String username);
	
	Member selectByUsernameAndPassword(Member member);
	
	List<Member> selectAll();
	
	int add(Member member);

}

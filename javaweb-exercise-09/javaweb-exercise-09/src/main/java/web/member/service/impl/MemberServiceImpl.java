package web.member.service.impl;

import java.sql.Connection;
import java.util.Base64;
import java.util.List;

import web.member.bean.Member;
import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.service.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDao dao;

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

	@Override
	public boolean register(Member member) {
		String username = member.getUsername();
		if (username.length() < 5 || username.length() > 50) // length()拿到string的長度
			return false;

		String password = member.getPassword();
		if (password.length() < 6 || password.length() > 12) {
			return false;
		}

//		String nickname = member.getNickname();
//		if (nickname.length() < 1 || nickname.length() > 20) {
//			return false;
//		}

		if (dao.selectByUsername(username) != null) {
			return false;
		}
		
		return dao.insert(member) >= 1;
	} // 繼承MemberService介面

	@Override
	public Member edit(Member member) {
		String base64 = member.getAvatarBase64();
		if (base64 != null && !base64.isEmpty()) {
			byte[] avatar = Base64.getDecoder().decode(base64);
			member.setAvatar(avatar);
		}
		
		String password = member.getPassword();
		if (password != null && !password.isEmpty() && (password.length() < 6 || password.length() > 12)) {
			return null;
		}

		String nickname = member.getNickname();
		if (nickname.length() < 1 || nickname.length() > 20) {
			return null;
		}

		int resultCount = dao.updateByUsername(member);
		if (resultCount >= 1) {
			return member;
		} else {
			return null;
		}
	}

	@Override
	public Member login(Member member) {
		String username = member.getUsername();
		if (username == null || username.isEmpty()) // length()拿到string的長度
			return null;

		String password = member.getPassword();
		if (password == null || password.isEmpty()) {
			return null;
		}
		return dao.selectByUsernameAndPassword(member);
	}

	@Override
	public List<Member> findAll() {
		return dao.selectAll();
	}
}

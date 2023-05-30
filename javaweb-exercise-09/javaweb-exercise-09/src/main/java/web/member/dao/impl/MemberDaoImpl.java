package web.member.dao.impl;

import static util.CommenConstant.url;
import static util.CommenConstant.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import web.member.bean.Member;
import web.member.dao.MemberDao;

public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;

	public MemberDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Member member) {
		String sql = "insert into MEMBER(USERNAME, PASSWORD, NICKNAME) values(?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getNickname());
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	

	@Override
	public Member selectByUsername(String username) {
		String sql = "select * from MEMBER where username = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Member member = new Member();
					member.setId(rs.getInt("id"));
					member.setUsername(rs.getString("username"));
					member.setPassword(rs.getString("password"));
					member.setNickname(rs.getString("nickname"));
					member.setPass(rs.getBoolean("pass"));
					return member;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // 繼承MemberDao介面

	@Override
	public int updateByUsername(Member member) {
		StringBuilder sql = new StringBuilder("update MEMBER ").append("set NICKNAME = ?");

		String password = member.getPassword();
		if (password != null && !password.isEmpty()) {
			sql.append(", PASSWORD = ?");
		}
		
		byte[] avatar = member.getAvatar();
		if (avatar != null && avatar.length != 0) {
			sql.append(", AVATAR = ?");
		}

		sql.append(" where USERNAME = ? ");
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, member.getNickname());
				int positionOfUsername = 2;
				if (password != null && !password.isEmpty()) {
					pstmt.setString(positionOfUsername++, member.getPassword());
					pstmt.setBytes(positionOfUsername++, member.getAvatar());
			}
				pstmt.setString(positionOfUsername, member.getUsername());
				return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Member selectByUsernameAndPassword(Member member) {
		String sql = "select * from MEMBER where username = ? and password = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					member = new Member();
					member.setId(rs.getInt("id"));
					member.setUsername(rs.getString("username"));
					member.setPassword(rs.getString("password"));
					member.setNickname(rs.getString("nickname"));
					member.setRoleId(rs.getInt("role_id"));
					member.setAvatar(rs.getBytes("avatar"));

					return member;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		String sql = "select * from Member";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			List<Member> list = new ArrayList<>();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("password"));
				member.setNickname(rs.getString("nickname"));
				member.setPass(rs.getBoolean("pass"));
				member.setLastUpdateDate(rs.getTimestamp("last_update_date"));
				member.setAvatar(rs.getBytes("avatar"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int add(Member member) {
		String sql = "insert into MEMBER(USERNAME, PASSWORD, NICKNAME) values(?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}

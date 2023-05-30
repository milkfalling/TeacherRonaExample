package web.member.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Member")
public class Member implements Serializable{ 
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username; // 使用者名稱
	private String password; // 密碼
	private String nickname;
	private Boolean pass; // 帳號啟用記號
	private Timestamp lastUpdateDate;
	private String avatarBase64;
	private byte[] avatar;
	private Integer roleId;
	
	

	

	public Member() {//沒有參數的建構式
		
	}

	public Member(Integer id, String username, String password, String nickname, Boolean pass,
			Timestamp lastUpdateDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.pass = pass;
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public String getAvatarBase64() {
		return avatarBase64;
	}

	public void setAvatarBase64(String avatarBase64) {
		this.avatarBase64 = avatarBase64;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
	

package test.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.servlet.annotation.WebServlet;

public class Member implements Serializable{ 
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username; // 使用者名稱
	private String password; // 密碼
	private Boolean pass; // 帳號啟用記號
	private Timestamp lastUpdateDate;
	private String img;
	
	
	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public Member() {//沒有參數的建構式
		
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
	
}

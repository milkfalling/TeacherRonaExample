package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.member.bean.Member;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson(); //為了轉譯Json
		Member member = gson.fromJson(req.getReader(), Member.class);
		member = service.login(member);
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", member != null);
		
		if (member != null) {
			if (
				req.getSession(false) != null) {
				req.changeSessionId(); // ←產生新的Session ID
			} // ↓此屬性物件即用來區分是否登入中
			
			req.getSession().setAttribute("member", member);
			respBody.addProperty("nickname", member.getNickname());
			
			} else {
			respBody.addProperty("message", "使用者名稱或密碼錯誤");
			}
		
		resp.getWriter().write(respBody.toString());
	}
	
}

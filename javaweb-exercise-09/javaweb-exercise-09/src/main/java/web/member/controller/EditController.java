package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.member.bean.Member;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;

@WebServlet("/member/edit")
public class EditController extends HttpServlet {
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
		
		//放入session, 記得登入的人是誰
		HttpSession session = req.getSession();
		Member seMember = (Member) session.getAttribute("member");
		member.setUsername(seMember.getUsername());
		
		member = service.edit(member);
		JsonObject respBody = new JsonObject();
		
		respBody.addProperty("successful", member != null);
		if (member == null) {
			respBody.addProperty("message","修改失敗");
		}else {
			   JsonObject respMemer = new JsonObject();
			   respMemer.addProperty("username", member.getUsername());
			   respMemer.addProperty("nickname", member.getNickname());
			   respMemer.addProperty("password", member.getPassword());
		}
		resp.getWriter().write(gson.toJson(member));
	}
	
}

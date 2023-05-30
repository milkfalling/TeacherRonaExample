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

@WebServlet("/member/getInfo")
public class GetInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//FIXME 暫時寫死稍後修正
		Gson gson = new Gson(); //為了轉譯Json
//		Member member = new Member();
		//取得session物件
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("member");
		
		
		resp.getWriter().write(gson.toJson(member));
	}
	
}

package web.member.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/member/getAllInfo")
public class GetAllInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	Gson gson = new Gson(); //為了轉譯Json
	
	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Member> list = service.findAll();
		resp.getWriter().write(gson.toJson(list));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Member member = gson.fromJson(req.getReader(), Member.class);
		Boolean result = service.register(member);
		resp.getWriter().write(gson.toJson(result));
		
	}
	
}

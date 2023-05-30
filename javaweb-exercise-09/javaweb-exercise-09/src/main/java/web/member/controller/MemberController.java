package web.member.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.member.bean.Member;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new GsonBuilder()
	.setDateFormat("yyyy/MM/dd HH:mm:ss")
    .create();
	private static final MemberService SERVICE = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = gson.fromJson(req.getReader(), Member.class);
		boolean result = SERVICE.register(member);
		JsonObject reqBody = new JsonObject();
		reqBody.addProperty("successful", result);
		String message = "註冊" + (result ? "成功" : "失敗");
		JsonObject respBody = new JsonObject();		
		respBody.addProperty("message", message);
		resp.getWriter().write(respBody.toString());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取得網址後面的字串
		String pathInfo = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			List<Member> memberList = SERVICE.findAll();
			memberList.forEach( member -> {
				String avatarBase64 = Base64.getEncoder().encodeToString(member.getAvatar());
				member.setAvatarBase64(avatarBase64);
				member.setAvatar(null);
			});
			resp.getWriter().write(gson.toJson(memberList));
			
		}else {
			pathInfo = pathInfo.substring(1);
			String[] pathVariables = pathInfo.split("/");
			
			Member member = new Member();
			member.setUsername(pathVariables[0]);
			member.setPassword(pathVariables[1]);
			member = SERVICE.login(member);

			if (member != null) {
				if (req.getSession(false) != null) {
					req.changeSessionId(); // ←產生新的Session ID
				} // ↓此屬性物件即用來區分是否登入中
				HttpSession session = req.getSession();//用getSession拿出自己的新的session(ID)
				session.setAttribute("member", member); //用setAttribute存取所有資料
			}
			String avatarBase64 = Base64.getEncoder().encodeToString(member.getAvatar());
			member.setAvatarBase64(avatarBase64); //因為資料庫類型為blomb, 所以要轉為base64輸出到前端
			resp.getWriter().write(gson.toJson(member));
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = gson.fromJson(req.getReader(), Member.class);
		HttpSession session = req.getSession();
		Member seMember = (Member) session.getAttribute("member");
		Integer roleId = seMember.getRoleId();
		if (roleId == 2) { // 當前的使用者登入就用當前的session, 就只能改自己的
			Integer id = seMember.getId();
			member.setId(id);
		}
		
		Member result = SERVICE.edit(member);
		JsonObject respBody = new JsonObject();		
		respBody.addProperty("successful", result != null);
		String message = "儲存" + (result != null ? "成功" : "失敗");
		respBody.addProperty("message", message);
		resp.getWriter().write(respBody.toString());
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//拿到新產生的session ID(當前登入的資料)去做寫出(顯示在編輯頁)
		Member member = (Member) req.getSession().getAttribute("member");
		member.setPassword(null);//設定帶入的密碼資料為空
		resp.getWriter().write(gson.toJson(member));
	}
	
	@Override     
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {         
		req.getSession().invalidate();         
		JsonObject respBody = new JsonObject();                 
		respBody.addProperty("successful", true);         
		resp.getWriter().write(respBody.toString());          
		
	}

}

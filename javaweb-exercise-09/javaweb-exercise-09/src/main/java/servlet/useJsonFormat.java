package servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import test.web.pojo.Member;

@WebServlet("/useJsonFormat")
public class useJsonFormat extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Member member = gson.fromJson(req.getReader(), Member.class); // 這樣就可以從前端讀入轉成member的物件
		
		member.setPass(true);
		try(
				InputStream is = getServletContext().getResourceAsStream("/WEB-INF/img/xxx.jpg");
				BufferedInputStream bis = new BufferedInputStream(is);	
			){
			   byte[] bytes = new byte[bis.available()];
			   bis.read(bytes);
			   String base64Str = Base64.getEncoder().encodeToString(bytes);
			   member.setImg(base64Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String jsonStr = gson.toJson(member); //序列化成json格式回應
		resp.getWriter().write(jsonStr);
		
		
		
		
		
		//		Gson gson = new Gson();
//		Member member = gson.fromJson(req.getReader(), Member.class); // 這樣就可以從前端讀入轉成member的物件
//		
//		member.setPass(true);
//		
//		String jsonStr = gson.toJson(member); //序列化成json格式回應
//		resp.getWriter().write(jsonStr);
		
		
	}

}

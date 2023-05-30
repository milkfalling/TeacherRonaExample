package servlet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/test")
public class testServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(req.getReader(), JsonObject.class);
		String base64Str = obj.get("img").getAsString();
		byte[] bytes = Base64.getDecoder().decode(base64Str);
		
		try(
			FileOutputStream fos = new FileOutputStream("C:/Users/Tibame_T14/Desktop/ronatest.jpg");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
				
		) {
			
		bos.write(bytes);
		bos.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		BufferedReader br = req.getReader();
//		String line; //以一橫列
//		while ((line = br.readLine()) != null) {
//			System.out.println(line);
//			
//		}
//		
//		//------------------------------------
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("successful", true);
//		respBody.addProperty("code", 1);
//		respBody.addProperty("msg", "使用JsonObject可隨意定義屬性");
//		JsonArray array = new JsonArray();
//		array.add("a");
//		array.add("b");
//		array.add("c");
//		array.add("d");
//		respBody.add("skills", array);
//		
//		resp.getWriter().write(new Gson().toJson(respBody));
//	}

}

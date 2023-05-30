package servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//從硬碟上讀入pdf
		String file = "/WEB-INF/pdf/01_Android導論.pdf";
		ServletContext context = getServletContext(); //取得應⽤系統環境物件
		
		try(
			InputStream is = context.getResourceAsStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
				
			){
			
			byte[] data = new byte[bis.available()]; //造空間籃子
			bis.read(data);
				
			//從pdf檔, 寫出至前端
			resp.setContentType("application/pdf"); //先製造出一個回應的content type
			OutputStream os = resp.getOutputStream();
			os.write(data);
			os.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

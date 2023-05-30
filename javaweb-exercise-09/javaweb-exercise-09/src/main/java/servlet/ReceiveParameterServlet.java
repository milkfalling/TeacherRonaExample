package servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ReceiveParameterServlet")
public class ReceiveParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String nickname = req.getParameter("nickname");
	String[] habbits = req.getParameterValues("habbits");
	System.out.println("nickname: " + nickname);
	System.out.println("habbits: " + Arrays.toString(habbits));
	resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	//---------------------------------------------------------------
//	Enumeration<String> names = req.getHeaderNames(); //拿到所有header的名稱
//	while (names.hasMoreElements()) { //走訪所有名稱
//		String name = names.nextElement();
//		System.out.print(name + ": ");
//		Enumeration<String> values = req.getHeaders(name); //拿到所有名稱的值
//		while (values.hasMoreElements()) { //走訪所有名稱的值
//			String value = values.nextElement(); 
//			System.out.print(value + "\t");
//		}
//		System.out.println();
	}
	
}


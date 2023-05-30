package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

//@WebServlet(urlPatterns = { "/registerSL",
//		"/REGISTER_SL" }, name = "registerSLServlet", loadOnStartup = 1, initParams = {
//				@WebInitParam(name = "name", value = "Rona"), @WebInitParam(name = "height", value = "155") }
//
//)

public class RegisterSLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final PrintWriter pw = resp.getWriter(); // final為常數(val), 盡量要加
		final Enumeration<String> paramNames = getInitParameterNames(); // 拿到名稱
		while (paramNames.hasMoreElements()) { // 直接幫忙走訪集合
			final String paramName = paramNames.nextElement();
			final String paramValue = getInitParameter(paramName); // 拿到值
			pw.append(paramName).append(": ").append(paramValue).append("\n");
		}
	}
}

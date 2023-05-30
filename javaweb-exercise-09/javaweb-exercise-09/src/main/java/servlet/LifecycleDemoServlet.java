package servlet;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LifecycleDemoServlet")
public class LifecycleDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LifecycleDemoServlet() {
      System.out.println("constructor()");
    }

    @Override
    public void init() throws ServletException {
    	System.out.println("init()");
    	super.init();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("service()");
    	super.service(req, resp);
    }
    
    @Override
    public void destroy() {
    	System.out.println("destroy()");
    	super.destroy();
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Time time = new Time(System.currentTimeMillis()); //System.currentTimeMillis()執行那一刻的毫秒
		response.getWriter().print(time); //append只吃文字
	}

	


}

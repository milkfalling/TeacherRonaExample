package test.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context物件初始化完成");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Context物件銷毀完成");
		
	}
	
	

}

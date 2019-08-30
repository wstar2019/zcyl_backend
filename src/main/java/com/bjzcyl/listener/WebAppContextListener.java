package com.bjzcyl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.*;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bjzcyl.util.Const;

import java.io.IOException;
import java.util.Properties; 

public class WebAppContextListener implements ServletContextListener {
	Scheduler scheduler = null;
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (scheduler != null) {
			try {
				scheduler.shutdown();
			} catch (SchedulerException e) {
				throw new RuntimeException("微信任务调度器关闭失败", e);
			}
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		
		Properties prop = new Properties();
		String path=event.getServletContext().getContextPath();
		event.getServletContext().setAttribute("basePath",path);
		try {
			prop.load(WebAppContextListener.class.getClassLoader().getResourceAsStream("baseconfig.properties"));
		} catch (IOException e) {
			throw new RuntimeException("加载资源文件失败");
		}

		Const.DOMAIN = prop.getProperty("wxbaseurl"); 

	}

}

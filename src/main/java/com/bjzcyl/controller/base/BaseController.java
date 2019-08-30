package com.bjzcyl.controller.base;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.client.Traveler;
import com.bjzcyl.entity.system.SysManager;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.Logger;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.TravelerConst;
import com.bjzcyl.util.UuidUtil;


public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 6357869213649815390L;
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	public Page getPage(){
		return new Page();
	}
	public String getTravlerName(){
		Session session = Jurisdiction.getSession();
		Traveler user = (Traveler) session.getAttribute(TravelerConst.SESSION_TRAVELER);
		if (user != null) {
			return user.getNAME();
		}else{
			return "";
		}
	}
	public String getUserLGID(){
		Session session = Jurisdiction.getSession();
		SysManager user = (SysManager) session.getAttribute(Const.SESSION_USER);
		if (user != null) {
			return user.getLG_ID();
		}else{
			return "";
		}
	}
	public String getUserID(){
		Session session = Jurisdiction.getSession();
		SysManager user = (SysManager) session.getAttribute(Const.SESSION_USER);
		if (user != null) {
			return user.getID();
		}else{
			return "";
		}
	}
	public String getUserName(){
		Session session = Jurisdiction.getSession();
		SysManager user = (SysManager) session.getAttribute(Const.SESSION_USER);
		if (user != null) {
			return user.getNAME();
		}else{
			return "";
		}
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
}

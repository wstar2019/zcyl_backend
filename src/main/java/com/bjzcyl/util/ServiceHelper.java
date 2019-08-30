package com.bjzcyl.util;

import com.bjzcyl.service.system.sys.ManagerManager;

public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		return Const.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static ManagerManager getUserService(){
		return (ManagerManager) getService("ManagerService");
	}
	
}

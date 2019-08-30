package com.bjzcyl.util;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Const {
	
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	
	public static final String APPLICATION_BASE_PATH = "basepath";
	public static final Map<String, String> MENUS = new HashMap<String,String>();
	public static final Integer PAGE_SIZE = 10;
	
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_MENU_RIGHTS = "sessionMenuRights";
	public static final String SESSION_PERM_RIGHTS = "sessionPermRights";
	public static final String SESSION_menuList = "menuList";
	public static final String SESSION_allmenuList = "allmenuList";
	public static final String SESSION_QX = "QX";
	public static final String SESSION_USERNAME = "USERNAME";
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";
	public static final String SYSNAME = "admin/config/SYSNAME.txt";
	public static final String PAGE = "admin/config/PAGE.txt";	
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";
	public static final String TOUR_IMAGE_FILE_PATH = "uploadFiles/uploadImgs/tour/";
	public static final String SN_IMAGE_FILE_PATH = "uploadFiles/uploadImgs/sn/";
	public static final String SA_IMAGE_FILE_PATH = "uploadFiles/uploadImgs/sa/";
	public static final String SP_IMAGE_FILE_PATH = "uploadFiles/uploadImgs/sp/";
	public static final String FP_IMAGE_FILE_PATH = "uploadFiles/uploadImgs/fp/";
	public static final String CS_IMAGE_FILE_PATH = "uploadFiles/uploadImgs/customer/";
	public static final String FILEPATHFILE = "uploadFiles/file/";
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)|(traveler)|(uploadFiles/tid/eid/(.*))|(uploadFiles/uploadImgs/(.*))).*";    //不对匹配该值的访问路径拦截（正则）
	public static final Long ACCESS_TOCKEN_CACHE_SECONDS = 7000L;
	public static final String SERVLET_CONTEXT = "servletContext";
	public static ApplicationContext WEB_APP_CONTEXT = null;
	public static String DOMAIN = null;
	
	public static final String OPERATION_ADD = "新增";
	public static final String OPERATION_EDIT = "编辑";
	public static final String OPERATION_DELETE = "删除";
	public static final String OPERATION_BACKUP = "备份";
	public static final String OPERATION_RESTORE = "还原";


}

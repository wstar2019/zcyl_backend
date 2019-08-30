package com.bjzcyl.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.bjzcyl.entity.system.Menus;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.service.system.sys.PermissionManager;

public class Jurisdiction {
	
	@SuppressWarnings("unchecked")
	public static boolean hasJurisdiction(String menuUrl, PermissionManager permService){
		String USERNAME = getUsername();
		Session session = getSession();
		List<Menus> menuList = (List<Menus>)session.getAttribute(USERNAME + Const.SESSION_allmenuList);
		String permRights = (String)session.getAttribute(USERNAME + Const.SESSION_PERM_RIGHTS);
		return readMenu(menuList, menuUrl, session, USERNAME, permRights, permService);
	}
	
	
	public static boolean readMenu(List<Menus> menuList, String menuUrl, Session session,
			String USERNAME, String permRights, PermissionManager permService){
		for(int i = 0; i < menuList.size(); i++){
			if(menuList.get(i).getMENU_URL().equals(menuUrl)){
				if(!menuList.get(i).isHasMenu()){
					return false;
				}else{
					String m_id = menuList.get(i).getMENU_ID();
					List<SysPermission> permList;
					Map<String, String> map = new HashMap<String, String>();
					try {
						permList = permService.listPermissionByMenuId(m_id);
						if(permList.size() > 0){
							for(int j = 0; j < permList.size(); j++){
								if(USERNAME.equals("admin")){
									map.put(permList.get(j).getENG_NAME(), "1");
								}
								else{
									if(RightsHelper.testRightsPermission(permRights, permList.get(j).getID())){
										map.put(permList.get(j).getENG_NAME(), "1");
									}
									else{
										map.put(permList.get(j).getENG_NAME(), "0");
									}
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					session.setAttribute(USERNAME + Const.SESSION_QX, map);
					return true;
				}
			}else{
				readMenu(menuList.get(i).getSubMenu(), menuUrl,session,USERNAME, permRights, permService);
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean buttonJurisdiction(String menuUrl, String type){
		String USERNAME = getUsername();
		Session session = getSession();
		List<Menus> menuList = (List<Menus>)session.getAttribute(USERNAME + Const.SESSION_allmenuList);
		readMenuButton(menuList,menuUrl,session,USERNAME,type);
		return true;
	}
	
	public static boolean readMenuButton(List<Menus> menuList,String menuUrl,Session session,String USERNAME, String type){
		for(int i=0;i<menuList.size();i++){
			if(menuList.get(i).getMENU_URL().equals(menuUrl)){
				if(!menuList.get(i).isHasMenu()){
					return false;
				}else{							
					return true;
				}
			}else{
				readMenuButton(menuList.get(i).getSubMenu(), menuUrl, session, USERNAME,type);
			}
		}
		return true;
	}
	
	public static String getUsername(){
		return getSession().getAttribute(Const.SESSION_USERNAME).toString();
	}
	
	@SuppressWarnings({ "unchecked" })
	public static Map<String, String> getHC(){
		return (Map<String, String>) getSession().getAttribute(getUsername() + Const.SESSION_QX);
	}
	
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
}

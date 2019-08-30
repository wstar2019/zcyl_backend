package com.bjzcyl.controller.system.login;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.system.Menus;
import com.bjzcyl.entity.system.SysManager;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.entity.system.SysRole;
import com.bjzcyl.service.system.menus.MenusManager;
import com.bjzcyl.service.system.sys.ManagerManager;
import com.bjzcyl.service.system.sys.RolesManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.DateUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.RightsHelper;
import com.bjzcyl.util.Tools;
@Controller
//@RequestMapping(value="/main")
public class LoginController extends BaseController {


	@Resource(name="RolesService")
	private RolesManager RolesService;	
	@Resource(name="ManagerService")
	private ManagerManager ManagerService;	
	@Resource(name="menusService")
	private MenusManager menusService;
	
	
	private String m_strUName;
	
	@RequestMapping(value="/front")
	public ModelAndView front()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //读取系统名称
				
		mv.setViewName("frontend/zbtj");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	@RequestMapping(value = "/login_toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME));
				
		mv.setViewName("system/index/login");
		mv.addObject("pd", pd);
		return mv;
	}

	@RequestMapping(value = "/login_login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("RC03030514", "").replaceAll("19881994NH", "").split(",R3C5,");
		if (null != KEYDATA && KEYDATA.length == 3) {
			
			Session session = Jurisdiction.getSession();
			String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);
			String code  = KEYDATA[2];
			if(null == code || "".equals(code)){
				errInfo = "nullcode";
			} else {
				String USERNAME = KEYDATA[0];
				String PASSWORD = KEYDATA[1];
				pd.put("USERNAME", USERNAME);
				if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){
					String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();
					pd.put("PASSWORD", passwd);
					pd = ManagerService.getManagerByNameAndPwd(pd);
					if (pd != null) {
						if(pd.get("STATE").toString().equals("1")){
							pd.put("LAST_LOGIN", DateUtil.getTime().toString());				
							
							SysManager user = new SysManager();
							user.setID(pd.getString("ID"));
							user.setNAME(pd.getString("NAME"));
							user.setPASS(pd.getString("PASS"));
							user.setLG_ID(pd.getString("LG_ID"));						
							user.setROLE_ID(pd.getString("ROLE_ID"));
							session.setAttribute(Const.SESSION_USER, user);
							session.removeAttribute(Const.SESSION_SECURITY_CODE);
							
							Subject subject = SecurityUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD);
							
							try {
								subject.login(token);
							} catch (AuthenticationException e) {
								errInfo = "身份验证失败！";
							}
						}
						else{
							errInfo = "stateerror";
						}
					} else {
						errInfo = "usererror";
						logBefore(logger, USERNAME + "登录系统密码或用户名错误");
					}
				} else {
					errInfo = "codeerror";
				}
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success";
					logBefore(logger, USERNAME + "登录系统");
				}
			}
		} else {
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value = "/main/{changeMenu}")
	public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Session session = Jurisdiction.getSession();
			SysManager user = (SysManager) session.getAttribute(Const.SESSION_USER);
			if (user != null) {
				pd.put("ROLE_ID", user.getROLE_ID());
				pd = RolesService.getRoleByRoleID(pd);
				if(pd == null)
				{
					pd = this.getPageData();
					pd.put("ROLE_ID", user.getROLE_ID());
				}
				SysRole role = new SysRole();				
				
				role.setROLE_MENU_IDS(pd.getString("ROLE_MENU_IDS"));
				role.setROLE_MENU_PERMISSION(pd.getString("ROLE_MENU_PERMISSION"));
				String menuRights = role.getROLE_MENU_IDS();
				String permRights = role.getROLE_MENU_PERMISSION();
				String USERNAME = user.getNAME();
				
				String _LOGINNAME = user.getLG_ID();
				String _UID = user.getID();
				m_strUName = USERNAME;
				
				session.setAttribute(USERNAME + Const.SESSION_MENU_RIGHTS, menuRights);
				session.setAttribute(USERNAME + Const.SESSION_PERM_RIGHTS, permRights);				
				session.setAttribute(Const.SESSION_USERNAME, user.getNAME());
				
				List<Menus> allmenuList = new ArrayList<Menus>();
				allmenuList = menusService.listAllMenusWithPermission("0", null, "");
									
				if(_LOGINNAME.equals("admin")){
					menuRights = "^";
					for(int i = 1; i <= 200; i++) {
						menuRights += i + "^";
					}
				}
				allmenuList = this.readMenu(allmenuList, menuRights);
				
				if(!_LOGINNAME.equals("admin")){
					allmenuList = this.readPermission(allmenuList, permRights);
				}
				
				session.setAttribute(USERNAME + Const.SESSION_allmenuList, allmenuList);
				
				pd.put("UAME", m_strUName);
				pd.put("LAME", _LOGINNAME);
				pd.put("UID", _UID);
				mv.addObject("pd", pd);
				mv.setViewName("system/index/main");
				mv.addObject("user", user);
				mv.addObject("menuList", allmenuList);
				
				logBefore(logger, "login successed！");
			} else {
				mv.setViewName("system/index/login");
			}
		} catch (Exception e) {
			mv.setViewName("system/index/login");
			logger.error(e.getMessage(), e);
		}
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME));
		mv.addObject("pd", pd);
		
		return mv;
	}

	public List<Menus> readMenu(List<Menus> menuList, String menuRights) {
		for (int i = 0; i < menuList.size(); i++) {
			menuList.get(i).setHasMenu(RightsHelper.testRightsMenu(menuRights, menuList.get(i).getMENU_ID()));
			if (menuList.get(i).isHasMenu()) {
				this.readMenu(menuList.get(i).getSubMenu(), menuRights);
			} else {
				menuList.remove(i);
				i--;
			}
		}
		return menuList;
	}
	
	public List<Menus> readPermission(List<Menus> menuList, String permRights) {
		for (int i = 0; i < menuList.size(); i++) {
			List<SysPermission> permList = menuList.get(i).getPermission();
			boolean perm = false;
			if(permList != null){
				for(int j = 0; j < permList.size(); j++){
					if(RightsHelper.testRightsPermission(permRights, permList.get(j).getID())){
						perm = true;
						break;
					}
				}
			}
			
			if(menuList.get(i).getMENU_URL().equals("#")){
				menuList.get(i).setHasMenu(true);
			}else{
				menuList.get(i).setHasMenu(perm);
			}
			
			if (menuList.get(i).isHasMenu()) {
				this.readPermission(menuList.get(i).getSubMenu(), permRights);
			} else {
				menuList.remove(i);
				i--;
			}
		}
		return menuList;
	}

	@RequestMapping(value = "/landing")
	public String tab() {
		return "system/index/landing";
	}

	@RequestMapping(value = "/login_default")
	public ModelAndView defaultPage(HttpSession session) throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("now", new Date().getTime());
		mv.setViewName("system/index/default");
		return mv;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout() {
		String USERNAME = Jurisdiction.getUsername();
		logBefore(logger, USERNAME + "退出系统");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Session session = Jurisdiction.getSession();
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(USERNAME + Const.SESSION_MENU_RIGHTS);
		session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
		session.removeAttribute(USERNAME + Const.SESSION_menuList);
		session.removeAttribute(USERNAME + Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute("changeMenu");
		
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		pd = this.getPageData();
		pd.put("msg", pd.getString("msg"));
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME));
		mv.setViewName("system/index/login");
		mv.addObject("pd", pd);
		return mv;
	}

	
//	@SuppressWarnings("rawtypes")
//	public Map<String, Map> getUQX(String LOGINNAME, String USERNAME, List<Menus> menuList, String permRights) {
//		
//		Map<String, Map> tMap = new HashMap<String, Map>();
//		
//		for (int i = 0; i < menuList.size(); i++) {
//			String m_id = menuList.get(i).getMENU_ID();
//			List<SysPermission> permList;
//			try {
//				permList = permissionService.listPermissionByMenuId(m_id);
//				if(permList.size() > 0){
//					Map<String, String> map = new HashMap<String, String>();
//					
//					for(int j = 0; j < permList.size(); j++){
//						if(RightsHelper.testRightsPermission(permRights, permList.get(j).getID())){
//							map.put(permList.get(j).getENG_NAME(), "1");
//						}
//						else{
//							map.put(permList.get(j).getENG_NAME(), "0");
//						}
//					}
//					tMap.put(menuList.get(i).getMENU_URL(), map);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			this.getUQX(LOGINNAME, USERNAME, menuList.get(i).getSubMenu(), permRights);
//		}
//		
//		return tMap;
//	}


}

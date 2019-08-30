package com.bjzcyl.controller.system.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.Menus;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.entity.system.TreeMenus;
import com.bjzcyl.entity.system.TreePermission;
import com.bjzcyl.service.system.menus.MenusManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.system.sys.ManagerManager;
import com.bjzcyl.service.system.sys.RolesManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

import utils.CurrentDateTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/sys")
public class RolesController extends BaseController {
	
	String menuUrl = "sys/listRole";
	
	@Resource(name="RolesService")
	private RolesManager RolesService;	
	
	@Resource(name="menusService")
	private MenusManager menusService;

	@Resource(name="ManagerService")
	private ManagerManager ManagerService;
		
	@Resource(name="LogService")
	private LogManager LogService;
	
	private List<TreeMenus> ConvertMenus(List<Menus> pArr){
		List<TreeMenus> tm = new ArrayList<TreeMenus>();
		
		for (Menus menus : pArr) {
			TreeMenus m = new TreeMenus();
			m.setMENU_ID(menus.getMENU_ID());
			m.setMENU_NAME(menus.getMENU_NAME());
			m.setPARENT_ID(menus.getPARENT_ID());
			m.setSubMenu(this.ConvertMenus(menus.getSubMenu()));
			tm.add(m);
		}
		return tm;
	}
	
	private List<TreePermission> ConvertPermission(List<Menus> pArr){
		List<TreePermission> tm = new ArrayList<TreePermission>();
		
		for (Menus menus : pArr) {
			TreePermission m = new TreePermission();
			m.setPermission_ID(menus.getMENU_ID());
			m.setPermission_NAME(menus.getMENU_NAME());
			m.setPARENT_ID(menus.getPARENT_ID());
			if(menus.getSubMenu().size() > 0){
				m.setSubPermission(this.ConvertPermission(menus.getSubMenu()));
			}else{
				if(menus.getPermission().size() > 0){
					List<TreePermission> subtm = new ArrayList<TreePermission>();
					for (SysPermission perm : menus.getPermission()) {
						TreePermission subm = new TreePermission();
						
						subm.setPermission_ID("perm_" + perm.getID());
						subm.setPermission_NAME(perm.getNAME());
						subm.setPARENT_ID(perm.getM_ID());
						subtm.add(subm);
					}
					m.setSubPermission(subtm);
				}
			}
			tm.add(m);
		}
		return tm;
	}
	
	@RequestMapping(value="/listRole")
	public ModelAndView listRole(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);		
		List<PageData>	RolesList = new ArrayList<PageData>();
		try{
			RolesList = RolesService.rolelistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.addObject("RolesList", RolesList);
		mv.addObject("pd", pd);		
				
		List<Menus> menuList = menusService.listAllMenus("0", "");
		List<TreeMenus> menuTList =  ConvertMenus(menuList);
		
		List<Menus> menuPermission = menusService.listAllMenusWithPermission("0", null, "");
		List<TreePermission> menuTPermission = ConvertPermission(menuPermission);
		
		JSONObject mObj = new JSONObject();
		JSONObject pObj = new JSONObject();
		
		String[] plugins = new String[3];
		plugins[0] = "wholerow";
		plugins[1] = "checkbox";
		plugins[2] = "types";
		
		mObj.put("plugins", plugins);
		pObj.put("plugins", plugins);
		
		JSONObject mCore = new JSONObject();
		JSONObject pCore = new JSONObject();
		
		JSONObject themes = new JSONObject();		
		themes.put("responsive", false);
		mCore.put("themes", themes);
		pCore.put("themes", themes);
		
		JSONArray mArr = JSONArray.fromObject(menuTList);
		JSONArray pArr = JSONArray.fromObject(menuTPermission);
		
		String mJSON = mArr.toString();
		String pJSON = pArr.toString();
		
		mJSON = mJSON.replaceAll("MENU_ID", "id").replaceAll("ICON", "icon").replaceAll("MENU_NAME", "text")
					.replaceAll("subMenu", "children").replaceAll("hasMenu", "checked");
		
		pJSON = pJSON.replaceAll("permission_ID", "id").replaceAll("ICON", "icon").replaceAll("permission_NAME", "text")
				.replaceAll("subPermission", "children").replaceAll("hasPermission", "checked");
		mCore.put("data", mJSON);
		pCore.put("data", pJSON);
		
		mObj.put("core", mCore);
		pObj.put("core", pCore);
		
		JSONObject types = new JSONObject();		
		JSONObject def = new JSONObject();
		def.put("icon", "fa fa-folder icon-state-warning icon-lg");
		JSONObject ff = new JSONObject();
		ff.put("icon", "fa fa-file icon-state-warning icon-lg");
		types.put("default", def);
		types.put("file", ff);
		
		mObj.put("types", types);
		pObj.put("types", types);
		
		mJSON = mObj.toString();
		pJSON = pObj.toString();
		
		mv.addObject("mTreeNodes", mJSON);
		mv.addObject("pTreeNodes", pJSON);
				
		mv.setViewName("system/sys/roles_list");
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
	}
	
	@RequestMapping(value="/saveRole")
	@ResponseBody
	public Object saveRoles(Page page) throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"insert_sys_role");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			
			CurrentDateTime dt = new CurrentDateTime();
			String dTime = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
			
			pd.put("ID", this.get32UUID());
			pd.put("ROLE_STATE", 1);
			pd.put("ROLE_DT", dTime);
			pd.put("ROLE_MENU_PERMISSION", "");
			pd.put("ROLE_MENU_IDS", "");
			
			RolesService.saveRole(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/deleteRole")
	@ResponseBody
	public Object deleteRole() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"delete_manager");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			RolesService.deleteRole(pd);
			ManagerService.initManagerRole(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	@RequestMapping(value="/updateRole")
	@ResponseBody
	public Object updateRole() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"update_role");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			CurrentDateTime dt = new CurrentDateTime();
			String dTime = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
			pd.put("ROLE_DT", dTime);
			
			RolesService.updateRole(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
}
package com.bjzcyl.controller.system.menus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.menus.MenusManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;


@Controller
@RequestMapping(value="/sys")
public class MenusController extends BaseController {
	
	String menuUrl = "menus/listMenus"; 
	
	@Resource(name="menusService")
	private MenusManager menusService;
	
	private List<PageData> menusList;
	private String tree = "";
	
	public void makeMenusList(String id, int level) throws Exception
	{
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PARENT_ID", id);
		List<PageData> lst = menusService.listMenusByUpperId(pd);
		if(lst.size() == 0)
		{
			return;
		}
		else
		{
			tree += "<ul>";
		}
		for(int i = 0; i < lst.size(); i++)
		{
			PageData pageData = lst.get(i);
			String cid = pageData.get("MENU_ID").toString();
			pageData.put("LEVEL", level);
			tree += "<li tr_id='" + pageData.get("MENU_ID") + "' class='tree" + pageData.get("MENU_ID")+ "'>" + pageData.get("MENU_NAME");
			menusList.add(pageData);
			makeMenusList(cid, level + 1);
			tree += "</li>";
		}
		tree += "</ul>";
	}
	
	@RequestMapping(value="/listMenus")
	public ModelAndView listMenus(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		pd.put("PARENT_ID", -1);
		page.setPd(pd);
		try{
			menusList = menusService.listMenusByUpperId(pd);//列出机构列表
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		tree = "";
		makeMenusList("0", 0);
		
		mv.setViewName("system/sys/menus_list");
		
		mv.addObject("strTree", tree);
		mv.addObject("menusList", menusList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
	}
		
	@RequestMapping(value="/saveMenus")
	public ModelAndView saveMenus(Page page) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}

		PageData pd = new PageData();
		pd = this.getPageData();
		pd.remove("MENU_ID");
		
		if(pd.get("MENU_ORDER") == null || pd.get("MENU_ORDER").toString().isEmpty())
		{
			pd.remove("MENU_ORDER");
		}
		if(pd.get("PARENT_ID") == null || pd.get("PARENT_ID").toString().isEmpty())
			pd.put("PARENT_ID", "0");
		if(pd.get("MENU_STATE") == null || pd.get("MENU_STATE").toString().isEmpty())
			pd.put("MENU_STATE", "1");
		try{
			menusService.saveMenus(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		return listMenus(page);
	}
	
	@RequestMapping(value="/updateMenus")
	public ModelAndView updateMenus(Page page) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限

		PageData pd = new PageData();
		pd = this.getPageData();			
		
		if(pd.get("PARENT_ID") == null || pd.get("PARENT_ID").toString().isEmpty())
			pd.put("PARENT_ID", "0");
		
		menusService.updateMenus(pd);
		
		return listMenus(page);
	}
	
	@RequestMapping(value="/deleteMenus")
	public ModelAndView deleteMenus(Page page) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限

		PageData pd = new PageData();
		pd = this.getPageData();			
			
		deleteMenuTree(pd.getString("MENU_ID"));
		menusService.deleteMenus(pd);
		
		return listMenus(page);
	}
	
	public void deleteMenuTree(String id) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PARENT_ID", id);
		List<PageData> lst = menusService.listMenusByUpperId(pd);
		if(lst.size() == 0)
		{
			return;
		}		
		for(int i = 0; i < lst.size(); i++)
		{
			PageData pageData = lst.get(i);
			String cid = pageData.get("MENU_ID").toString();			
			deleteMenuTree(cid);
			
			pd.put("MENU_ID", cid);
			menusService.deleteMenus(pd);
		}
	}
	
	@RequestMapping(value="/hasMenus")
	@ResponseBody
	public Object hasMenus(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(pd.get("PARENT_ID") == null || pd.get("PARENT_ID").toString().isEmpty())
				pd.put("PARENT_ID", "0");
			
			if(menusService.findMenu(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}

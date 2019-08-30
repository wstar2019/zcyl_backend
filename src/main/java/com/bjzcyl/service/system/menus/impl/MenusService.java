package com.bjzcyl.service.system.menus.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.Menus;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.service.system.menus.MenusManager;
import com.bjzcyl.util.PageData;

@Service("menusService")
public class MenusService implements MenusManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listMenus(Page page)throws Exception{
		return (List<PageData>) dao.findForList("MenusMapper.getAllMenusList", page);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listMenusByUpperId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("MenusMapper.getMenusByUpperId", pd);
	}
	
	public void saveMenus(PageData page)throws Exception{
		dao.save("MenusMapper.saveMenus", page);
	}
	
	public void updateMenus(PageData page)throws Exception{
		dao.save("MenusMapper.updateMenus", page);
	}
	
	public void deleteMenus(PageData page)throws Exception{
		dao.delete("MenusMapper.deleteMenus", page);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menus> listSubMenuByParentId(String parentId) throws Exception {
		return (List<Menus>) dao.findForList("MenusMapper.listSubMenuByParentId", parentId);
	}
	
	public List<Menus> listAllMenus(String parentId, String url) throws Exception {
		List<Menus> menuList = this.listSubMenuByParentId(parentId);			
		for(Menus menu : menuList){
			menu.setSubMenu(this.listAllMenus(menu.getMENU_ID(), url));			
		}
		return menuList;
	}
	public PageData getMenuString(String Url)throws Exception
	{
		return (PageData)dao.findForObject("MenusMapper.getMenuString", Url);
	}
	public PageData findMenu(PageData pd)throws Exception
	{
		return (PageData)dao.findForObject("MenusMapper.find", pd);
	}
	///////////////////////////////R3//////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public List<SysPermission> getPermissionWithMenu(String menuId) throws Exception {
		return (List<SysPermission>) dao.findForList("SysPermissionMapper.listPermissionByMenuId", menuId);
	}
	@Override
	public List<Menus> listAllMenusWithPermission(String parentId, Menus parentMenu, String url) throws Exception {
		List<Menus> menuList = this.listSubMenuByParentId(parentId);
		if(menuList.size() == 0 && parentMenu != null){
			parentMenu.setPermission(this.getPermissionWithMenu(parentMenu.getMENU_ID()));
		}else{
			for(Menus menu : menuList){
				menu.setSubMenu(this.listAllMenusWithPermission(menu.getMENU_ID(), menu, url));
			}
		}
		
		return menuList;
	}
	
}

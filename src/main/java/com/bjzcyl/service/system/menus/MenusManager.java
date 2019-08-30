package com.bjzcyl.service.system.menus;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.Menus;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.util.PageData;


/** 财政接口类
 * @author KGH
 * 修改时间：2015.11.2
 */
public interface MenusManager {
		
	public List<PageData> listMenus(Page page)throws Exception;
	public List<PageData> listMenusByUpperId(PageData pd)throws Exception;
	public List<Menus> listSubMenuByParentId(String parentId)throws Exception;
	public void saveMenus(PageData pd)throws Exception;
	public void updateMenus(PageData pd)throws Exception;
	public void deleteMenus(PageData pd)throws Exception;
	public List<Menus> listAllMenus(String parentId, String url) throws Exception;
	public List<Menus> listAllMenusWithPermission(String parentId, Menus parentMenu, String url) throws Exception;
	public List<SysPermission> getPermissionWithMenu(String menuId)throws Exception;
	public PageData findMenu(PageData pd)throws Exception;
	public PageData getMenuString(String Url)throws Exception;
}

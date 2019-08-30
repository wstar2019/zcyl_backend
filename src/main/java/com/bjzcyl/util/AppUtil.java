package com.bjzcyl.util;


import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.ui.ModelMap;

import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.Menus;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.service.system.menus.MenusManager;
import com.bjzcyl.service.system.menus.impl.MenusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


public class AppUtil  {
	
	protected static Logger logger = Logger.getLogger(AppUtil.class);
	
	public static PageData setPageParam(PageData pd){
		String page_now_str = pd.get("page_now").toString();
		int pageNowInt = Integer.parseInt(page_now_str)-1;
		String page_size_str = pd.get("page_size").toString(); //每页显示记录数
		int pageSizeInt = Integer.parseInt(page_size_str);
		String page_now = pageNowInt+"";
		String page_start = (pageNowInt*pageSizeInt)+"";
		pd.put("page_now", page_now);
		pd.put("page_start", page_start);
		return pd;
	}

	public static AppUtil getInstance() {
		return new AppUtil();
	}

	int i = 0;
	public  String getIcons() {
		String[] icons = new String[]{
				"menu-icon fa fa-leaf black","menu-icon fa fa-tachometer"
				,"menu-icon fa  fa-coffee","menu-icon fa fa-glass","menu-icon fa fa-fighter-jet"
				,"menu-icon fa fa-circle-o","menu-icon fa fa-headphones","menu-icon fa fa-globe"
				,"menu-icon fa fa-square-o","menu-icon fa fa-eye","menu-icon fa fa-flag","menu-icon fa fa-lightbulb-o"
		};
		int r = i;
		i = (i + 1) % icons.length;
		return icons[r];
	}
	

	/**
	 * @param pd
	 * @param map
	 * @return
	 */
	public static Object returnObject(PageData pd, @SuppressWarnings("rawtypes") Map map){
		if(pd.containsKey("callback")){
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}else{
			return map;
		}
	}

	public static void putTitle(ModelMap map, Integer menu_id) {
		map.put("title", Const.MENUS.get(menu_id));
	}
	
	
}

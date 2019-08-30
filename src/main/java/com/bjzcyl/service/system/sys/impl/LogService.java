package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.menus.MenusManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.PageData;

import utils.CurrentDateTime;

@Service("LogService")
public class LogService implements LogManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name="menusService")
	private MenusManager menusService;
	
	
	public String getMeunString(String Url){
		String menu = "";
		PageData m;
		try {
			m = menusService.getMenuString(Url);
			menu = m.getString("PM") + ">>" + m.getString("CM");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> loglistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysLogMapper.loglistPage", page);
	}
	
	@Override
	public void deleteLog(PageData pd) throws Exception {
		dao.delete("SysLogMapper.deleteLog", pd);
	}
	
	@Override
	public void insertLog(String _id, String _man, String _url, String _kind, String _content, String _at_sort, String _at_id) throws Exception {
		PageData pd = new PageData();

		CurrentDateTime dt = new CurrentDateTime();
		String dTime = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		
		pd.put("ID", _id);
		pd.put("OP_DT", dTime);
		pd.put("OP_MAN", _man);
		pd.put("OP_MENU", this.getMeunString(_url));
		pd.put("OP_KIND", _kind);
		pd.put("OP_CONTENT", _content);
		pd.put("AT_SORT", _at_sort);
		pd.put("AT_ID", _at_id);
		
		dao.save("SysLogMapper.insertLog", pd);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> loglistBySAArticlePage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysLogMapper.logsBySAArticlelistPage", page);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> loglistBySPArticlePage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysLogMapper.logsBySPArticlelistPage", page);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> loglistByTourArticlePage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysLogMapper.logsByTourArticlelistPage", page);
	}
}

package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface LogManager {
	
	public List<PageData> loglistPage(Page page)throws Exception;
	
	public void deleteLog(PageData pd)throws Exception;
	
	public void insertLog(String _id, String _man, String _url, String _kind, String _content, String _at_sort, String _at_id)throws Exception;
	
	public List<PageData> loglistBySAArticlePage(Page page)throws Exception;
	
	public List<PageData> loglistBySPArticlePage(Page page)throws Exception;
	
	public List<PageData> loglistByTourArticlePage(Page page)throws Exception;
}

package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface SAOptionManager {
	
	public List<PageData> listOption(Page page)throws Exception;
	public List<PageData> listAllOption(PageData pd)throws Exception;
	public PageData findByName(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void saveOpt(PageData pd)throws Exception;
	public void editOpt(PageData pd)throws Exception;
	public void changeStateOpt(PageData pd)throws Exception;
	public void deleteOpt(PageData pd)throws Exception;
	public void deleteAllOpt(String[] _IDS)throws Exception;
	
	
	
}

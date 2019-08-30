package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface OptionValueManager {
	
	public List<PageData> listOptVal(Page page)throws Exception;
	public List<PageData> listAllOptVal(PageData pd)throws Exception;
	public PageData findByName(PageData pd)throws Exception;
	public List<PageData> findByMid(PageData pd)throws Exception;
	public List<PageData> getValuesOfCbxByMid(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void saveOptVal(PageData pd)throws Exception;
	public void editOptVal(PageData pd)throws Exception;
	public void deleteOptVal(PageData pd)throws Exception;
	public void deleteAllOptVal(String[] _IDS)throws Exception;
	
}

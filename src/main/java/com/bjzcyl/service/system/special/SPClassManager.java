package com.bjzcyl.service.system.special;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.SPClass;
import com.bjzcyl.util.PageData;


public interface SPClassManager {
	
	public List<PageData> getClasslistPage(Page page)throws Exception;	
	public void saveClass(PageData pd)throws Exception;	
	public void updateClass(PageData pd)throws Exception;	
	public void deleteClass(PageData pd)throws Exception;	
	public PageData findClassById(PageData pd) throws Exception;	
	public List<PageData> listClassByUpperId(PageData pd)throws Exception;
	public List<SPClass> listAllClass(String parentId) throws Exception;
}

package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.SAClass;
import com.bjzcyl.util.PageData;


public interface SAClassManager {
	
	public List<PageData> getClasslistPage(Page page)throws Exception;	
	public void saveClass(PageData pd)throws Exception;	
	public void updateClass(PageData pd)throws Exception;	
	public void deleteClass(PageData pd)throws Exception;	
	public PageData findClassById(PageData pd) throws Exception;	
	public List<PageData> listClassByUpperId(PageData pd)throws Exception;
	public List<SAClass> listAllClass(String parentId) throws Exception;
}

package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface RolesManager {	
	public List<PageData> rolelistPage(Page page)throws Exception;
	public List<PageData> roleList(PageData pd)throws Exception;
	public void saveRole(PageData pd)throws Exception;
	public void deleteRole(PageData pd)throws Exception;
	public void updateRole(PageData pd)throws Exception;
	public PageData getRoleByRoleID(PageData pd)throws Exception;
}

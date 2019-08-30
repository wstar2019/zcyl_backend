package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface ManagerManager {
	public PageData findManager(PageData pd)throws Exception;
	public List<PageData> managerlistPage(Page page)throws Exception;
	public List<PageData> listAllManager(PageData pd)throws Exception;
	public void updatePass(PageData pd)throws Exception;
	public void updateManager(PageData pd)throws Exception;
	public void updateManagerRole(PageData pd)throws Exception;
	public void updateManagerState(PageData pd)throws Exception;	
	public void saveManager(PageData pd)throws Exception;
	public void deleteManager(PageData pd)throws Exception;
	public List<PageData> findByName(PageData pd) throws Exception;	
	public PageData findByLGID(PageData pd) throws Exception;
	public PageData getManagerByNameAndPwd(PageData pd) throws Exception;
	public void initManagerRole(PageData pd) throws Exception;
}

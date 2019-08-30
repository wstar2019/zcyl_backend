package com.bjzcyl.service.system.sitnews;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface KPNewsManager {
	public List<PageData> kpNewslistPage(Page page)throws Exception;
	public void deleteSN(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void updateSN(PageData pd)throws Exception;
	public void saveSN(PageData pd)throws Exception;
	public PageData prevNews(PageData pd)throws Exception;
	public PageData nextNews(PageData pd)throws Exception;
	
	public void updateTravelerFPViewState(PageData pd)throws Exception;
	public List<PageData> getFPNews() throws Exception;
	public List<PageData> getFPNewsList(PageData pd) throws Exception;
	public PageData getFPListCount(PageData pd)throws Exception;
}

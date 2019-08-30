package com.bjzcyl.service.system.sitnews;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface KPSituationManager {
	public List<PageData> kpSituationlistPage(Page page)throws Exception;
	public void deleteSN(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public PageData prevSituation(PageData pd)throws Exception;
	public PageData nextSituation(PageData pd)throws Exception;
	public void updateSN(PageData pd)throws Exception;
	public void saveSN(PageData pd)throws Exception;
	
	public List<PageData> getSitsListForTraveler(PageData pd) throws Exception;
}

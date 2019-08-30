package com.bjzcyl.service.system.landingpage;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface FPNewsManager {
	public List<PageData> listFPNews(Page page)throws Exception;
//	public List<PageData> fpNewsAll() throws Exception;
//	public List<PageData> getFPNews() throws Exception;
//	public void saveFPNews(PageData pd)throws Exception;
//	public void updateFPNews(PageData pd)throws Exception;
//	public void updateFPNewsViewNum(PageData pd)throws Exception;
//	public void updateFPNewsViewState(PageData pd)throws Exception;
//	public void deleteFPNews(PageData pd)throws Exception;
//	public List<PageData> hasFPNews(PageData pd ) throws Exception;
}

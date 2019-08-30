package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.util.PageData;


public interface TourArticleOptionManager {
	
	public List<PageData> listTAOption(PageData pd)throws Exception;
	public PageData checkItem(PageData pd)throws Exception;
	public void saveTAOption(PageData pd)throws Exception;
	public void deleTAOption(PageData pd)throws Exception;
	
}

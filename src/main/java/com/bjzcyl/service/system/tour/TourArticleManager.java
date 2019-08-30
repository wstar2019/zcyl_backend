package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface TourArticleManager {
	
	public List<PageData> listTourArticle(Page page)throws Exception;
	public PageData findByName(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void updateArt(PageData pd)throws Exception;
	public void saveArt(PageData pd)throws Exception;
	public void deleteArt(PageData pd)throws Exception;
	public List<PageData> getTourListForTraveler(PageData pd) throws Exception;
	public PageData getTourListCountForTraveler(PageData pd) throws Exception;
	
}

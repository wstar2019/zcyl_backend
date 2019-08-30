package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface SAArticleManager {
	public List<PageData> getSAListForTraveler(PageData pd) throws Exception;
	public PageData getSAListCountForTraveler(PageData pd)throws Exception;
	
	public List<PageData> listArticle(Page page)throws Exception;
	public PageData findByName(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void updateArt(PageData pd)throws Exception;
	public void saveArt(PageData pd)throws Exception;
	public void deleteArt(PageData pd)throws Exception;
	
}

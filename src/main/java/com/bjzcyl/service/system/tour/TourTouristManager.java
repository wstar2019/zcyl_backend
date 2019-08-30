package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface TourTouristManager {
	
	public List<PageData> listTourTourist(Page page)throws Exception;
	public PageData findByName(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void saveTourist(PageData pd)throws Exception;
	public void editTourist(PageData pd)throws Exception;
	public void deleteTourist(PageData pd)throws Exception;
	
}

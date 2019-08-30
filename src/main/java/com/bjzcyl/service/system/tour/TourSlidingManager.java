package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface TourSlidingManager {
	public List<PageData> listTourSlide(Page page)throws Exception;
	public List<PageData> tourSlideAll() throws Exception;
	public void saveTourSlide(PageData pd)throws Exception;
	public void updateTourSlide(PageData pd)throws Exception;
	public void deleteTourSlide(PageData pd)throws Exception;
	public void changeStateSlide(PageData pd)throws Exception;
}

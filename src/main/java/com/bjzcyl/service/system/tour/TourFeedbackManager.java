package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface TourFeedbackManager {
	public List<PageData> listTourFeedback(Page page)throws Exception;
	public void deleteFeedback(PageData pd)throws Exception;
	public PageData findById(PageData pd)throws Exception;
}

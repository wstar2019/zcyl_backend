package com.bjzcyl.service.system.special;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface SPFeedbackManager {
	public List<PageData> listFeedback(Page page)throws Exception;
	public void deleteFeedback(PageData pd)throws Exception;
}

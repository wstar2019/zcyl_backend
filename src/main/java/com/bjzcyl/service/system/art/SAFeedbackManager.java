package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface SAFeedbackManager {
	public List<PageData> listFeedback(Page page)throws Exception;
	public void deleteFeedback(PageData pd)throws Exception;
}

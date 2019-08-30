package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPFeedbackManager;
import com.bjzcyl.util.PageData;


@Service("spFeedbackService")
public class SPFeedbackService implements SPFeedbackManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFeedback(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SPFeedbackMapper.feedbacklistPage", page);
	}
	
	@Override
	public void deleteFeedback(PageData pd) throws Exception {
		dao.delete("SPFeedbackMapper.deleteFeedback", pd);
	}

}

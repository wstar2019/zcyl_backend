package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SAFeedbackManager;
import com.bjzcyl.util.PageData;


@Service("saFeedbackService")
public class SAFeedbackService implements SAFeedbackManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFeedback(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SAFeedbackMapper.feedbacklistPage", page);
	}
	
	@Override
	public void deleteFeedback(PageData pd) throws Exception {
		dao.delete("SAFeedbackMapper.deleteFeedback", pd);
	}

}

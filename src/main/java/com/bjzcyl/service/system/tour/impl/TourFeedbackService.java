package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.TourFeedbackManager;
import com.bjzcyl.util.PageData;


@Service("tourFeedbackService")
public class TourFeedbackService implements TourFeedbackManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listTourFeedback(Page page)throws Exception{
		return (List<PageData>) dao.findForList("TourFeedbackMapper.tourfeedbacklistPage", page);
	}
	
	@Override
	public void deleteFeedback(PageData pd) throws Exception {
		dao.delete("TourFeedbackMapper.deleteFeedback", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourFeedbackMapper.findById", pd);
	}

}

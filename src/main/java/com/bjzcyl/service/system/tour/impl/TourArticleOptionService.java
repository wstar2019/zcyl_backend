package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.system.tour.TourArticleOptionManager;
import com.bjzcyl.util.PageData;


@Service("tAOptionService")
public class TourArticleOptionService implements TourArticleOptionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listTAOption(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("TourArticleOptionMapper.tAOptionList", pd);
	}
	@Override
	public PageData checkItem(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourArticleOptionMapper.checkItem", pd);
	}
	@Override
	public void saveTAOption(PageData pd) throws Exception {
		dao.save("TourArticleOptionMapper.saveTAOption", pd);		
	}
	
	@Override
	public void deleTAOption(PageData pd) throws Exception {
		dao.delete("TourArticleOptionMapper.deleTAOption", pd);
	}

}

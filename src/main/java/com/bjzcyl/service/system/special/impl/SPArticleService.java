package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPArticleManager;
import com.bjzcyl.util.PageData;


@Service("spArticleService")
public class SPArticleService implements SPArticleManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listArticle(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SPArticleMapper.articlelistPage", page);
	}
	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPArticleMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPArticleMapper.findById", pd);
	}

	@Override
	public void saveArt(PageData pd) throws Exception {
		dao.save("SPArticleMapper.saveArt", pd);		
	}

	@Override
	public void updateArt(PageData pd) throws Exception {
		dao.update("SPArticleMapper.updateArt", pd);
	}
	
	@Override
	public void deleteArt(PageData pd) throws Exception {
		dao.delete("SPArticleMapper.deleteArt", pd);
	}
	
	@Override
	public PageData getSPListCountForTraveler(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPArticleMapper.spListCountForTraveler", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getSPListForTraveler(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SPArticleMapper.spPageListForTraveler", pd);
	}
}

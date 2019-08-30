package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SAArticleManager;
import com.bjzcyl.util.PageData;


@Service("saArticleService")
public class SAArticleService implements SAArticleManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listArticle(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SAArticleMapper.articlelistPage", page);
	}
	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAArticleMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAArticleMapper.findById", pd);
	}
	@Override
	public void saveArt(PageData pd) throws Exception {
		dao.save("SAArticleMapper.saveArt", pd);		
	}
	@Override
	public void updateArt(PageData pd) throws Exception {
		dao.update("SAArticleMapper.updateArt", pd);
	}	
	@Override
	public void deleteArt(PageData pd) throws Exception {
		dao.delete("SAArticleMapper.deleteArt", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getSAListForTraveler(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SAArticleMapper.saPageListForTraveler", pd);
	}
	@Override
	public PageData getSAListCountForTraveler(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAArticleMapper.saListCountForTraveler", pd);
	}
	

}

package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.system.art.SAArticleOptionManager;
import com.bjzcyl.util.PageData;


@Service("saArticleOptionService")
public class SAArticleOptionService implements SAArticleOptionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listArticleOption(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SAArticleOptionMapper.listArticleOption", pd);
	}
	@Override
	public PageData checkItem(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAArticleOptionMapper.checkItem", pd);
	}
	@Override
	public void saveSAOption(PageData pd) throws Exception {
		dao.save("SAArticleOptionMapper.saveSAOption", pd);		
	}
	
	@Override
	public void deleSAOption(PageData pd) throws Exception {
		dao.delete("SAArticleOptionMapper.deleSAOption", pd);
	}

}

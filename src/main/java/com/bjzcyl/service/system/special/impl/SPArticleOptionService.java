package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.system.special.SPArticleOptionManager;
import com.bjzcyl.util.PageData;


@Service("spArticleOptionService")
public class SPArticleOptionService implements SPArticleOptionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listArticleOption(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SPArticleOptionMapper.listArticleOption", pd);
	}
	@Override
	public PageData checkItem(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPArticleOptionMapper.checkItem", pd);
	}
	@Override
	public void saveSPOption(PageData pd) throws Exception {
		dao.save("SPArticleOptionMapper.saveSPOption", pd);		
	}
	
	@Override
	public void deleSPOption(PageData pd) throws Exception {
		dao.delete("SPArticleOptionMapper.deleSPOption", pd);
	}

}
